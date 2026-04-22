# Sistema de Oportunidades — Documentação

DISCLAIMER: Gerei os testes automatizados e a documentação com ajuda do GitHub Copilot mas verifiquei tudo(Pedro H). Os testes eram só pra ajudar a rodar os Services já que a implementação de interface ainda não faz parte desta etapa. Como não sabia se o senhor aceitaria só os testes automatizados coloquei a opção de fazer eles manualmente também inserindo os inputs. Por achar que não fazia parte diretamente do que foi pedido pra primeira etapa(apesar de necessário para ver se os Services estavam funcionando) utilizei o Copilot pra agilizar o processo. Todas as Entidades e Serviços foram implementados manualmente pela equipe.


Resumo rápido
- Projeto para compor a nota da disciplina de Linguagem de Programação II: Sistema para gerenciar oportunidades (eventos, cursos, projetos, oficinas), inscrições, usuários e grupos para auxiliar os discentes da UFMA a cumprir as horas necessárias de acordo com o PPC do seu curso.
- Estrutura simples: entidades em `src/entity`, repositórios(somente dados de teste por enquanto) em `src/repository`, serviços em `src/services` e casos de teste em `test/`.

Arquivos principais
- `src/Main.java`: ponto de entrada. Oferece opção para rodar os testes automatizados (1) ou simulação manual interativa (2).
- `test/`: classes de teste com `main()` com testes unitários de cada serviço.


Como executar
- Compilar os arquivos do programa
```powershell
    javac -d bin src\*.java src\entity\*.java src\entity\enums\*.java src\repository\*.java src\services\*.java src\test\*.java
```
- Executar o programa principal (menu):
```powershell
java -cp bin Main
```
- Executar um teste específico (por exemplo `DocenteServiceTest`):
```powershell
java -cp bin test.DocenteServiceTest
```
  Observação: os testes no diretório `test/` estão escritos como classes com `main()` — também é possível executar via `Main` (opção 1) que chama cada `main()`.

Simulação manual (opção 2)
- Ao escolher a opção 2 em `Main`, abre-se um menu interativo para o professor testar o sistema inserindo inputs:
  - 1 Listar Discentes
  - 2 Listar Docentes
  - 3 Listar Cursos
  - 4 Listar Oportunidades
  - 5 Criar / Publicar Oportunidade (fornecer título, descrição, carga, vagas, tipo, modalidade, duração)
  - 6 Inscrever Discente em Oportunidade (selecionar índices e informar motivação)
  - 7 Listar Inscrições (simulação)
  - 8 Fechar inscrições de uma oportunidade (fecha definindo fim = agora)
  - 9 Alterar senha de um usuário (simulação)
  - 0 Sair
- Uso: siga as instruções de cada opção digitando o número correspondente e preenchendo os campos solicitados.

Notas técnicas
- Repositórios em `src/repository` populam listas com dados de exemplo para demonstração.
- Serviços em `src/services` implementam regras simples: publicar oportunidade, criar inscrição (em memória), mudar senha (simulação), etc.
- A simulação mantém inscrições em memória durante a execução; não há  ainda.

Problemas comuns
- Certifique-se de compilar todos os arquivos antes de executar (`bin` atualizado).
- Se houver erros de compilação, execute o comando de compilação e verifique a mensagem do `javac`.

Próximos passos
- Implementar o restante dos Requisitos Funcionais
- Adicionar persistência (arquivos/BD) para salvar dados entre execuções.
- Se adequar aos Requisitos Não Funcionais
- Conectar com a interface