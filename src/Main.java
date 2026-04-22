import entity.*;
import entity.enums.*;
import repository.CursoRepository;
import repository.DiscenteRepository;
import repository.DocenteRepository;
import repository.OportunidadeRepository;
import services.*;
import test.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo ao ambiente de testes do sistema de oportunidades!\n");
        System.out.println("Escolha 1 para rodar os testes automatizados");
        System.out.println("Escolha 2 para simular o sistema manualmente");
        System.out.print("Informe a opção desejada: ");
        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                rodarTodosTestes();
                break;
            case 2:
                simularSistema();
                break;
            default:
                System.out.println("Opção inválida. Encerrando a execução.");
                break;
        }
    }

        private static void simularSistema() {
            Scanner scanner = new Scanner(System.in);

            DiscenteRepository discenteRepo = new DiscenteRepository();
            DocenteRepository docenteRepo = new DocenteRepository();
            CursoRepository cursoRepo = new CursoRepository();
            OportunidadeRepository oportunidadeRepo = new OportunidadeRepository();

            OportunidadesService oportunidadesService = new OportunidadesService(oportunidadeRepo);
            DocenteService docenteService = new DocenteService(oportunidadesService);
            UsuarioService usuarioService = new UsuarioService();
            GrupoService grupoService = new GrupoService();
            InscricaoService inscricaoService = new InscricaoService();

            java.util.List<Inscricao> inscricoesSim = new java.util.ArrayList<>();

            while (true) {
                System.out.println("\n=== SIMULAÇÃO MANUAL ===");
                System.out.println("1) Listar Discentes");
                System.out.println("2) Listar Docentes");
                System.out.println("3) Listar Cursos");
                System.out.println("4) Listar Oportunidades");
                System.out.println("5) Criar/ Publicar Oportunidade");
                System.out.println("6) Inscrever Discente em Oportunidade");
                System.out.println("7) Listar Inscrições (simulação)");
                System.out.println("8) Fechar Inscrições de uma Oportunidade");
                System.out.println("9) Alterar senha de um usuário (simulação)");
                System.out.println("0) Sair da simulação\n");
                System.out.print("Escolha: ");

                String opt = scanner.nextLine().trim();
                if (opt.isEmpty()) opt = scanner.nextLine().trim();

                switch (opt) {
                    case "1":
                        System.out.println("Discentes:");
                        for (int i = 0; i < discenteRepo.listaDiscente.size(); i++) {
                            Discente d = discenteRepo.listaDiscente.get(i);
                            System.out.println(i + ") " + d.getNome() + " - " + d.getMatricula());
                        }
                        break;
                    case "2":
                        System.out.println("Docentes:");
                        for (int i = 0; i < docenteRepo.listaDocentes.size(); i++) {
                            Docente d = docenteRepo.listaDocentes.get(i);
                            System.out.println(i + ") " + d.getNome() + " - " + d.getSiape());
                        }
                        break;
                    case "3":
                        System.out.println("Cursos:");
                        for (int i = 0; i < cursoRepo.listaCursos.size(); i++) {
                            Curso c = cursoRepo.listaCursos.get(i);
                            System.out.println(i + ") " + c.getNome() + " (" + c.getCodigo() + ")");
                        }
                        break;
                    case "4":
                        System.out.println("Oportunidades:");
                        for (int i = 0; i < oportunidadesService.listarOportunidadesAtivas().size(); i++) {
                            Oportunidade o = oportunidadesService.listarOportunidadesAtivas().get(i);
                            System.out.println(i + ") " + o.getTitulo() + " - Vagas: " + o.getVagas() + " - Status: " + o.getStatusOportunidade());
                        }
                        break;
                    case "5":
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
                            StatusOportunidade status = StatusOportunidade.PUBLICADA;

                            // datas: inicio agora, fim em X dias
                            System.out.print("Duração em dias (ex: 30): ");
                            int dias = Integer.parseInt(scanner.nextLine());

                            Usuario autor = new Usuario(999, "Prof Simulador", "sim@local", "sim", new Papel("Docente"), true);
                            Docente docente = docenteRepo.listaDocentes.get(0);

                            Oportunidade criada = oportunidadesService.publicar(titulo, descricao, tipo, mod, ch, vagas, status, java.time.LocalDateTime.now(), java.time.LocalDateTime.now().plusDays(dias), autor, docente);
                            System.out.println("Oportunidade publicada: " + criada.getTitulo());
                        } catch (Exception e) {
                            System.out.println("Erro ao criar oportunidade: " + e.getMessage());
                        }
                        break;
                    case "6":
                        try {
                            System.out.println("Selecione Discente por índice:");
                            for (int i = 0; i < discenteRepo.listaDiscente.size(); i++) {
                                System.out.println(i + ") " + discenteRepo.listaDiscente.get(i).getNome());
                            }
                            int di = Integer.parseInt(scanner.nextLine());
                            Discente disc = discenteRepo.listaDiscente.get(di);

                            System.out.println("Selecione Oportunidade por índice:");
                            for (int i = 0; i < oportunidadesService.listarOportunidadesAtivas().size(); i++) {
                                System.out.println(i + ") " + oportunidadesService.listarOportunidadesAtivas().get(i).getTitulo());
                            }
                            int oi = Integer.parseInt(scanner.nextLine());
                            Oportunidade opp = oportunidadesService.listarOportunidadesAtivas().get(oi);

                            System.out.print("Motivação: ");
                            String motivo = scanner.nextLine();

                            inscricaoService.criarInscricao(opp, disc, motivo);
                            System.out.println("Inscrição criada (simulação) para " + disc.getNome() + " na oportunidade '" + opp.getTitulo() + "'.");
                        } catch (Exception e) {
                            System.out.println("Erro ao inscrever: " + e.getMessage());
                        }
                        break;
                    case "7":
                        System.out.println("Inscrições (simulação):");
                        for (int i = 0; i < inscricaoService.getInscricao().size(); i++) {
                            Inscricao ins = inscricaoService.getInscricao().get(i);
                            System.out.println(i + ") " + ins.getDiscente().getNome() + " -> " + ins.getOportunidade().getTitulo() + " | Motivo: " + ins.getMotivacao());
                        }
                        break;
                    case "8":
                        try {
                            System.out.println("Selecione Oportunidade para fechar inscrições:");
                            for (int i = 0; i < oportunidadeRepo.listaOportunidades.size(); i++) {
                                System.out.println(i + ") " + oportunidadeRepo.listaOportunidades.get(i).getTitulo());
                            }
                            int idx = Integer.parseInt(scanner.nextLine());
                            Oportunidade o = oportunidadeRepo.listaOportunidades.get(idx);
                            oportunidadesService.fecharInscricoes(o);
                            System.out.println("Inscrições fechadas para: " + o.getTitulo());
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                    case "9":
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
                    case "0":
                        System.out.println("Saindo da simulação manual.");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }

    public static void rodarTodosTestes() {
        System.out.println("\nIniciando execução dos testes em arquivos separados...\n");
        try {
            DocenteServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("Erro ao executar DocenteServiceTest: " + t.getMessage());
        }

        try {
            GrupoServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("Erro ao executar GrupoServiceTest: " + t.getMessage());
        }

        try {
            InscricaoServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("Erro ao executar InscricaoServiceTest: " + t.getMessage());
        }

        try {
            OportunidadesServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("Erro ao executar OportunidadesServiceTest: " + t.getMessage());
        }

        try {
            UsuarioServiceTest.main(new String[0]);
        } catch (Throwable t) {
            System.out.println("Erro ao executar UsuarioServiceTest: " + t.getMessage());
        }

        System.out.println("\nExecução dos testes finalizada.\n");
    }
}