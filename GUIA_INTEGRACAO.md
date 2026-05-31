# Guia de integração — RF011, RF012, RF015, RF016, RF017, RF019

Este documento descreve o que foi implementado e como conectar com os demais módulos do sistema.

---

## Visão geral do que foi adicionado

### Novos arquivos criados
| Arquivo | Descrição |
|---|---|
| `services/OportunidadesService.java` | Lógica de criação e aprovação de oportunidades (RF011, RF012) |
| `services/InscricaoService.java` | Lógica de inscrições (RF015, RF016, RF017) |
| `services/CertificadoService.java` | Encerramento e geração de certificados (RF019) |
| `repository/InscricaoRepository.java` | Armazenamento em memória das inscrições |

### Arquivos modificados
| Arquivo | O que mudou |
|---|---|
| `entity/Oportunidade.java` | Novos campos: `dataInicioInscricoes`, `dataFimInscricoes`, `feedbackReprovacao`. Construtor sem `StatusOportunidade` (sempre começa como `RASCUNHO`) |
| `entity/Inscricao.java` | Campo `sustituidoPor` para rastrear substituições. Status começa como `PENDENTE` automaticamente |
| `entity/enums/StatusOportunidade.java` | 9 estados: `RASCUNHO`, `AGUARDANDO_APROVACAO`, `APROVADA`, `REPROVADA`, `EM_INSCRICOES`, `EM_EXECUCAO`, `CONCLUIDA`, `CANCELADA`, `ARQUIVADA` |
| `entity/enums/StatusInscricao.java` | 5 estados: `PENDENTE`, `APROVADO`, `REJEITADO`, `CANCELADO`, `SUBSTITUIDO` |
| `entity/enums/TiposOportunidade.java` | Adicionados `PROGRAMA` e `PRESTACAO_SERVICO` |
| `repository/OportunidadeRepository.java` | Métodos `salvar()` e `listarTodas()` (lista interna deixou de ser pública) |

---

## Como instanciar os services

Todos os services recebem seus repositórios via construtor. Crie os repositórios primeiro:

```java
// 1. Repositórios
OportunidadeRepository opRepo   = new OportunidadeRepository();
InscricaoRepository    inscRepo = new InscricaoRepository();

// 2. Services
OportunidadesService opService   = new OportunidadesService(opRepo);
InscricaoService     inscService = new InscricaoService(inscRepo);
CertificadoService   certService = new CertificadoService(inscRepo);
```

---

## Fluxo completo de uma oportunidade (RF011 → RF019)

```
[RF011] criarOportunidade()        → status: RASCUNHO
[RF011] submeterParaAprovacao()    → status: AGUARDANDO_APROVACAO
[RF012] aprovarOportunidade()      → status: APROVADA (ou EM_INSCRICOES se período já aberto)
[RF015] criarInscricao()           → inscrição com status: PENDENTE
[RF015] aprovarInscricao()         → inscrição com status: APROVADO
[RF016] cancelarInscricao()        → inscrição com status: CANCELADO
[RF017] substituirParticipante()   → original: SUBSTITUIDO, substituto: APROVADO
[RF019] encerrarEGerarCertificados() → oportunidade: CONCLUIDA, gera Certificados[]
```

---

## Métodos públicos disponíveis

### OportunidadesService

```java
// Cria uma oportunidade no estado RASCUNHO
Oportunidade criarOportunidade(String titulo, String descricao,
    TiposOportunidade tipo, TiposModalidade modalidade,
    Integer cargaHoraria, Integer vagas,
    LocalDateTime inicio, LocalDateTime fim,
    LocalDateTime dataInicioInscricoes, LocalDateTime dataFimInscricoes,
    Usuario autor, Docente docenteResponsavel)

// Muda de RASCUNHO para AGUARDANDO_APROVACAO
void submeterParaAprovacao(Oportunidade oportunidade)

// Docente aprova → APROVADA (ou EM_INSCRICOES se período já aberto)
void aprovarOportunidade(Oportunidade oportunidade, Docente docente)

// Docente reprova com motivo obrigatório → REPROVADA
void reprovarOportunidade(Oportunidade oportunidade, Docente docente, String motivo)

// Lista apenas as com status EM_INSCRICOES (para portal de inscrições)
List<Oportunidade> listarOportunidadesAbertas()

// Lista as que esse docente precisa analisar
List<Oportunidade> listarAguardandoAprovacao(Docente docente)

List<Oportunidade> listarTodas()
```

### InscricaoService

```java
// Inscreve discente — só funciona se oportunidade estiver EM_INSCRICOES
Inscricao criarInscricao(Oportunidade oportunidade, Discente discente, String motivacao)

// Responsável aprova — verifica vagas disponíveis automaticamente
void aprovarInscricao(Inscricao inscricao, Oportunidade oportunidade)

// Responsável rejeita
void rejeitarInscricao(Inscricao inscricao)

// Discente cancela — bloqueado se atividade em execução ou encerrada
void cancelarInscricao(Inscricao inscricao)

// Substitui aprovado por outro discente que já está na lista de interessados
// O novoDiscente PRECISA ter inscrição existente na mesma oportunidade
Inscricao substituirParticipante(Inscricao inscricaoOriginal, Discente novoDiscente)

List<Inscricao> listarPorOportunidade(Oportunidade oportunidade)
List<Inscricao> listarAprovados(Oportunidade oportunidade)
```

### CertificadoService

```java
// Encerra a oportunidade e gera certificados para todos com inscrição APROVADA
// Lança exceção se oportunidade já estiver CONCLUIDA ou CANCELADA
List<Certificados> encerrarEGerarCertificados(Oportunidade oportunidade)

List<Certificados> listarCertificados()
List<Certificados> listarCertificadosPorDiscente(Discente discente)
```

---

## Pontos de integração com outros módulos

### Módulo de Notificações
Os services imprimem mensagens no console onde notificações reais devem ser disparadas:
- `aprovarOportunidade()` → notificar autor da oportunidade
- `reprovarOportunidade()` → notificar autor com feedback
- `aprovarInscricao()` / `rejeitarInscricao()` → notificar discente (RF040)
- `encerrarEGerarCertificados()` → notificar discentes que certificados estão prontos (RF042)

Para integrar: substitua ou adicione chamadas ao seu `NotificacaoService` nesses pontos.

### Módulo de Aproveitamento de Horas
Após `encerrarEGerarCertificados()`, as horas podem ser creditadas automaticamente no histórico do discente. A entidade `Aproveitamento` já existe no projeto — conecte via:
```java
// após gerar certificados, creditar horas
for (Certificados cert : certificadosGerados) {
    aproveitamentoService.creditarHoras(cert.getDiscente(), cert.getHoras());
}
```

### Módulo de Portal Público (RF013)
Use `listarOportunidadesAbertas()` para exibir no portal:
```java
List<Oportunidade> abertas = opService.listarOportunidadesAbertas();
// exibir no portal...
```

### Módulo de Validação de Certificados (RF027)
Cada certificado gerado tem um `uuidHash` único. Para validar:
```java
String hash = certificado.getUuidHash(); // ex: "F416F2FDE4764F76"
// seu módulo busca pelo hash e exibe os dados
```

---

## Como rodar os testes

```bash
# Compilar (na pasta src/)
javac -encoding UTF-8 entity/*.java entity/enums/*.java repository/*.java services/*.java test/*.java

# Rodar testes de oportunidades (RF011, RF012)
java -cp . test.OportunidadesServiceTest

# Rodar testes de inscrições (RF015, RF016, RF017, RF019)
java -cp . test.InscricaoServiceTest
```
