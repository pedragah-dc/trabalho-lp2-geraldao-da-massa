package test;

import entity.*;
import entity.enums.*;
import repository.*;
import services.*;

import java.time.LocalDateTime;
import java.util.List;

public class InscricaoServiceTest {

    public static void main(String[] args) {

        Papel papelDocente  = new Papel("DOCENTE");
        Papel papelDiscente = new Papel("DISCENTE");

        Docente docente = new Docente(1, "Prof. Ana", "ana@ufma.br", "123", papelDocente, true, RolesUsuario.DOCENTE, "9999", "TI");

        CursoRepository cursoRepo = new CursoRepository();
        Curso curso = cursoRepo.listaCursos.get(0);

        Discente joao  = new Discente(10, "João",  "joao@disc.ufma.br",  "123", papelDiscente, true, null, "2021001", 3, curso, RolesUsuario.DISCENTE);
        Discente maria = new Discente(11, "Maria", "maria@disc.ufma.br", "123", papelDiscente, true, null, "2021002", 3, curso, RolesUsuario.DISCENTE);
        Discente pedro = new Discente(12, "Pedro", "pedro@disc.ufma.br", "123", papelDiscente, true, null, "2021003", 4, curso, RolesUsuario.DISCENTE);
        Discente carla = new Discente(13, "Carla", "carla@disc.ufma.br", "123", papelDiscente, true, null, "2021004", 3, curso, RolesUsuario.DISCENTE);

        OportunidadeRepository opRepo   = new OportunidadeRepository();
        InscricaoRepository    inscRepo = new InscricaoRepository();
        OportunidadesService opService  = new OportunidadesService(opRepo);
        InscricaoService inscService    = new InscricaoService(inscRepo);
        CertificadoService certService  = new CertificadoService(inscRepo);

        Oportunidade op = opService.criarOportunidade(
                "Curso de Java POO", "Orientação a objetos na prática",
                TiposOportunidade.CURSO, TiposModalidade.PRESENCIAL,
                40, 2,
                LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(50),
                LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(9),
                docente, docente);

        opService.submeterParaAprovacao(op);
        opService.aprovarOportunidade(op, docente);

        System.out.println("=== RF015: Inscrever e APROVAR/REJEITAR ===");
        Inscricao inscJoao  = inscService.criarInscricao(op, joao,  "Quero aprender Java");
        Inscricao inscMaria = inscService.criarInscricao(op, maria, "Necessário para minha pesquisa");
        Inscricao inscPedro = inscService.criarInscricao(op, pedro, "Interesse em POO");
        Inscricao inscCarla = inscService.criarInscricao(op, carla, "Tenho interesse");

        inscService.aprovarInscricao(inscJoao, op);
        inscService.aprovarInscricao(inscMaria, op);
        inscService.rejeitarInscricao(inscPedro);
        System.out.println("João: " + inscJoao.getStatus());   // APROVADO
        System.out.println("Maria: " + inscMaria.getStatus()); // APROVADO
        System.out.println("Pedro: " + inscPedro.getStatus()); // REJEITADO

        System.out.println("\n--- Vagas esgotadas ---");
        try {
            // Criar dados de teste
            Papel papel = new Papel("Discente");
            Discente discente = new Discente(1, "João Silva", "joao@email.com", "senha123", papel, true, null, "2024001", 3, curso, RolesUsuario.DISCENTE);

            Papel papelDoc = new Papel("Docente");
            Usuario autor = new Usuario(3, "Admin", "admin@email.com", "admin123", new Papel("Admin"), true, null);

            Oportunidade oportunidade = new Oportunidade(
                "Projeto de IA", 
                "Projeto sobre inteligência artificial",
                TiposOportunidade.PROJETO,
                TiposModalidade.PRESENCIAL,
                40,
                5,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(40),
                autor,
                docente
            );

            // Teste
            InscricaoService service = new InscricaoService();
            service.criarInscricao(oportunidade, discente, "Quero aprender mais sobre IA");

            System.out.println("✓ Inscrição criada com sucesso!");
            System.out.println("  - Discente: " + discente.getNome());
            System.out.println("  - Oportunidade: " + oportunidade.getTitulo());
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }

        System.out.println("\n=== RF016: Cancelar inscrição ===");
        inscService.cancelarInscricao(inscPedro);
        System.out.println("Pedro após cancelar: " + inscPedro.getStatus()); // CANCELADO

        System.out.println("\n=== RF017: Substituir por alguém da lista de interessados ===");
        // Carla está PENDENTE na oportunidade = é da lista de interessados
        Inscricao inscSubstituta = inscService.substituirParticipante(inscJoao, carla);
        System.out.println("João (original): " + inscJoao.getStatus());        // SUBSTITUIDO
        System.out.println("Carla (substituta): " + inscSubstituta.getStatus()); // APROVADO

        System.out.println("\n--- Tentando substituir por quem cancelou ---");
        try {
            Papel papel = new Papel("Discente");
            Discente discente = new Discente(1, "Maria", "maria@email.com", "123", papel, true, null, "2024002", 2, curso, RolesUsuario.DISCENTE);

            Papel papelDoc = new Papel("Docente");
            Usuario autor = new Usuario(3, "Admin", "admin@email.com", "789", new Papel("Admin"), true, null);

            Oportunidade oportunidade = new Oportunidade(
                "Workshop Python",
                "Aprenda Python do zero",
                TiposOportunidade.EVENTO,
                TiposModalidade.REMOTO,
                20,
                3,
                StatusOportunidade.PUBLICADA,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(15),
                autor,
                docente
            );

            InscricaoService service = new InscricaoService();
            service.criarInscricao(oportunidade, discente, "Tenho interesse");
            service.criarInscricao(oportunidade, discente, "Outro motivo");

            var inscricoes = service.getInscricao();
            System.out.println("✓ Total de inscrições: " + inscricoes.size());
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
        }
    }

    public static void testAlterarStatusInscricao() {
        System.out.println("TEST 3: Alterar Status da Inscrição");
        try {
            Papel papel = new Papel("Discente");
            Curso curso = new Curso("Cursos", 1003, 100, "PPC v1.0");
            Discente discente = new Discente(2, "Pedro", "pedro@email.com", "pass", papel, true, null, "2024003", 1, curso, RolesUsuario.DISCENTE);

            Papel papelDoc = new Papel("Docente");
            Docente docente = new Docente(3, "Prof. Ana", "ana@email.com", "prof", papelDoc, true, RolesUsuario.DOCENTE, "789123", "Eng");
            Usuario autor = new Usuario(4, "Admin", "admin@email.com", "adm", new Papel("Admin"), true, null);

            Oportunidade oportunidade = new Oportunidade(
                "Seminário Tech",
                "Seminário sobre tecnologia",
                TiposOportunidade.OFICINA,
                TiposModalidade.REMOTO,
                10,
                2,
                StatusOportunidade.EM_PROGRESSO,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                autor,
                docente
            );

            InscricaoService service = new InscricaoService();
            service.criarInscricao(oportunidade, discente, "Interessa");

            var inscricoes = service.getInscricao();
            Inscricao inscricao = inscricoes.get(0);
            inscricao.setStatus(StatusInscricao.APROVADO);

            System.out.println("✓ Status alterado para: " + inscricao.getStatus());
            System.out.println("  - Discente: " + inscricao.getDiscente().getNome());
            System.out.println("  - Novo Status: APROVADO");
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }
}
