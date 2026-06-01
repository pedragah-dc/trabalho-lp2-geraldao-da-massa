package test;

import entity.*;
import entity.enums.*;
import repository.*;
import services.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Testes Integrados de Todos os Services
 * Demonstra o fluxo completo do sistema RF011 → RF019
 */
public class IntegratedServiceTests {

    private static DiscenteRepository discenteRepo;
    private static DocenteRepository docenteRepo;
    private static OportunidadeRepository oportunidadeRepo;
    private static InscricaoRepository inscricaoRepo;
    private static AlteracaoPermissaoRepository alteracaoRepo;

    private static OportunidadesService oportunidadesService;
    private static InscricaoService inscricaoService;
    private static CertificadoService certificadoService;
    private static DocenteService docenteService;
    private static DiscenteService discenteService;
    private static UsuarioService usuarioService;
    private static AdministradorService administradorService;
    private static AproveitamentoService aproveitamentoService;

    private static int testesPassados = 0;
    private static int testesFalhados = 0;

    public static void main(String[] args) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       TESTES INTEGRADOS - TODOS OS SERVICES               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        inicializarServicos();

        try {
            testesCadastroUsuarios();
            testesOportunidades();
            testesInscricoes();
            testesCertificados();
            testesAlteracaoPermissoes();
            testesAproveitamento();
        } catch (Exception e) {
            System.out.println("\n❌ ERRO CRÍTICO: " + e.getMessage());
            e.printStackTrace();
        }

        exibirResumoTestes();
    }

    private static void inicializarServicos() {
        discenteRepo = new DiscenteRepository();
        docenteRepo = new DocenteRepository();
        oportunidadeRepo = new OportunidadeRepository();
        inscricaoRepo = new InscricaoRepository();
        alteracaoRepo = new AlteracaoPermissaoRepository();

        oportunidadesService = new OportunidadesService(oportunidadeRepo);
        inscricaoService = new InscricaoService(inscricaoRepo);
        certificadoService = new CertificadoService(inscricaoRepo);
        docenteService = new DocenteService(oportunidadesService);
        usuarioService = new UsuarioService(alteracaoRepo);
        discenteService = new DiscenteService(discenteRepo, usuarioService);
        administradorService = new AdministradorService(usuarioService, docenteRepo);
        aproveitamentoService = new AproveitamentoService();
    }

    // ====================================================================
    // TESTES DE CADASTRO DE USUÁRIOS
    // ====================================================================
    private static void testesCadastroUsuarios() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TESTES: CADASTRO DE USUÁRIOS");
        System.out.println("=".repeat(60));

        testeCadastroDiscente();
        testeCadastroDocente();
        testeAlteracaoSenha();
    }

    private static void testeCadastroDiscente() {
        System.out.println("\n[TESTE 1] Cadastro de Discente");
        try {
            Discente discente = discenteService.autocadastroDiscente(
                "João Silva", "joao@email.com", "senha123", "2021001"
            );

            assert discente != null : "Discente não pode ser nulo";
            assert discente.getNome().equals("João Silva") : "Nome incorreto";
            assert discente.getMatricula().equals("2021001") : "Matrícula incorreta";

            System.out.println("✓ Discente cadastrado: " + discente.getNome());
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeCadastroDocente() {
        System.out.println("\n[TESTE 2] Cadastro de Docente");
        try {
            Docente docente = administradorService.cadastroDocente(
                "Prof. Maria", "maria@ufma.br", "prof123", "111222", "Computação", RolesUsuario.DOCENTE
            );

            assert docente != null : "Docente não pode ser nulo";
            assert docente.getSiape().equals("111222") : "SIAPE incorreto";
            assert docenteRepo.listaDocentes.size() > 0 : "Docente não foi adicionado ao repositório";

            System.out.println("✓ Docente cadastrado: " + docente.getNome() + " (SIAPE: " + docente.getSiape() + ")");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeAlteracaoSenha() {
        System.out.println("\n[TESTE 3] Alteração de Senha");
        try {
            Discente discente = discenteService.autocadastroDiscente(
                "Pedro Costa", "pedro@email.com", "senha456", "2021002"
            );

            boolean resultado = usuarioService.mudarSenha(discente, "novaSenha789");

            assert resultado : "Falha ao alterar senha";
            assert discente.getSenha().equals("novaSenha789") : "Senha não foi alterada";

            System.out.println("✓ Senha alterada com sucesso");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    // ====================================================================
    // TESTES DE OPORTUNIDADES (RF011, RF012)
    // ====================================================================
    private static void testesOportunidades() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TESTES: OPORTUNIDADES (RF011, RF012)");
        System.out.println("=".repeat(60));

        testeCriarOportunidade();
        testeSubmeterAprovacao();
        testeAprovarOportunidade();
        testeReprovarOportunidade();
        testeListarOportunidadesAbertas();
    }

    private static void testeCriarOportunidade() {
        System.out.println("\n[TESTE 4] Criar Oportunidade (RF011)");
        try {
            Docente docente = docenteRepo.listaDocentes.get(0);
            LocalDateTime agora = LocalDateTime.now();

            Oportunidade oportunidade = oportunidadesService.criarOportunidade(
                "Seminário de IA",
                "Palestras sobre Inteligência Artificial",
                TiposOportunidade.EVENTO,
                TiposModalidade.PRESENCIAL,
                8,
                50,
                agora.plusDays(10),
                agora.plusDays(11),
                agora.plusDays(1),
                agora.plusDays(9),
                docente,
                docente
            );

            assert oportunidade != null : "Oportunidade não pode ser nula";
            assert oportunidade.getStatus() == StatusOportunidade.RASCUNHO : "Status deve ser RASCUNHO";
            assert oportunidade.getTitulo().equals("Seminário de IA") : "Título incorreto";

            System.out.println("✓ Oportunidade criada em RASCUNHO: " + oportunidade.getTitulo());
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeSubmeterAprovacao() {
        System.out.println("\n[TESTE 5] Submeter para Aprovação (RF011)");
        try {
            List<Oportunidade> todas = oportunidadesService.listarTodas();
            Oportunidade oportunidade = todas.get(0);

            oportunidadesService.submeterParaAprovacao(oportunidade);

            assert oportunidade.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO : "Status deve ser AGUARDANDO_APROVACAO";

            System.out.println("✓ Oportunidade submetida para aprovação");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeAprovarOportunidade() {
        System.out.println("\n[TESTE 6] Aprovar Oportunidade (RF012)");
        try {
            List<Oportunidade> todas = oportunidadesService.listarTodas();
            Oportunidade oportunidade = todas.get(0);
            Docente docente = oportunidade.getDocenteResponsavel();

            oportunidadesService.aprovarOportunidade(oportunidade, docente);

            assert oportunidade.getStatus() == StatusOportunidade.APROVADA || 
                   oportunidade.getStatus() == StatusOportunidade.EM_INSCRICOES : 
                   "Status deve ser APROVADA ou EM_INSCRICOES";

            System.out.println("✓ Oportunidade aprovada - Status: " + oportunidade.getStatus());
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeReprovarOportunidade() {
        System.out.println("\n[TESTE 7] Reprovar Oportunidade (RF012)");
        try {
            Docente docente = docenteRepo.listaDocentes.get(0);
            LocalDateTime agora = LocalDateTime.now();

            Oportunidade oportunidade = oportunidadesService.criarOportunidade(
                "Workshop de Python",
                "Aprenda Python",
                TiposOportunidade.EVENTO,
                TiposModalidade.REMOTO,
                16,
                30,
                agora.plusDays(15),
                agora.plusDays(17),
                agora.plusDays(2),
                agora.plusDays(14),
                docente,
                docente
            );

            oportunidadesService.submeterParaAprovacao(oportunidade);
            oportunidadesService.reprovarOportunidade(oportunidade, docente, "Falta de informações");

            assert oportunidade.getStatus() == StatusOportunidade.REPROVADA : "Status deve ser REPROVADA";
            assert oportunidade.getFeedbackReprovacao() != null : "Feedback não pode ser nulo";

            System.out.println("✓ Oportunidade reprovada com feedback");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeListarOportunidadesAbertas() {
        System.out.println("\n[TESTE 8] Listar Oportunidades Abertas");
        try {
            List<Oportunidade> abertas = oportunidadesService.listarOportunidadesAbertas();

            assert abertas != null : "Lista não pode ser nula";
            for (Oportunidade op : abertas) {
                assert op.getStatus() == StatusOportunidade.EM_INSCRICOES : 
                       "Deve listar apenas EM_INSCRICOES";
            }

            System.out.println("✓ Total de oportunidades abertas: " + abertas.size());
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    // ====================================================================
    // TESTES DE INSCRIÇÕES (RF015, RF016, RF017)
    // ====================================================================
    private static void testesInscricoes() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TESTES: INSCRIÇÕES (RF015, RF016, RF017)");
        System.out.println("=".repeat(60));

        testeInscreverDiscente();
        testeAprovarInscricao();
        testeRejeitarInscricao();
        testeCancelarInscricao();
    }

    private static void testeInscreverDiscente() {
        System.out.println("\n[TESTE 9] Inscrever Discente (RF015)");
        try {
            List<Oportunidade> abertas = oportunidadesService.listarOportunidadesAbertas();
            if (abertas.isEmpty()) {
                System.out.println("⊘ SKIP: Nenhuma oportunidade aberta para inscrição");
                return;
            }

            Oportunidade oportunidade = abertas.get(0);
            Discente discente = discenteRepo.listaDiscente.get(0);

            Inscricao inscricao = inscricaoService.criarInscricao(
                oportunidade, discente, "Tenho interesse neste tema"
            );

            assert inscricao != null : "Inscrição não pode ser nula";
            assert inscricao.getStatus() == StatusInscricao.PENDENTE : "Status deve ser PENDENTE";
            assert inscricao.getDiscente().equals(discente) : "Discente incorreto";

            System.out.println("✓ Discente " + discente.getNome() + " inscrito com status PENDENTE");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeAprovarInscricao() {
        System.out.println("\n[TESTE 10] Aprovar Inscrição (RF015)");
        try {
            List<Inscricao> todas = inscricaoRepo.listarTodas();
            if (todas.isEmpty()) {
                System.out.println("⊘ SKIP: Nenhuma inscrição para testar");
                return;
            }

            Inscricao inscricao = null;
            for (Inscricao i : todas) {
                if (i.getStatus() == StatusInscricao.PENDENTE) {
                    inscricao = i;
                    break;
                }
            }

            if (inscricao == null) {
                System.out.println("⊘ SKIP: Nenhuma inscrição pendente");
                return;
            }

            inscricaoService.aprovarInscricao(inscricao, inscricao.getOportunidade());

            assert inscricao.getStatus() == StatusInscricao.APROVADO : "Status deve ser APROVADO";

            System.out.println("✓ Inscrição aprovada");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeRejeitarInscricao() {
        System.out.println("\n[TESTE 11] Rejeitar Inscrição (RF015)");
        try {
            List<Oportunidade> abertas = oportunidadesService.listarOportunidadesAbertas();
            if (abertas.isEmpty()) {
                System.out.println("⊘ SKIP: Nenhuma oportunidade aberta");
                return;
            }

            Oportunidade oportunidade = abertas.get(0);
            Discente discente = discenteRepo.listaDiscente.get(0);

            Inscricao inscricao = inscricaoService.criarInscricao(
                oportunidade, discente, "Teste rejeição"
            );

            inscricaoService.rejeitarInscricao(inscricao);

            assert inscricao.getStatus() == StatusInscricao.REJEITADO : "Status deve ser REJEITADO";

            System.out.println("✓ Inscrição rejeitada");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeCancelarInscricao() {
        System.out.println("\n[TESTE 12] Cancelar Inscrição (RF016)");
        try {
            List<Oportunidade> abertas = oportunidadesService.listarOportunidadesAbertas();
            if (abertas.isEmpty()) {
                System.out.println("⊘ SKIP: Nenhuma oportunidade aberta");
                return;
            }

            Oportunidade oportunidade = abertas.get(0);
            Discente discente = discenteRepo.listaDiscente.get(0);

            Inscricao inscricao = inscricaoService.criarInscricao(
                oportunidade, discente, "Teste cancelamento"
            );

            inscricaoService.cancelarInscricao(inscricao);

            assert inscricao.getStatus() == StatusInscricao.CANCELADO : "Status deve ser CANCELADO";

            System.out.println("✓ Inscrição cancelada");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    // ====================================================================
    // TESTES DE CERTIFICADOS (RF019)
    // ====================================================================
    private static void testesCertificados() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TESTES: CERTIFICADOS (RF019)");
        System.out.println("=".repeat(60));

        testeGerarCertificados();
        testeListarCertificados();
    }

    private static void testeGerarCertificados() {
        System.out.println("\n[TESTE 13] Gerar Certificados (RF019)");
        try {
            List<Oportunidade> abertas = oportunidadesService.listarOportunidadesAbertas();
            if (abertas.isEmpty()) {
                System.out.println("⊘ SKIP: Nenhuma oportunidade aberta");
                return;
            }

            Oportunidade oportunidade = abertas.get(0);

            List<Certificado> certificados = certificadoService.encerrarEGerarCertificados(oportunidade);

            assert oportunidade.getStatus() == StatusOportunidade.CONCLUIDA : "Oportunidade deve estar CONCLUIDA";
            assert certificados != null : "Lista de certificados não pode ser nula";

            System.out.println("✓ " + certificados.size() + " certificado(s) gerado(s)");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeListarCertificados() {
        System.out.println("\n[TESTE 14] Listar Certificados");
        try {
            List<Certificado> certificados = certificadoService.listarCertificados();

            assert certificados != null : "Lista não pode ser nula";

            System.out.println("✓ Total de certificados emitidos: " + certificados.size());
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    // ====================================================================
    // TESTES DE PERMISSÕES E ALTERAÇÕES
    // ====================================================================
    private static void testesAlteracaoPermissoes() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TESTES: ALTERAÇÃO DE PERMISSÕES");
        System.out.println("=".repeat(60));

        testeAtribuirPermissao();
        testeMudarPermissao();
        testeRemoverPermissao();
    }

    private static void testeAtribuirPermissao() {
        System.out.println("\n[TESTE 15] Atribuir Permissão");
        try {
            Discente discente = discenteService.autocadastroDiscente(
                "Ana Silva", "ana@email.com", "senha123", "2021003"
            );

            assert discente.getRole() == RolesUsuario.DISCENTE : "Role deve ser DISCENTE";
            assert alteracaoRepo.getListaAlteracaoPermissao().size() > 0 : "Alteração não foi registrada";

            System.out.println("✓ Permissão DISCENTE atribuída");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeMudarPermissao() {
        System.out.println("\n[TESTE 16] Mudar Permissão");
        try {
            Discente discente = discenteRepo.listaDiscente.get(0);

            usuarioService.mudarPermissao(discente, RolesUsuario.DOCENTE);

            assert discente.getRole() == RolesUsuario.DOCENTE : "Role deve ser DOCENTE";

            System.out.println("✓ Permissão alterada para DOCENTE");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    private static void testeRemoverPermissao() {
        System.out.println("\n[TESTE 17] Remover Permissão");
        try {
            Discente discente = discenteRepo.listaDiscente.get(0);

            usuarioService.removerPermissao(discente);

            assert discente.getRole() == null : "Role deve ser nulo";

            System.out.println("✓ Permissão removida");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    // ====================================================================
    // TESTES DE APROVEITAMENTO
    // ====================================================================
    private static void testesAproveitamento() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TESTES: APROVEITAMENTO DE HORAS");
        System.out.println("=".repeat(60));

        testeSolicitarAproveitamento();
    }

    private static void testeSolicitarAproveitamento() {
        System.out.println("\n[TESTE 18] Solicitar Aproveitamento de Horas");
        try {
            Discente discente = discenteRepo.listaDiscente.get(0);

            boolean resultado = aproveitamentoService.solicitarAproveitamento(
                discente, "Curso externo", "USP", 20
            );

            assert resultado : "Falha ao solicitar aproveitamento";

            System.out.println("✓ Solicitação de aproveitamento realizada");
            testesPassados++;
        } catch (AssertionError e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            testesFalhados++;
        } catch (Exception e) {
            System.out.println("✗ ERRO: " + e.getMessage());
            testesFalhados++;
        }
    }

    // ====================================================================
    // RESUMO DOS TESTES
    // ====================================================================
    private static void exibirResumoTestes() {
        int totalTestes = testesPassados + testesFalhados;
        double percentualSucesso = totalTestes > 0 ? (testesPassados * 100.0 / totalTestes) : 0;

        System.out.println("\n" + "=".repeat(60));
        System.out.println("RESUMO DOS TESTES");
        System.out.println("=".repeat(60));
        System.out.printf("\n✓ Testes Passados: %d\n", testesPassados);
        System.out.printf("✗ Testes Falhados: %d\n", testesFalhados);
        System.out.printf("Total: %d\n", totalTestes);
        System.out.printf("Taxa de Sucesso: %.1f%%\n", percentualSucesso);

        if (testesFalhados == 0) {
            System.out.println("\n🎉 TODOS OS TESTES PASSARAM COM SUCESSO!");
        } else {
            System.out.println("\n⚠️  Alguns testes falharam. Verifique os detalhes acima.");
        }

        System.out.println("\n" + "=".repeat(60) + "\n");
    }
}
