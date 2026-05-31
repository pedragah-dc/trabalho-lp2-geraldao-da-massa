import entity.*;
import entity.enums.*;
import repository.*;
import services.*;
import test.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUtils.lerStringValida;


public class Main {
    // Repositórios globais
    private static DiscenteRepository discenteRepo;
    private static DocenteRepository docenteRepo;
    private static OportunidadeRepository oportunidadeRepo;
    private static InscricaoRepository inscricaoRepo;
    private static AlteracaoPermissaoRepository alteracaoRepo;
    private static CursoRepository cursoRepo;
    private static SolicitacaoOportunidadeRepository solicitacaoRepo;

    // Services globais
    private static OportunidadesService oportunidadesService;
    private static InscricaoService inscricaoService;
    private static CertificadoService certificadoService;
    private static DocenteService docenteService;
    private static DiscenteService discenteService;
    private static UsuarioService usuarioService;
    private static AdministradorService administradorService;
    private static AproveitamentoService aproveitamentoService;
    private static GrupoService grupoService;
    private static CoordenadorService coordenadorService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarServicos();
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE OPORTUNIDADES - AMBIENTE DE TESTES        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Rodar testes automatizados (testes unitários)");
        System.out.println("2 - Simular o sistema manualmente (menu interativo)");
        System.out.print("\nOpção desejada: ");
        
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha
        
        switch (escolha) {
            case 1:
                rodarTodosTestes();
                break;
            case 2:
                menuSimulacaoManual(scanner);
                break;
            default:
                System.out.println("\n❌ Opção inválida. Encerrando a execução.");
        }
        
        scanner.close();
    }

    private static void menuAdministrador(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("║           MENU - AÇÕES RESTRITAS DO ADMINISTRADOR      ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("1 - Cadastrar/Atualizar PPC ");
            System.out.println("2 - Alterar Permissão de usuário");
            System.out.println("3 - Cadastrar Docente (sem passar por coordenador) ");
            System.out.println("4 - Cadastrar Administrador");
            System.out.println("0 - Voltar\n");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1":
                    cadastrarPPCAdministrador(scanner);
                    break;
                case "2":
                    alterarPermissaoComoAdministrador(scanner);
                    break;
                case "3":
                    cadastrarDocente(scanner);
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }
    
    private static void cadastrarPPCAdministrador(Scanner scanner) {
        System.out.println("\n--- Cadastrar/Atualizar PPC (Administrador) ---");

        try {
            // Escolher curso ou criar novo
            System.out.println("Cursos disponíveis:");
            for (int i = 0; i < cursoRepo.listaCursos.size(); i++) {
                Curso c = cursoRepo.listaCursos.get(i);
                System.out.printf("%d) %s (Código: %d, CH: %d, Versão: %s)\n", i+1, c.getNome(), c.getCodigo(), c.getCargaHoraria(), c.getVersaoPPC());
            }

            System.out.print("Deseja criar um novo curso? (S/N): ");
            String criar = scanner.nextLine().trim().toUpperCase();
            Curso cursoSelecionado = null;
            if (criar.equals("S")) {
                System.out.print("Nome do curso: ");
                String nome = scanner.nextLine();
                System.out.print("Código do curso (numero): ");
                Integer codigo = Integer.parseInt(scanner.nextLine());
                System.out.print("Carga horária total: ");
                Integer ch = Integer.parseInt(scanner.nextLine());
                System.out.print("Versão inicial do PPC: ");
                String versao = scanner.nextLine();

                Curso novo = new Curso(nome, codigo, ch, versao);
                novo.setListaAlteracaoPPC(new java.util.ArrayList<>());
                cursoRepo.listaCursos.add(novo);
                cursoSelecionado = novo;
            } else {
                System.out.print("Selecione o curso (número): ");
                int idx = Integer.parseInt(scanner.nextLine()) - 1;
                if (idx < 0 || idx >= cursoRepo.listaCursos.size()) {
                    System.out.println("Índice inválido!");
                    return;
                }
                cursoSelecionado = cursoRepo.listaCursos.get(idx);
            }

            if (cursoSelecionado.getListaAlteracaoPPC() == null) {
                cursoSelecionado.setListaAlteracaoPPC(new java.util.ArrayList<>());
            }

            System.out.print("Versão do PPC (nova): ");
            String novaVersao = scanner.nextLine();
            System.out.print("Carga horária (numero): ");
            Integer novaCH = Integer.parseInt(scanner.nextLine());

            Papel papelAdmin = new Papel("Admin");
            Administrador admin = new Administrador(0, "Administrador", "admin@local", "admin", papelAdmin, true, RolesUsuario.ADMINISTRADOR);

            administradorService.cadastrarPPC(cursoSelecionado, novaVersao, novaCH, admin);
            System.out.println("✓ PPC cadastrado/atualizado com sucesso para o curso: " + cursoSelecionado.getNome());

        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar PPC: " + e.getMessage());
        }
    }

    private static void cadastrarPPCCoordenador(Scanner scanner) {
        System.out.println("\n--- Cadastrar/Atualizar PPC (Coordenador) ---");

        try {
            if (docenteRepo.listaDocentes.isEmpty()) {
                System.out.println("Nenhum docente cadastrado. Cadastre um docente primeiro.");
                return;
            }

            listarDocentes();
            System.out.print("Selecione o docente coordenador (número): ");
            int dIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (dIdx < 0 || dIdx >= docenteRepo.listaDocentes.size()) {
                System.out.println("Índice inválido!");
                return;
            }
            Docente coordenador = docenteRepo.listaDocentes.get(dIdx);

            // Escolher curso
            System.out.println("Cursos disponíveis:");
            for (int i = 0; i < cursoRepo.listaCursos.size(); i++) {
                Curso c = cursoRepo.listaCursos.get(i);
                System.out.printf("%d) %s (Código: %d, CH: %d, Versão: %s)\n", i+1, c.getNome(), c.getCodigo(), c.getCargaHoraria(), c.getVersaoPPC());
            }
            System.out.print("Selecione o curso (número): ");
            int cIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (cIdx < 0 || cIdx >= cursoRepo.listaCursos.size()) {
                System.out.println("Índice inválido!");
                return;
            }
            Curso curso = cursoRepo.listaCursos.get(cIdx);
            if (curso.getListaAlteracaoPPC() == null) curso.setListaAlteracaoPPC(new java.util.ArrayList<>());

            System.out.print("Versão do PPC (nova): ");
            String versao = scanner.nextLine();
            System.out.print("Carga horária (numero): ");
            Integer ch = Integer.parseInt(scanner.nextLine());

            coordenadorService.cadastrarPPC(curso, versao, ch, coordenador);
            System.out.println("✓ PPC cadastrado/atualizado com sucesso pelo coordenador: " + coordenador.getNome());

        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private static void alterarPermissaoComoAdministrador(Scanner scanner) {
        System.out.println("\n--- Alterar Permissão (Administrador) ---");

        try {
            System.out.println("1 - Alterar permissão de Discente");
            System.out.println("2 - Alterar permissão de Docente");
            System.out.print("Escolha: ");
            String opt = scanner.nextLine().trim();
            Usuario alvo = null;

            if (opt.equals("1")) {
                listarDiscentes();
                System.out.print("Selecione o discente (número): ");
                int idx = Integer.parseInt(scanner.nextLine()) - 1;
                if (idx < 0 || idx >= discenteRepo.listaDiscente.size()) {
                    System.out.println("Índice inválido!");
                    return;
                }
                alvo = discenteRepo.listaDiscente.get(idx);
            } else if (opt.equals("2")) {
                listarDocentes();
                System.out.print("Selecione o docente (número): ");
                int idx = Integer.parseInt(scanner.nextLine()) - 1;
                if (idx < 0 || idx >= docenteRepo.listaDocentes.size()) {
                    System.out.println("Índice inválido!");
                    return;
                }
                alvo = docenteRepo.listaDocentes.get(idx);
            } else {
                System.out.println("Opção inválida.");
                return;
            }

            System.out.println("Papéis disponíveis:");
            RolesUsuario[] valores = RolesUsuario.values();
            for (int i = 0; i < valores.length; i++) {
                System.out.printf("%d) %s\n", i+1, valores[i]);
            }
            System.out.print("Selecione o papel (número): ");
            int rIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (rIdx < 0 || rIdx >= valores.length) {
                System.out.println("Índice inválido!");
                return;
            }

            RolesUsuario novoRole = valores[rIdx];
            usuarioService.mudarPermissao(alvo, novoRole);
            System.out.println("✓ Permissão alterada para " + novoRole + " no usuário " + alvo.getNome());

        } catch (Exception e) {
            System.out.println("❌ Erro ao alterar permissão: " + e.getMessage());
        }
    }

    private static void inicializarServicos() {
        discenteRepo = new DiscenteRepository();
        docenteRepo = new DocenteRepository();
        oportunidadeRepo = new OportunidadeRepository();
        inscricaoRepo = new InscricaoRepository();
        alteracaoRepo = new AlteracaoPermissaoRepository();
        cursoRepo = new CursoRepository();
        solicitacaoRepo = new SolicitacaoOportunidadeRepository(new java.util.ArrayList<>());

        oportunidadesService = new OportunidadesService(oportunidadeRepo);
        inscricaoService = new InscricaoService(inscricaoRepo);
        certificadoService = new CertificadoService(inscricaoRepo);
        docenteService = new DocenteService(oportunidadesService);
        usuarioService = new UsuarioService(alteracaoRepo);
        discenteService = new DiscenteService(discenteRepo, usuarioService);
        administradorService = new AdministradorService(usuarioService, docenteRepo);
        coordenadorService = new CoordenadorService(solicitacaoRepo);
        aproveitamentoService = new AproveitamentoService();
        grupoService = new GrupoService();
    }

    public static void menuSimulacaoManual(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            exibirMenuPrincipal();
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();
            
            switch (opcao) {
                case "1":
                    menuGerenciamentoPerfis(scanner);
                    break;
                case "2":
                    menuCriacaoOportunidade(scanner);
                    break;
                case "3":
                    menuAprovacaoOportunidade(scanner);
                    break;
                case "4":
                    menuInscricoeEParticipantes(scanner);
                    break;
                case "5":
                    menuGestaoAlternativas(scanner);
                    break;
                case "6":
                    menuRelatorios(scanner);
                    break;
                case "7":
                    menuAdministrador(scanner);
                    break;
                case "0":
                    System.out.println("\n✓ Encerrando simulação...");
                    continuar = false;
                    break;
                default:
                    System.out.println("\n❌ Opção inválida!");
            }
        }
    }
    
    private static void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║          MENU PRINCIPAL - SIMULAÇÃO INTERATIVA          ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println("1 - Gerenciar Perfis (Discentes, Docentes, Admin)");
        System.out.println("2 - Criar e Submeter Oportunidades (RF011)");
        System.out.println("3 - Aprovar/Reprovar Oportunidades (RF012)");
        System.out.println("4 - Inscrições e Participantes (RF015, RF016, RF017)");
        System.out.println("5 - Encerramento e Certificados (RF019)");
        System.out.println("6 - Relatórios e Consultas");
        System.out.println("7 - Menu Administrador (ações restritas)");
        System.out.println("0 - Sair da Simulação\n");
    }
    
    private static void menuGerenciamentoPerfis(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("║        MENU - GERENCIAR PERFIS E USUÁRIOS              ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("1 - Cadastrar Novo Discente");
            System.out.println("2 - Listar Discentes");
            System.out.println("3 - Listar Docentes");
            System.out.println("4 - Alterar Senha de Usuário");
            System.out.println("5 - Cadastrar/Atualizar PPC (Coordenador)");
            System.out.println("0 - Voltar ao Menu Principal\n");
            System.out.print("Escolha: ");
            
            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1":
                    cadastrarDiscente(scanner);
                    break;
                case "2":
                    listarDiscentes();
                    break;
                case "3":
                    listarDocentes();
                    break;
                case "4":
                    alterarSenhaUsuario(scanner);
                    break;
                case "5":
                    cadastrarPPCCoordenador(scanner);
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }
    
    private static void cadastrarDiscente(Scanner scanner) {
        System.out.println("\n--- Cadastro de Discente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        
        try {
            Discente discente = discenteService.autocadastroDiscente(nome, email, senha, matricula);
            if (discente != null) {
                System.out.println("✓ Discente cadastrado com sucesso!");
                System.out.println("  ID: " + discente.getId() + ", Nome: " + discente.getNome());
            } else {
                System.out.println("❌ Falha no cadastro - verifique a matrícula.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar discente: " + e.getMessage());
        }
    }
    
    private static void cadastrarDocente(Scanner scanner) {
        System.out.println("\n--- Cadastro de Docente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("SIAPE: ");
        String siape = scanner.nextLine();
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        
        try {
            Docente docente = administradorService.cadastroDocente(
                nome, email, senha, siape, departamento, RolesUsuario.DOCENTE
            );
            System.out.println("✓ Docente cadastrado com sucesso!");
            System.out.println("  ID: " + docente.getId() + ", Nome: " + docente.getNome());
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar docente: " + e.getMessage());
        }
    }
    
    private static void listarDiscentes() {
        System.out.println("\n--- Discentes Cadastrados ---");
        if (discenteRepo.listaDiscente.isEmpty()) {
            System.out.println("Nenhum discente cadastrado.");
        } else {
            for (int i = 0; i < discenteRepo.listaDiscente.size(); i++) {
                Discente d = discenteRepo.listaDiscente.get(i);
                System.out.printf("%d) %s (Matrícula: %s, Email: %s)\n", i+1, d.getNome(), d.getMatricula(), d.getEmail());
            }
        }
    }
    
    private static void listarDocentes() {
        System.out.println("\n--- Docentes Cadastrados ---");
        if (docenteRepo.listaDocentes.isEmpty()) {
            System.out.println("Nenhum docente cadastrado.");
        } else {
            for (int i = 0; i < docenteRepo.listaDocentes.size(); i++) {
                Docente d = docenteRepo.listaDocentes.get(i);
                System.out.printf("%d) %s (SIAPE: %s, Email: %s)\n", i+1, d.getNome(), d.getSiape(), d.getEmail());
            }
        }
    }
    
    private static void alterarSenhaUsuario(Scanner scanner) {
        System.out.println("\n--- Alterar Senha ---");
        listarDiscentes();
        System.out.print("Selecione o número do discente: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice >= 0 && indice < discenteRepo.listaDiscente.size()) {
                Discente discente = discenteRepo.listaDiscente.get(indice);
                System.out.print("Nova senha: ");
                String novaSenha = scanner.nextLine();
                usuarioService.mudarSenha(discente, novaSenha);
                System.out.println("✓ Senha alterada com sucesso!");
            } else {
                System.out.println("❌ Índice inválido!");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    private static void menuCriacaoOportunidade(Scanner scanner) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║    CRIAR E SUBMETER OPORTUNIDADE (RF011)               ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        if (docenteRepo.listaDocentes.isEmpty()) {
            System.out.println("❌ Cadastre um docente primeiro!");
            return;
        }
        
        listarDocentes();
        System.out.print("Selecione o docente responsável (número): ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice < 0 || indice >= docenteRepo.listaDocentes.size()) {
                System.out.println("❌ Índice inválido!");
                return;
            }
            
            Docente docenteResponsavel = docenteRepo.listaDocentes.get(indice);
            
            System.out.print("Título da Oportunidade: ");
            String titulo = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Tipo (EVENTO, CURSO, WORKSHOP, PROGRAMA, PRESTACAO_SERVICO): ");
            String tipoStr = scanner.nextLine().toUpperCase();
            TiposOportunidade tipo = TiposOportunidade.valueOf(tipoStr);
            
            System.out.print("Modalidade (PRESENCIAL, HIBRIDO, ONLINE): ");
            String modalidadeStr = scanner.nextLine().toUpperCase();
            TiposModalidade modalidade = TiposModalidade.valueOf(modalidadeStr);
            
            System.out.print("Carga Horária: ");
            int cargaHoraria = Integer.parseInt(scanner.nextLine());
            System.out.print("Número de Vagas: ");
            int vagas = Integer.parseInt(scanner.nextLine());
            
            LocalDateTime agora = LocalDateTime.now();
            LocalDateTime inicio = agora.plusDays(7);
            LocalDateTime fim = inicio.plusDays(2);
            LocalDateTime inicioInscricoes = agora.plusDays(1);
            LocalDateTime fimInscricoes = agora.plusDays(6);
            
            Oportunidade oportunidade = oportunidadesService.criarOportunidade(
                titulo, descricao, tipo, modalidade, cargaHoraria, vagas,
                inicio, fim, inicioInscricoes, fimInscricoes,
                docenteResponsavel, docenteResponsavel
            );
            
            System.out.println("\n✓ Oportunidade criada em RASCUNHO!");
            System.out.println("  Título: " + oportunidade.getTitulo());
            
            System.out.print("\nDeseja submeter para aprovação agora? (S/N): ");
            if (scanner.nextLine().toUpperCase().equals("S")) {
                oportunidadesService.submeterParaAprovacao(oportunidade);
                System.out.println("✓ Oportunidade submetida para AGUARDANDO_APROVACAO!");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    private static void menuAprovacaoOportunidade(Scanner scanner) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║    APROVAR/REPROVAR OPORTUNIDADES (RF012)              ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        List<Oportunidade> todasOps = oportunidadesService.listarTodas();
        List<Oportunidade> aguardando = new java.util.ArrayList<>();
        
        for (Oportunidade op : todasOps) {
            if (op.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO) {
                aguardando.add(op);
            }
        }
        
        if (aguardando.isEmpty()) {
            System.out.println("Nenhuma oportunidade aguardando aprovação.");
            return;
        }
        
        System.out.println("\nOportunidades aguardando aprovação:");
        for (int i = 0; i < aguardando.size(); i++) {
            Oportunidade op = aguardando.get(i);
            System.out.printf("%d) %s (Status: %s)\n", i+1, op.getTitulo(), op.getStatus());
        }
        
        System.out.print("Selecione a oportunidade (número): ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice < 0 || indice >= aguardando.size()) {
                System.out.println("❌ Índice inválido!");
                return;
            }
            
            Oportunidade oportunidade = aguardando.get(indice);
            listarDocentes();
            System.out.print("Selecione o docente aprovador (número): ");
            int docIndice = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (docIndice < 0 || docIndice >= docenteRepo.listaDocentes.size()) {
                System.out.println("❌ Índice inválido!");
                return;
            }
            
            Docente docente = docenteRepo.listaDocentes.get(docIndice);
            
            System.out.println("\n1 - Aprovar");
            System.out.println("2 - Reprovar");
            System.out.print("Ação: ");
            String acao = scanner.nextLine();
            
            if (acao.equals("1")) {
                oportunidadesService.aprovarOportunidade(oportunidade, docente);
                System.out.println("✓ Oportunidade APROVADA!");
            } else if (acao.equals("2")) {
                System.out.print("Motivo da reprovação: ");
                String motivo = scanner.nextLine();
                oportunidadesService.reprovarOportunidade(oportunidade, docente, motivo);
                System.out.println("✓ Oportunidade REPROVADA!");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    private static void menuInscricoeEParticipantes(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("║      INSCRIÇÕES E PARTICIPANTES (RF015-RF017)         ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("1 - Inscrever Discente em Oportunidade (RF015)");
            System.out.println("2 - Aprovar Inscrição (RF015)");
            System.out.println("3 - Rejeitar Inscrição (RF015)");
            System.out.println("4 - Cancelar Inscrição (RF016)");
            System.out.println("5 - Listar Inscrições");
            System.out.println("0 - Voltar\n");
            System.out.print("Escolha: ");
            
            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1":
                    inscreverDiscenteOportunidade(scanner);
                    break;
                case "2":
                    aprovarInscricao(scanner);
                    break;
                case "3":
                    rejeitarInscricao(scanner);
                    break;
                case "4":
                    cancelarInscricao(scanner);
                    break;
                case "5":
                    listarInscricoes(scanner);
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }
    
    private static void inscreverDiscenteOportunidade(Scanner scanner) {
        System.out.println("\n--- Inscrever Discente em Oportunidade ---");
        
        List<Oportunidade> abertas = oportunidadesService.listarOportunidadesAbertas();
        if (abertas.isEmpty()) {
            System.out.println("Nenhuma oportunidade com inscrições abertas.");
            return;
        }
        
        System.out.println("Oportunidades com inscrições abertas:");
        for (int i = 0; i < abertas.size(); i++) {
            System.out.printf("%d) %s (Vagas: %d)\n", i+1, abertas.get(i).getTitulo(), abertas.get(i).getVagas());
        }
        
        System.out.print("Selecione (número): ");
        try {
            int opIndice = Integer.parseInt(scanner.nextLine()) - 1;
            if (opIndice < 0 || opIndice >= abertas.size()) {
                System.out.println("❌ Índice inválido!");
                return;
            }
            
            Oportunidade oportunidade = abertas.get(opIndice);
            
            listarDiscentes();
            System.out.print("Selecione o discente (número): ");
            int disIndice = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (disIndice < 0 || disIndice >= discenteRepo.listaDiscente.size()) {
                System.out.println("❌ Índice inválido!");
                return;
            }
            
            Discente discente = discenteRepo.listaDiscente.get(disIndice);
            System.out.print("Motivação da inscrição: ");
            String motivacao = scanner.nextLine();
            
            Inscricao inscricao = inscricaoService.criarInscricao(oportunidade, discente, motivacao);
            System.out.println("✓ Inscrição criada com status PENDENTE!");
            
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    private static void aprovarInscricao(Scanner scanner) {
        System.out.println("\n--- Aprovar Inscrição ---");
        listarInscricoesPendentes();
        System.out.print("Selecione a inscrição (número): ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            List<Inscricao> todas = inscricaoRepo.listarTodas();
            List<Inscricao> pendentes = new java.util.ArrayList<>();
            for (Inscricao i : todas) {
                if (i.getStatus() == StatusInscricao.PENDENTE) {
                    pendentes.add(i);
                }
            }
            
            if (indice < 0 || indice >= pendentes.size()) {
                System.out.println("❌ Índice inválido!");
                return;
            }
            
            Inscricao inscricao = pendentes.get(indice);
            inscricaoService.aprovarInscricao(inscricao, inscricao.getOportunidade());
            System.out.println("✓ Inscrição APROVADA!");
            
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    private static void rejeitarInscricao(Scanner scanner) {
        System.out.println("\n--- Rejeitar Inscrição ---");
        listarInscricoesPendentes();
        System.out.print("Selecione a inscrição (número): ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            List<Inscricao> todas = inscricaoRepo.listarTodas();
            List<Inscricao> pendentes = new java.util.ArrayList<>();
            for (Inscricao i : todas) {
                if (i.getStatus() == StatusInscricao.PENDENTE) {
                    pendentes.add(i);
                }
            }
            
            if (indice < 0 || indice >= pendentes.size()) {
                System.out.println("❌ Índice inválido!");
                return;
            }
            
            Inscricao inscricao = pendentes.get(indice);
            inscricaoService.rejeitarInscricao(inscricao);
            System.out.println("✓ Inscrição REJEITADA!");
            
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    private static void cancelarInscricao(Scanner scanner) {
        System.out.println("\n--- Cancelar Inscrição (RF016) ---");
        List<Inscricao> todas = inscricaoRepo.listarTodas();
        List<Inscricao> cancelaveis = new java.util.ArrayList<>();
        
        for (Inscricao i : todas) {
            if (i.getStatus() != StatusInscricao.CANCELADO && i.getStatus() != StatusInscricao.SUBSTITUIDO) {
                cancelaveis.add(i);
            }
        }
        
        if (cancelaveis.isEmpty()) {
            System.out.println("Nenhuma inscrição disponível para cancelamento.");
            return;
        }
        
        System.out.println("Inscrições disponíveis:");
        for (int i = 0; i < cancelaveis.size(); i++) {
            Inscricao insc = cancelaveis.get(i);
            System.out.printf("%d) %s em %s (Status: %s)\n", i+1, insc.getDiscente().getNome(), 
                insc.getOportunidade().getTitulo(), insc.getStatus());
        }
        
        System.out.print("Selecione (número): ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice < 0 || indice >= cancelaveis.size()) {
                System.out.println("❌ Índice inválido!");
                return;
            }
            
            Inscricao inscricao = cancelaveis.get(indice);
            inscricaoService.cancelarInscricao(inscricao);
            System.out.println("✓ Inscrição CANCELADA!");
            
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    private static void listarInscricoesPendentes() {
        System.out.println("\nInscrições Pendentes:");
        List<Inscricao> todas = inscricaoRepo.listarTodas();
        List<Inscricao> pendentes = new java.util.ArrayList<>();
        
        for (Inscricao i : todas) {
            if (i.getStatus() == StatusInscricao.PENDENTE) {
                pendentes.add(i);
            }
        }
        
        if (pendentes.isEmpty()) {
            System.out.println("Nenhuma inscrição pendente.");
        } else {
            for (int i = 0; i < pendentes.size(); i++) {
                Inscricao insc = pendentes.get(i);
                System.out.printf("%d) %s em %s\n", i+1, insc.getDiscente().getNome(), 
                    insc.getOportunidade().getTitulo());
            }
        }
    }
    
    private static void listarInscricoes(Scanner scanner) {
        System.out.println("\n--- Listar Inscrições ---");
        List<Inscricao> todas = inscricaoRepo.listarTodas();
        
        if (todas.isEmpty()) {
            System.out.println("Nenhuma inscrição registrada.");
            return;
        }
        
        System.out.println("Todas as Inscrições:");
        for (int i = 0; i < todas.size(); i++) {
            Inscricao insc = todas.get(i);
            System.out.printf("%d) %s em %s - Status: %s\n", 
                i+1, insc.getDiscente().getNome(), 
                insc.getOportunidade().getTitulo(), 
                insc.getStatus());
        }
    }
    
    private static void menuGestaoAlternativas(Scanner scanner) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║        GESTÃO DE ALTERNATIVAS (RF017, RF019)           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println("1 - Substitua Participante (RF017)");
        System.out.println("2 - Encerrar Oportunidade e Gerar Certificados (RF019)");
        System.out.print("Escolha: ");
        
        String opcao = scanner.nextLine().trim();
        switch (opcao) {
            case "1":
                substituirParticipante(scanner);
                break;
            case "2":
                encerrarOportunidadeGerarCertificados(scanner);
                break;
            default:
                System.out.println("❌ Opção inválida!");
        }
    }
    
    private static void substituirParticipante(Scanner scanner) {
        System.out.println("\n--- Substituir Participante (RF017) ---");
        System.out.println("Funcionalidade disponível através da API InscricaoService.substituirParticipante()");
    }
    
    private static void encerrarOportunidadeGerarCertificados(Scanner scanner) {
        System.out.println("\n--- Encerrar Oportunidade e Gerar Certificados (RF019) ---");
        
        List<Oportunidade> todasOps = oportunidadesService.listarTodas();
        List<Oportunidade> ativas = new java.util.ArrayList<>();
        
        for (Oportunidade op : todasOps) {
            if (op.getStatus() == StatusOportunidade.EM_EXECUCAO || 
                op.getStatus() == StatusOportunidade.APROVADA || 
                op.getStatus() == StatusOportunidade.EM_INSCRICOES) {
                ativas.add(op);
            }
        }
        
        if (ativas.isEmpty()) {
            System.out.println("Nenhuma oportunidade pode ser encerrada.");
            return;
        }
        
        System.out.println("Oportunidades que podem ser encerradas:");
        for (int i = 0; i < ativas.size(); i++) {
            System.out.printf("%d) %s (Status: %s)\n", i+1, ativas.get(i).getTitulo(), ativas.get(i).getStatus());
        }
        
        System.out.print("Selecione (número): ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice < 0 || indice >= ativas.size()) {
                System.out.println("❌ Índice inválido!");
                return;
            }
            
            Oportunidade oportunidade = ativas.get(indice);
            List<Certificado> certificados = certificadoService.encerrarEGerarCertificados(oportunidade);
            System.out.println("✓ Certificados gerados: " + certificados.size());
            
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    private static void menuRelatorios(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("║           MENU - RELATÓRIOS E CONSULTAS               ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("1 - Total de Oportunidades por Status");
            System.out.println("2 - Total de Inscrições por Status");
            System.out.println("3 - Certificados Emitidos");
            System.out.println("4 - Log de Alterações de Permissões");
            System.out.println("0 - Voltar\n");
            System.out.print("Escolha: ");
            
            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1":
                    relatorioOportunidades();
                    break;
                case "2":
                    relatorioInscricoes();
                    break;
                case "3":
                    relatorioCertificados();
                    break;
                case "4":
                    relatorioAlteracoes();
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }
    
    private static void relatorioOportunidades() {
        System.out.println("\n--- Relatório de Oportunidades ---");
        List<Oportunidade> todas = oportunidadesService.listarTodas();
        
        if (todas.isEmpty()) {
            System.out.println("Nenhuma oportunidade registrada.");
            return;
        }
        
        java.util.Map<StatusOportunidade, Integer> contagem = new java.util.HashMap<>();
        for (Oportunidade op : todas) {
            contagem.put(op.getStatus(), contagem.getOrDefault(op.getStatus(), 0) + 1);
        }
        
        System.out.println("Total de Oportunidades: " + todas.size());
        for (StatusOportunidade status : contagem.keySet()) {
            System.out.println("  " + status + ": " + contagem.get(status));
        }
    }
    
    private static void relatorioInscricoes() {
        System.out.println("\n--- Relatório de Inscrições ---");
        List<Inscricao> todas = inscricaoRepo.listarTodas();
        
        if (todas.isEmpty()) {
            System.out.println("Nenhuma inscrição registrada.");
            return;
        }
        
        java.util.Map<StatusInscricao, Integer> contagem = new java.util.HashMap<>();
        for (Inscricao insc : todas) {
            contagem.put(insc.getStatus(), contagem.getOrDefault(insc.getStatus(), 0) + 1);
        }
        
        System.out.println("Total de Inscrições: " + todas.size());
        for (StatusInscricao status : contagem.keySet()) {
            System.out.println("  " + status + ": " + contagem.get(status));
        }
    }
    
    private static void relatorioCertificados() {
        System.out.println("\n--- Relatório de Certificados ---");
        List<Certificado> certs = certificadoService.listarCertificados();
        System.out.println("Total de Certificados Emitidos: " + certs.size());
        
        if (!certs.isEmpty()) {
            System.out.println("\nDetalhes:");
            for (Certificado cert : certs) {
                System.out.printf("  - %s | Hash: %s | Carga: %dh\n", 
                    cert.getDiscente().getNome(), cert.getHash(), cert.getCargaHoraria());
            }
        }
    }
    
    private static void relatorioAlteracoes() {
        System.out.println("\n--- Relatório de Alterações de Permissões ---");
        List<AlteracaoPermissao> alteracoes = alteracaoRepo.getListaAlteracaoPermissao();
        System.out.println("Total de Alterações: " + alteracoes.size());
        
        if (!alteracoes.isEmpty()) {
            System.out.println("\nDetalhes:");
            for (AlteracaoPermissao alt : alteracoes) {
                System.out.printf("  - Usuário: %s | Operação: %s | Data: %s\n", 
                    alt.getUsuario().getNome(), alt.getTipoOperacao(), alt.getData());
            }
        }
    }

        private static void simularSistema() {
            Scanner scanner = new Scanner(System.in);

            DiscenteRepository discenteRepo = new DiscenteRepository();
            DocenteRepository docenteRepo = new DocenteRepository();
            CursoRepository cursoRepo = new CursoRepository();
            OportunidadeRepository oportunidadeRepo = new OportunidadeRepository();
            AlteracaoPermissaoRepository alteracaoRepositorio = new AlteracaoPermissaoRepository();
            OportunidadesService oportunidadesService = new OportunidadesService(oportunidadeRepo);
            DocenteService docenteService = new DocenteService(oportunidadesService);
            UsuarioService usuarioService = new UsuarioService( alteracaoRepositorio);
            GrupoService grupoService = new GrupoService();
            AproveitamentoService aproveitamentoService = new AproveitamentoService();
            InscricaoService inscricaoService = new InscricaoService(inscricaoRepo);
            DiscenteService discenteService = new DiscenteService(discenteRepo, usuarioService);

            java.util.List<Inscricao> inscricoesSim = new java.util.ArrayList<>();

            while (true) {
                System.out.println("\n=== SIMULAÇÃO MANUAL ===");
                System.out.println("1) Autocadastro discente");
                System.out.println("2) Listar Discentes");
                System.out.println("3) Listar Docentes");
                System.out.println("4) Listar Cursos");
                System.out.println("5) Listar Oportunidades");
                System.out.println("6) Criar / Publicar Oportunidade");
                System.out.println("7) Inscrever Discente em Oportunidade");
                System.out.println("8) Listar Inscrições (simulação)");
                System.out.println("9) Fechar Inscrições de uma Oportunidade");
                System.out.println("10) Alterar senha de um usuário (simulação)");
                System.out.println("11) Listar log de alterações");
                System.out.println("0) Sair da simulação\n");
                System.out.print("Escolha: ");

                String opt = scanner.nextLine().trim();
                if (opt.isEmpty()) opt = scanner.nextLine().trim();

                switch (opt) {
                    case "1":
                        String nome = lerStringValida(scanner, "insira o seu nome: ");
                        String email = lerStringValida(scanner, "insira o seu email: ");
                        String senha = lerStringValida(scanner, "insira a senha: ");
                        String matricula = lerStringValida(scanner, "insira a sua matricula: ");

                        Discente novoDiscente = discenteService.autocadastroDiscente(nome, email, senha, matricula);
                        discenteRepo.listaDiscente.add(novoDiscente);
                        break;
                    case "2":
                        System.out.println("Discentes:");
                        for (int i = 0; i < discenteRepo.listaDiscente.size(); i++) {
                            Discente d = discenteRepo.listaDiscente.get(i);
                            System.out.println(i + ") " + d.getNome() + " - " + d.getMatricula());
                        }
                        break;
                    case "3":
                        System.out.println("Docentes:");
                        for (int i = 0; i < docenteRepo.listaDocentes.size(); i++) {
                            Docente d = docenteRepo.listaDocentes.get(i);
                            System.out.println(i + ") " + d.getNome() + " - " + d.getSiape());
                        }
                        break;
                    case "4":
                        System.out.println("Cursos:");
                        for (int i = 0; i < cursoRepo.listaCursos.size(); i++) {
                            Curso c = cursoRepo.listaCursos.get(i);
                            System.out.println(i + ") " + c.getNome() + " (" + c.getCodigo() + ")");
                        }
                        break;
                    case "5":
                        System.out.println("Oportunidades:");
                        for (int i = 0; i < oportunidadesService.listarOportunidadesAbertas().size(); i++) {
                            Oportunidade o = oportunidadesService.listarOportunidadesAbertas().get(i);
                            System.out.println(i + ") " + o.getTitulo() + " - Vagas: " + o.getVagas() + " - Status: " + o.getStatus().toString());
                        }
                        break;
                    case "6":
                        try {
                            System.out.print("Título: ");
                            String titulo = scanner.nextLine();
                            System.out.print("Descrição: ");
                            String descricao = scanner.nextLine();
                            System.out.print("Carga horária (número): ");
                            Integer ch = Integer.parseInt(scanner.nextLine());
                            System.out.print("Vagas (número): ");
                            Integer vagas = Integer.parseInt(scanner.nextLine());
                            System.out.println("Tipo (1=PROJETO,2=EVENTO,3=CURSO,4=OFICINA): ");
                            int tt = Integer.parseInt(scanner.nextLine());
                            TiposOportunidade tipo = TiposOportunidade.PROJETO;
                            if (tt == 2) tipo = TiposOportunidade.EVENTO;
                            else if (tt == 3) tipo = TiposOportunidade.CURSO;
                            else if (tt == 4) tipo = TiposOportunidade.OFICINA;

                            System.out.println("Modalidade (1=PRESENCIAL,2=REMOTO,3=HIBRIDO): ");
                            int mm = Integer.parseInt(scanner.nextLine());
                            TiposModalidade mod = TiposModalidade.PRESENCIAL;
                            if (mm == 2) mod = TiposModalidade.REMOTO;
                            else if (mm == 3) mod = TiposModalidade.HIBRIDO;

                            // status default PUBLICADA
                            StatusOportunidade status = StatusOportunidade.AGUARDANDO_APROVACAO;

                            // datas: inicio agora, fim em X dias
                            System.out.print("Duração em dias (ex: 30): ");
                            int dias = Integer.parseInt(scanner.nextLine());

                            System.out.println("Quanto tempo ate finalizar inscricoes(em dias): ");
                            int diasInscricao = Integer.parseInt(scanner.nextLine());

                            Usuario autor = new Usuario(999, "Prof Simulador", "sim@local", "sim", new Papel("Docente"), true, null);
                            Docente docente = docenteRepo.listaDocentes.get(0);

                            Oportunidade criada = oportunidadesService.criarOportunidade(
                                    titulo,
                                    descricao,
                                    tipo,
                                    mod,
                                    ch,
                                    vagas,
                                    LocalDateTime.now(),
                                    LocalDateTime.now().plusDays(dias),
                                    LocalDateTime.now(),
                                    LocalDateTime.now().plusDays(diasInscricao),
                                    autor,
                                    docente
                            );
                            System.out.println("Oportunidade publicada: " + criada.getTitulo());
                        } catch (Exception e) {
                            System.out.println("Erro ao criar oportunidade: " + e.getMessage());
                        }
                        break;
                    case "7":
                        try {
                            System.out.println("Selecione Discente por índice:");
                            for (int i = 0; i < discenteRepo.listaDiscente.size(); i++) {
                                System.out.println(i + ") " + discenteRepo.listaDiscente.get(i).getNome());
                            }
                            int di = Integer.parseInt(scanner.nextLine());
                            Discente disc = discenteRepo.listaDiscente.get(di);

                            System.out.println("Selecione Oportunidade por índice:");
                            for (int i = 0; i < oportunidadesService.listarOportunidadesAbertas().size(); i++) {
                                System.out.println(i + ") " + oportunidadesService.listarOportunidadesAbertas().get(i).getTitulo());
                            }
                            int oi = Integer.parseInt(scanner.nextLine());
                            Oportunidade opp = oportunidadesService.listarOportunidadesAbertas().get(oi);

                            System.out.print("Motivação: ");
                            String motivo = scanner.nextLine();

                            inscricaoService.criarInscricao(opp, disc, motivo);
                            System.out.println("Inscrição criada (simulação) para " + disc.getNome() + " na oportunidade '" + opp.getTitulo() + "'.");
                        } catch (Exception e) {
                            System.out.println("Erro ao inscrever: " + e.getMessage());
                        }
                        break;
                    case "8":
                        System.out.println("Inscrições (simulação):");
                        for (int i = 0; i < inscricaoRepo.listarTodas().size(); i++) {
                            Inscricao ins = inscricaoRepo.listarTodas().get(i);
                            System.out.println(i + ") " + ins.getDiscente().getNome() + " -> " + ins.getOportunidade().getTitulo() + " | Motivo: " + ins.getMotivacao());
                        }
                        break;
                    case "9":
                        try {
                            System.out.println("Selecione Oportunidade para fechar inscrições:");
                            for (int i = 0; i < oportunidadeRepo.listarTodas().size(); i++) {
                                System.out.println(i + ") " + oportunidadeRepo.listarTodas().get(i).getTitulo());
                            }
                            int idx = Integer.parseInt(scanner.nextLine());
                            Oportunidade o = oportunidadeRepo.listarTodas().get(idx);
                            oportunidadesService.fecharInscricoes(o);
                            System.out.println("Inscrições fechadas para: " + o.getTitulo());
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                    case "10":
                        try {
                            System.out.println("Selecione Discente por índice para alterar senha:");
                            for (int i = 0; i < discenteRepo.listaDiscente.size(); i++) {
                                System.out.println(i + ") " + discenteRepo.listaDiscente.get(i).getNome());
                            }
                            int di2 = Integer.parseInt(scanner.nextLine());
                            Usuario u = discenteRepo.listaDiscente.get(di2);
                            System.out.print("Nova senha: ");
                            String ns = scanner.nextLine();
                            usuarioService.mudarSenha(u, ns);
                            System.out.println("Senha alterada para: " + u.getSenha());
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                    case "11":
                        System.out.println("Log de Alterações de Permissão:");
                        for (AlteracaoPermissao ap : alteracaoRepositorio.getListaAlteracaoPermissao())
                            System.out.println(ap.getTipoOperacao() + " - Usuário: " + ap.getUsuario().getNome() + " - Data: " + ap.getData());
                        break;

                    case "0":
                        System.out.println("Saindo da simulação manual.");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }

    public static void rodarTodosTestes() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║            EXECUÇÃO DOS TESTES AUTOMATIZADOS                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        System.out.println("\n--- Iniciando Testes Integrados (Completos) ---\n");
        try {
            IntegratedServiceTests.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("❌ Erro ao executar IntegratedServiceTests: " + t.getMessage());
            t.printStackTrace();
        }

        System.out.println("\n--- Executando Testes Específicos ---\n");
        
        try {
            System.out.println("\n▶ Executando DocenteServiceTest...");
            DocenteServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("⚠ Erro ao executar DocenteServiceTest: " + t.getMessage());
        }

        try {
            System.out.println("\n▶ Executando GrupoServiceTest...");
            GrupoServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("⚠ Erro ao executar GrupoServiceTest: " + t.getMessage());
        }

        try {
            System.out.println("\n▶ Executando InscricaoServiceTest...");
            InscricaoServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("⚠ Erro ao executar InscricaoServiceTest: " + t.getMessage());
        }

        try {
            System.out.println("\n▶ Executando OportunidadesServiceTest...");
            OportunidadesServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("⚠ Erro ao executar OportunidadesServiceTest: " + t.getMessage());
        }

        try {
            System.out.println("\n▶ Executando UsuarioServiceTest...");
            UsuarioServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("⚠ Erro ao executar UsuarioServiceTest: " + t.getMessage());
        }

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           EXECUÇÃO DOS TESTES FINALIZADA                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
    }
}