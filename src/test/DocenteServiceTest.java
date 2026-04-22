package test;

import entity.*;
import entity.enums.*;
import repository.OportunidadeRepository;
import services.DocenteService;
import services.OportunidadesService;

import java.time.LocalDateTime;

/**
 * Testes para DocenteService
 */
public class DocenteServiceTest {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE DOCENTE ===\n");

        testCriarOportunidade();
        testCriarOportunidadeComDiferentesModalidades();
        testCriarOportunidadeComCarateristica();
    }

    public static void testCriarOportunidade() {
        System.out.println("TEST 1: Criar uma Oportunidade");
        try {
            Papel papel = new Papel("Docente");
            Docente docente = new Docente(1, "Prof. Carlos", "carlos@email.com", "prof123", papel, true, "111111", "Computação");
            Usuario autor = new Usuario(2, "Admin", "admin@email.com", "admin123", new Papel("Admin"), true);
            OportunidadesService oportunidadeService = new OportunidadesService(new OportunidadeRepository());
            DocenteService service = new DocenteService(oportunidadeService);
            
            Oportunidade oportunidade = service.criarOportunidade(
                "Curso Algoritmos",
                "Aprenda estrutura de dados e algoritmos",
                TiposOportunidade.CURSO,
                TiposModalidade.PRESENCIAL,
                60,
                30,
                StatusOportunidade.PUBLICADA,
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(4),
                autor,
                docente
            );

            System.out.println("✓ Oportunidade criada com sucesso!");
            System.out.println("  - Título: " + oportunidade.getTitulo());
            System.out.println("  - Carga Horária: " + oportunidade.getCargaHoraria() + " horas");
            System.out.println("  - Vagas: " + oportunidade.getVagas());
            System.out.println("  - Status: " + oportunidade.getStatusOportunidade());
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }

    public static void testCriarOportunidadeComDiferentesModalidades() {
        System.out.println("TEST 2: Criar Oportunidades com Diferentes Modalidades");
        try {
            Papel papel = new Papel("Docente");
            Docente docente = new Docente(2, "Prof. Helena", "helena@email.com", "helena", papel, true, "222222", "Engenharia");
            Usuario autor = new Usuario(3, "Admin", "admin@email.com", "admin", new Papel("Admin"), true);

            OportunidadeRepository repository = new OportunidadeRepository();
            OportunidadesService oportunidadeService = new OportunidadesService(repository);
            DocenteService service = new DocenteService(oportunidadeService);

            // PRESENCIAL
            Oportunidade op1 = service.criarOportunidade(
                "Evento Prático",
                "Evento de programação presencial",
                TiposOportunidade.EVENTO,
                TiposModalidade.PRESENCIAL,
                16,
                20,
                StatusOportunidade.PENDENTE,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5),
                autor,
                docente
            );

            // REMOTO
            Oportunidade op2 = service.criarOportunidade(
                "Projeto Online",
                "Projeto de tecnologia online",
                TiposOportunidade.PROJETO,
                TiposModalidade.REMOTO,
                4,
                100,
                StatusOportunidade.PUBLICADA,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                autor,
                docente
            );

            // HÍBRIDO
            Oportunidade op3 = service.criarOportunidade(
                "Oficina Híbrida",
                "Oficina com presença e online",
                TiposOportunidade.OFICINA,
                TiposModalidade.HIBRIDO,
                40,
                15,
                StatusOportunidade.EM_PROGRESSO,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(60),
                autor,
                docente
            );

            System.out.println("✓ Oportunidades criadas com diferentes modalidades!");
            System.out.println("  - Presencial: " + op1.getTitulo() + " (" + op1.getModalidade() + ")");
            System.out.println("  - Remoto: " + op2.getTitulo() + " (" + op2.getModalidade() + ")");
            System.out.println("  - Híbrido: " + op3.getTitulo() + " (" + op3.getModalidade() + ")");
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }

    public static void testCriarOportunidadeComCarateristica() {
        System.out.println("TEST 3: Criar Oportunidades com Diferentes Características");
        try {
            Papel papel = new Papel("Docente");
            Docente docente = new Docente(3, "Prof. Ricardo", "ricardo@email.com", "ricardo", papel, true, "333333", "Sistemas");
            Usuario autor = new Usuario(4, "Admin", "admin@email.com", "admin", new Papel("Admin"), true);

            OportunidadeRepository repository = new OportunidadeRepository();
            OportunidadesService oportunidadeService = new OportunidadesService(repository);
            DocenteService service = new DocenteService(oportunidadeService);

            Oportunidade op1 = service.criarOportunidade(
                "Seminário - Alta Demanda",
                "Seminário com muitas vagas",
                TiposOportunidade.EVENTO,
                TiposModalidade.REMOTO,
                8,
                50,
                StatusOportunidade.PUBLICADA,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(3),
                autor,
                docente
            );

            Oportunidade op2 = service.criarOportunidade(
                "Projeto Especializado",
                "Projeto com poucas vagas - seletivo",
                TiposOportunidade.PROJETO,
                TiposModalidade.PRESENCIAL,
                120,
                3,
                StatusOportunidade.PENDENTE,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(90),
                autor,
                docente
            );

            System.out.println("✓ Oportunidades com características diferentes criadas!");
            System.out.println("  - " + op1.getTitulo() + " | Vagas: " + op1.getVagas() + " | CH: " + op1.getCargaHoraria());
            System.out.println("  - " + op2.getTitulo() + " | Vagas: " + op2.getVagas() + " | CH: " + op2.getCargaHoraria());
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }
}
