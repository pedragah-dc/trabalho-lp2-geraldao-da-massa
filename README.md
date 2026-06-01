# Sistema de Oportunidades — Documentação

DISCLAIMER: Gerei os testes automatizados e a documentação com ajuda do GitHub Copilot mas verifiquei tudo(Pedro H). Os testes eram só pra ajudar a rodar os Services já que a implementação de interface ainda não faz parte desta etapa. Como não sabia se o senhor aceitaria só os testes automatizados coloquei a opção de fazer eles manualmente também inserindo os inputs. Por achar que não fazia parte diretamente do que foi pedido pra primeira etapa(apesar de necessário para ver se os Services estavam funcionando) utilizei o Copilot pra agilizar o processo. Todas as Entidades e Serviços foram implementados manualmente pela equipe.

Resumo rápido
- Projeto para compor a nota da disciplina de Linguagem de Programação II: Sistema para gerenciar oportunidades (eventos, cursos, projetos, oficinas), inscrições, usuários e grupos para auxiliar os discentes da UFMA a cumprir as horas necessárias de acordo com o PPC do seu curso.
- Estrutura simples: entidades em `src/entity`, repositórios(somente dados de teste por enquanto) em `src/repository`, serviços em `src/services` e casos de teste em `test/`.
- **NOVO**: Menu interativo integrado com fluxo completo dos requisitos (RF011 → RF019) e testes unitários automatizados.

Arquivos principais
- `src/Main.java`: ponto de entrada. Oferece opção para rodar testes automatizados integrados (1) ou simulação manual interativa com menu completo (2).
- `test/IntegratedServiceTests.java`: **NOVO** - Testes unitários integrados que exercitam todos os services em fluxo (RF011 até RF019).
- `test/`: classes de teste com `main()` com testes unitários de cada serviço.
- `src/entity/Certificados.java`: **NOVO** - Classe para representar certificados gerados (RF019).


Como executar
- Compilar os arquivos do programa
```powershell
    javac -d bin src\*.java src\entity\*.java src\entity\enums\*.java src\repository\*.java src\services\*.java src\test\*.java src\utils\*.java
```

## Opção 1: Testes Automatizados Integrados (NOVO)
- Execute o programa principal e escolha a opção **1**:
```powershell
java -cp bin Main
```
- O sistema executará automaticamente:
  - **Testes Integrados Completos** (18 testes) que exercitam o fluxo RF011 → RF019:
    - Cadastro de usuários (Discentes, Docentes)
    - Alteração de senhas e permissões
    - Criação e aprovação de oportunidades (RF011, RF012)
    - Inscrições, aprovações e rejeições (RF015)
    - Cancelamento de inscrições (RF016)
    - Geração de certificados (RF019)
    - Testes de aproveitamento de horas
  - Testes específicos de cada serviço (DocenteServiceTest, GrupoServiceTest, etc.)

- Relatório final com:
  - Total de testes executados
  - Testes passados e falhados
  - Taxa de sucesso em percentual

## Opção 2: Simulação Manual Interativa (NOVO - Menu Completo)
- Execute o programa principal e escolha a opção **2**:
```powershell
java -cp bin Main
```

### Menu Principal
```
1 - Gerenciar Perfis (Discentes, Docentes, Admin)
2 - Criar e Submeter Oportunidades (RF011)
3 - Aprovar/Reprovar Oportunidades (RF012)
4 - Inscrições e Participantes (RF015, RF016, RF017)
5 - Encerramento e Certificados (RF019)
6 - Relatórios e Consultas
0 - Sair da Simulação
```

### 1. Gerenciar Perfis
- **Cadastrar Novo Discente**: Insira nome, email, senha e matrícula
- **Cadastrar Novo Docente**: Insira nome, email, senha, SIAPE e departamento
- **Listar Discentes**: Visualiza todos os discentes cadastrados
- **Listar Docentes**: Visualiza todos os docentes cadastrados
- **Alterar Senha**: Selecione um discente e informe a nova senha

### 2. Criar e Submeter Oportunidades (RF011)
- Selecione um docente responsável
- Informe os dados da oportunidade:
  - Título
  - Descrição
  - Tipo (EVENTO, CURSO, WORKSHOP, PROGRAMA, PRESTACAO_SERVICO)
  - Modalidade (PRESENCIAL, HIBRIDO, ONLINE)
  - Carga Horária
  - Número de Vagas
- A oportunidade é criada em status **RASCUNHO**
- Opção para submeter imediatamente para aprovação (status → **AGUARDANDO_APROVACAO**)

### 3. Aprovar/Reprovar Oportunidades (RF012)
- Lista oportunidades em status **AGUARDANDO_APROVACAO**
- Selecione uma oportunidade e um docente aprovador
- Escolha:
  - **Aprovar**: Status → **APROVADA** (ou **EM_INSCRICOES** se período já aberto)
  - **Reprovar**: Status → **REPROVADA** (informe motivo)

### 4. Inscrições e Participantes (RF015, RF016, RF017)
#### 4.1 Inscrever Discente (RF015)
- Lista oportunidades com inscrições abertas (status **EM_INSCRICOES**)
- Selecione uma oportunidade e um discente
- Informe a motivação da inscrição
- Inscrição criada com status **PENDENTE**

#### 4.2 Aprovar Inscrição (RF015)
- Selecione uma inscrição em status **PENDENTE**
- Status → **APROVADO**
- Sistema verifica vagas disponíveis

#### 4.3 Rejeitar Inscrição (RF015)
- Selecione uma inscrição em status **PENDENTE**
- Status → **REJEITADO**

#### 4.4 Cancelar Inscrição (RF016)
- Selecione uma inscrição não-cancelada
- Status → **CANCELADO**
- Discente pode cancelar sua inscrição antes do início da oportunidade

#### 4.5 Listar Inscrições
- Visualiza todas as inscrições com seus status

### 5. Encerramento e Certificados (RF019)
#### 5.1 Encerrar Oportunidade e Gerar Certificados
- Lista oportunidades que podem ser encerradas:
  - Status **EM_EXECUCAO**, **APROVADA** ou **EM_INSCRICOES**
- Selecione a oportunidade
- Sistema:
  - Muda status para **CONCLUIDA**
  - Lista todos os inscritos com status **APROVADO**
  - Gera certificados com hash único para cada participante
  - Exibe quantidade de certificados gerados

### 6. Relatórios e Consultas
- **Total de Oportunidades por Status**: Quebra de oportunidades por cada status
- **Total de Inscrições por Status**: Quebra de inscrições por cada status
- **Certificados Emitidos**: Lista detalhada de certificados com nome do discente, hash e carga horária
- **Log de Alterações de Permissões**: Histórico de mudanças de roles de usuários

## Executar Testes Específicos
- Executar um teste específico (por exemplo `DocenteServiceTest`):
```powershell
java -cp bin test.DocenteServiceTest
```

- Executar os testes integrados diretamente:
```powershell
java -cp bin test.IntegratedServiceTests
```

Observação: os testes no diretório `test/` estão escritos como classes com `main()` — também é possível executar via `Main` (opção 1) que chama cada `main()`.

Notas técnicas
- Repositórios em `src/repository` populam listas com dados de exemplo para demonstração.
- Serviços em `src/services` implementam regras simples: publicar oportunidade, criar inscrição (em memória), mudar senha (simulação), etc.
- A simulação mantém inscrições em memória durante a execução; não há persistência em BD ainda.
- Todos os services estão **sem modificações** e são utilizados integrado no menu e nos testes.

## Fluxo de Requisitos Funcionais (RF011 → RF019)

O sistema implementa o fluxo completo conforme especificado:

```
[RF011] Criar Oportunidade (RASCUNHO)
   ↓
[RF011] Submeter para Aprovação (AGUARDANDO_APROVACAO)
   ↓
[RF012] Docente Aprova (APROVADA ou EM_INSCRICOES)
   ↓ ou [RF012] Docente Reprova (REPROVADA)
[RF015] Discente se inscreve (PENDENTE)
   ↓
[RF015] Responsável aprova/rejeita (APROVADO ou REJEITADO)
   ↓
[RF016] Discente cancela inscrição (CANCELADO)
   ↓
[RF017] Responsável substitui participante (SUBSTITUIDO)
   ↓
[RF019] Encerrar oportunidade e gerar certificados (CONCLUIDA)
```

## Serviços Disponíveis (Sem Modificações)

| Serviço | Responsabilidade |
|---------|------------------|
| `OportunidadesService` | RF011 (criar, submeter) e RF012 (aprovar/reprovar) |
| `InscricaoService` | RF015 (criar, aprovar, rejeitar), RF016 (cancelar), RF017 (substituir) |
| `CertificadoService` | RF019 (encerrar e gerar certificados) |
| `DocenteService` | Criar oportunidades e aprovar (wrapper de OportunidadesService) |
| `DiscenteService` | Autocadastro de discentes e criação de solicitações |
| `UsuarioService` | Gerenciar usuários, senhas e permissões |
| `AdministradorService` | Cadastro de docentes e PPC |
| `AproveitamentoService` | Solicitar aproveitamento de horas |
| `GrupoService` | Gerenciar grupos e membros |

## Estrutura de Testes

### IntegratedServiceTests (18 Testes)
Exercita o fluxo completo em uma única bateria:

1. **Testes de Usuários** (3)
   - Cadastro de Discente
   - Cadastro de Docente
   - Alteração de Senha

2. **Testes de Oportunidades** (5)
   - Criar Oportunidade (RF011)
   - Submeter para Aprovação (RF011)
   - Aprovar Oportunidade (RF012)
   - Reprovar Oportunidade (RF012)
   - Listar Oportunidades Abertas

3. **Testes de Inscrições** (4)
   - Inscrever Discente (RF015)
   - Aprovar Inscrição (RF015)
   - Rejeitar Inscrição (RF015)
   - Cancelar Inscrição (RF016)

4. **Testes de Certificados** (2)
   - Gerar Certificados (RF019)
   - Listar Certificados

5. **Testes de Permissões** (3)
   - Atribuir Permissão
   - Mudar Permissão
   - Remover Permissão

6. **Testes de Aproveitamento** (1)
   - Solicitar Aproveitamento de Horas

Cada teste apresenta feedback com ✓ (sucesso) ou ✗ (falha), facilitando identificação de problemas.

Problemas comuns
- Certifique-se de compilar todos os arquivos antes de executar (`bin` atualizado):
  ```powershell
  javac -d bin src\*.java src\entity\*.java src\entity\enums\*.java src\repository\*.java src\services\*.java src\test\*.java src\utils\*.java
  ```
- Se houver erros de compilação, execute o comando de compilação e verifique a mensagem do `javac`.
- Se a classe `Certificados` não for encontrada, certifique-se de que `src/entity/Certificados.java` existe.
- Se o menu não responder aos inputs, pressione **Enter** após digitar cada opção.
- Para sair da simulação manual a qualquer momento, escolha a opção **0**.

## Exemplos de Uso

### Exemplo 1: Teste Automatizado Completo
```
$ java -cp bin Main
╔════════════════════════════════════════════════════════╗
║   SISTEMA DE OPORTUNIDADES - AMBIENTE DE TESTES      ║
╚════════════════════════════════════════════════════════╝

Escolha uma opção:
1 - Rodar testes automatizados (testes unitários)
2 - Simular o sistema manualmente (menu interativo)

Opção desejada: 1

╔══════════════════════════════════════════════════════════════╗
║       TESTES INTEGRADOS - TODOS OS SERVICES               ║
╚══════════════════════════════════════════════════════════════╝

[TESTE 1] Cadastro de Discente
✓ Discente cadastrado: João Silva

[TESTE 2] Cadastro de Docente
✓ Docente cadastrado: Prof. Maria (SIAPE: 111222)

... (mais testes)

RESUMO DOS TESTES
============================================================
✓ Testes Passados: 18
✗ Testes Falhados: 0
Total: 18
Taxa de Sucesso: 100.0%

🎉 TODOS OS TESTES PASSARAM COM SUCESSO!
```

### Exemplo 2: Simulação Manual - Fluxo Completo

#### Passo 1: Cadastrar Usuários
```
Opção desejada: 2

╔════════════════════════════════════════════════════════╗
║          MENU PRINCIPAL - SIMULAÇÃO INTERATIVA         ║
╚════════════════════════════════════════════════════════╝
1 - Gerenciar Perfis (Discentes, Docentes, Admin)
2 - Criar e Submeter Oportunidades (RF011)
3 - Aprovar/Reprovar Oportunidades (RF012)
4 - Inscrições e Participantes (RF015, RF016, RF017)
5 - Encerramento e Certificados (RF019)
6 - Relatórios e Consultas
0 - Sair da Simulação

Escolha: 1

╔════════════════════════════════════════════════════════╗
║        MENU - GERENCIAR PERFIS E USUÁRIOS             ║
╚════════════════════════════════════════════════════════╝
1 - Cadastrar Novo Discente
2 - Cadastrar Novo Docente (via Administrador)
3 - Listar Discentes
4 - Listar Docentes
5 - Alterar Senha de Usuário
0 - Voltar ao Menu Principal

Escolha: 1

--- Cadastro de Discente ---
Nome: João Silva
Email: joao@email.com
Senha: senha123
Matrícula: 2021001
✓ Discente cadastrado com sucesso!
  ID: 1, Nome: João Silva

Escolha: 2

--- Cadastro de Docente ---
Nome: Prof. Maria
Email: maria@ufma.br
Senha: prof123
SIAPE: 111222
Departamento: Computação
✓ Docente cadastrado com sucesso!
  ID: 2, Nome: Prof. Maria
```

#### Passo 2: Criar Oportunidade (RF011)
```
Escolha: 0  (volta ao menu principal)

Escolha: 2  (Criar Oportunidade)

╔════════════════════════════════════════════════════════╗
║    CRIAR E SUBMETER OPORTUNIDADE (RF011)              ║
╚════════════════════════════════════════════════════════╝

1) Prof. Maria (SIAPE: 111222, Email: maria@ufma.br)

Selecione o docente responsável (número): 1

Título da Oportunidade: Seminário de IA
Descrição: Palestras sobre Inteligência Artificial
Tipo (EVENTO, CURSO, WORKSHOP, PROGRAMA, PRESTACAO_SERVICO): EVENTO
Modalidade (PRESENCIAL, HIBRIDO, ONLINE): PRESENCIAL
Carga Horária: 8
Número de Vagas: 50

✓ Oportunidade criada em RASCUNHO!
  Título: Seminário de IA

Deseja submeter para aprovação agora? (S/N): S
✓ Oportunidade submetida para AGUARDANDO_APROVACAO!
```

#### Passo 3: Aprovar Oportunidade (RF012)
```
Escolha: 3  (Aprovar/Reprovar)

╔════════════════════════════════════════════════════════╗
║    APROVAR/REPROVAR OPORTUNIDADES (RF012)             ║
╚════════════════════════════════════════════════════════╝

Oportunidades aguardando aprovação:
1) Seminário de IA (Status: AGUARDANDO_APROVACAO)

Selecione a oportunidade (número): 1

Docentes disponíveis:
1) Prof. Maria (SIAPE: 111222, Email: maria@ufma.br)

Selecione o docente aprovador (número): 1

1 - Aprovar
2 - Reprovar
Ação: 1
✓ Oportunidade APROVADA!
```

#### Passo 4: Inscrever Discente (RF015)
```
Escolha: 4  (Inscrições e Participantes)

╔════════════════════════════════════════════════════════╗
║      INSCRIÇÕES E PARTICIPANTES (RF015-RF017)         ║
╚════════════════════════════════════════════════════════╝
1 - Inscrever Discente em Oportunidade (RF015)
2 - Aprovar Inscrição (RF015)
3 - Rejeitar Inscrição (RF015)
4 - Cancelar Inscrição (RF016)
5 - Listar Inscrições
0 - Voltar

Escolha: 1

--- Inscrever Discente em Oportunidade ---

Oportunidades com inscrições abertas:
1) Seminário de IA (Vagas: 50)

Selecione (número): 1

Discentes cadastrados:
1) João Silva (Matrícula: 2021001, Email: joao@email.com)

Selecione o discente (número): 1

Motivação da inscrição: Tenho interesse em IA
✓ Inscrição criada com status PENDENTE!
```

#### Passo 5: Aprovar Inscrição (RF015)
```
Escolha: 2  (Aprovar Inscrição)

--- Aprovar Inscrição ---

Inscrições Pendentes:
1) João Silva em Seminário de IA

Selecione a inscrição (número): 1
✓ Inscrição APROVADA!
```

#### Passo 6: Gerar Certificados (RF019)
```
Escolha: 5  (Encerramento e Certificados)

--- Encerrar Oportunidade e Gerar Certificados (RF019) ---

Oportunidades que podem ser encerradas:
1) Seminário de IA (Status: EM_INSCRICOES)

Selecione (número): 1

[RF019] Oportunidade 'Seminário de IA' ENCERRADA.
[RF019] Gerando certificados para 1 participante(s)...
[RF019]  Certificado gerado: João Silva | Hash: A1B2C3D4E5F6G7H8 | 8h
[RF019] Total de certificados gerados: 1
✓ Certificados gerados: 1
```

#### Passo 7: Consultar Relatórios
```
Escolha: 6  (Relatórios)

╔════════════════════════════════════════════════════════╗
║           MENU - RELATÓRIOS E CONSULTAS               ║
╚════════════════════════════════════════════════════════╝
1 - Total de Oportunidades por Status
2 - Total de Inscrições por Status
3 - Certificados Emitidos
4 - Log de Alterações de Permissões
0 - Voltar

Escolha: 3

--- Relatório de Certificados ---
Total de Certificados Emitidos: 1

Detalhes:
  - João Silva | Hash: A1B2C3D4E5F6G7H8 | Carga: 8h
```

Próximos passos
- Implementar o restante dos Requisitos Funcionais não cobertos (RF001-RF010, RF020+)
- Adicionar persistência (arquivos/BD) para salvar dados entre execuções
- Se adequar aos Requisitos Não Funcionais
- Conectar com a interface gráfica (GUI/Web)
- Expandir testes com casos de erro e edge cases
- Implementar tratamento de exceções mais robusto
- Adicionar validações mais estritas de entrada
- Integração com sistemas externos (email para notificações, etc.)

## Resumo das Alterações (Nova Versão)

### ✨ Novos Recursos Adicionados

1. **Menu Integrado Completo**
   - Menu principal estruturado com 6 seções temáticas
   - Submenu para cada funcionalidade
   - Navegação intuitiva com opção de retorno
   - Validação de inputs e tratamento de erros

2. **Testes Unitários Integrados**
   - Arquivo `IntegratedServiceTests.java` com 18 testes
   - Cobertura de fluxo RF011 → RF019
   - Relatório automático com taxa de sucesso
   - Feedback visual com ✓ e ✗

3. **Novas Entidades**
   - Classe `Certificados` para representar certificados gerados

4. **Melhorias na Main.java**
   - Inicialização centralizada de serviços
   - Métodos privados para organização
   - Constantes de layout (bordas decorativas)
   - Separação clara entre menu e testes

### 📊 Cobertura de Requisitos

| RF | Descrição | Implementado | Menu | Testes |
|----|-----------|--------------|------|--------|
| RF011 | Criar oportunidade | ✓ | ✓ | ✓ |
| RF011 | Submeter para aprovação | ✓ | ✓ | ✓ |
| RF012 | Aprovar oportunidade | ✓ | ✓ | ✓ |
| RF012 | Reprovar oportunidade | ✓ | ✓ | ✓ |
| RF015 | Criar inscrição | ✓ | ✓ | ✓ |
| RF015 | Aprovar inscrição | ✓ | ✓ | ✓ |
| RF015 | Rejeitar inscrição | ✓ | ✓ | ✓ |
| RF016 | Cancelar inscrição | ✓ | ✓ | ✓ |
| RF017 | Substituir participante | ✓ | ⚠ | — |
| RF019 | Gerar certificados | ✓ | ✓ | ✓ |

**Legenda**: ✓ = Implementado, ⚠ = Disponível via API, — = Não testado nesta versão