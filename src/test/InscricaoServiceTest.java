package test;

import entity.*;
import entity.enums.*;
import services.InscricaoService;

import java.time.LocalDateTime;

/**
 * Testes para InscricaoService
 */
public class InscricaoServiceTest {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE INSCRIÇÃO ===\n");

        testCriarInscricao();
        testListarInscricoes();
        testAlterarStatusInscricao();
    }

    public static void testCriarInscricao() {
        System.out.println("TEST 1: Criar uma Inscrição");
        try {
            // Criar dados de teste
            Papel papel = new Papel("Discente");
            Curso curso = new Curso("Engenharia de Software", 1001, 120, "PPC v2.0");
            Discente discente = new Discente(1, "João Silva", "joao@email.com", "senha123", papel, true, "2024001", 3, curso);

            Papel papelDoc = new Papel("Docente");
            Docente docente = new Docente(2, "Prof. Maria", "maria@email.com", "prof123", papelDoc, true, "123456", "Computação");
            Usuario autor = new Usuario(3, "Admin", "admin@email.com", "admin123", new Papel("Admin"), true);

            Oportunidade oportunidade = new Oportunidade(
                "Projeto de IA", 
                "Projeto sobre inteligência artificial",
                TiposOportunidade.PROJETO,
                TiposModalidade.PRESENCIAL,
                40,
                5,
                StatusOportunidade.PENDENTE,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),
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
    }

    public static void testListarInscricoes() {
        System.out.println("TEST 2: Listar Inscrições");
        try {
            Papel papel = new Papel("Discente");
            Curso curso = new Curso("Engenharia", 1002, 120, "PPC v1.0");
            Discente discente = new Discente(1, "Maria", "maria@email.com", "123", papel, true, "2024002", 2, curso);

            Papel papelDoc = new Papel("Docente");
            Docente docente = new Docente(2, "Prof. João", "joao@email.com", "456", papelDoc, true, "654321", "TI");
            Usuario autor = new Usuario(3, "Admin", "admin@email.com", "789", new Papel("Admin"), true);

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
            System.out.println();
        }
    }

    public static void testAlterarStatusInscricao() {
        System.out.println("TEST 3: Alterar Status da Inscrição");
        try {
            Papel papel = new Papel("Discente");
            Curso curso = new Curso("Cursos", 1003, 100, "PPC v1.0");
            Discente discente = new Discente(2, "Pedro", "pedro@email.com", "pass", papel, true, "2024003", 1, curso);

            Papel papelDoc = new Papel("Docente");
            Docente docente = new Docente(3, "Prof. Ana", "ana@email.com", "prof", papelDoc, true, "789123", "Eng");
            Usuario autor = new Usuario(4, "Admin", "admin@email.com", "adm", new Papel("Admin"), true);

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
