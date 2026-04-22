package test;

import entity.*;
import entity.enums.*;
import repository.OportunidadeRepository;
import services.OportunidadesService;

import java.time.LocalDateTime;

/**
 * Testes para OportunidadesService
 */
public class OportunidadesServiceTest {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE OPORTUNIDADES ===\n");

        testPublicarOportunidade();
        testFecharInscricoes();
        testPublicarMultiplasOportunidades();
    }

    public static void testPublicarOportunidade() {
        System.out.println("TEST 1: Publicar uma Oportunidade");
        try {
            Papel papel = new Papel("Docente");
            Docente docente = new Docente(1, "Prof. Lucia", "lucia@email.com", "lucia123", papel, true, "444444", "Engenharia");
            Usuario autor = new Usuario(2, "Admin", "admin@email.com", "admin123", new Papel("Admin"), true);

            OportunidadeRepository repository = new OportunidadeRepository();
            OportunidadesService service = new OportunidadesService(repository);

            System.out.println("  Oportunidades antes: " + repository.listaOportunidades.size());
            service.publicar("Internship - Desenvolvimento",
                "Oportunidade de estágio em desenvolvimento",
                TiposOportunidade.PROJETO,
                TiposModalidade.PRESENCIAL,
                20,
                2,
                StatusOportunidade.PUBLICADA,
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(6),
                autor,
                docente);
            System.out.println("  Oportunidades depois: " + repository.listaOportunidades.size());
            System.out.println("✓ Oportunidade publicada com sucesso!");
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }

    public static void testFecharInscricoes() {
        System.out.println("TEST 2: Fechar Inscrições de uma Oportunidade");
        try {
            Papel papel = new Papel("Docente");
            Docente docente = new Docente(2, "Prof. Roberto", "roberto@email.com", "roberto", papel, true, "555555", "Computação");
            Usuario autor = new Usuario(3, "Admin", "admin@email.com", "admin", new Papel("Admin"), true);

            LocalDateTime agora = LocalDateTime.now();
            LocalDateTime finalEsperado = agora.plusDays(10);

            Oportunidade oportunidade = new Oportunidade(
                "Bolsa de Pesquisa",
                "Bolsa para pesquisa acadêmica",
                TiposOportunidade.PROJETO,
                TiposModalidade.PRESENCIAL,
                40,
                5,
                StatusOportunidade.PUBLICADA,
                agora,
                finalEsperado,
                autor,
                docente
            );
            OportunidadeRepository repository = new OportunidadeRepository();
            OportunidadesService service = new OportunidadesService(repository);

            System.out.println("  Fim original: " + oportunidade.getFim());
            service.fecharInscricoes(oportunidade);
            System.out.println("  Novo fim: " + oportunidade.getFim());
            System.out.println("✓ Inscrições fechadas com sucesso!");
            System.out.println("  - Status finalizado: " + oportunidade.isFinalizada());
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }

    public static void testPublicarMultiplasOportunidades() {
        System.out.println("TEST 3: Publicar Múltiplas Oportunidades");
        try {
            Papel papel = new Papel("Docente");
            Docente docente = new Docente(3, "Prof. Fernanda", "fernanda@email.com", "fernanda", papel, true, "666666", "Sistemas");
            Usuario autor = new Usuario(4, "Admin", "admin@email.com", "admin", new Papel("Admin"), true);
            
            OportunidadeRepository repository = new OportunidadeRepository();
            OportunidadesService service = new OportunidadesService(repository);

            System.out.println("  Contagem inicial: " + repository.listaOportunidades.size());

            for (int i = 1; i <= 3; i++) {
                service.publicar("Oportunidade " + i,
                    "Descrição da oportunidade " + i,
                    TiposOportunidade.PROJETO,
                    TiposModalidade.REMOTO,
                    20,
                    i * 10,
                    StatusOportunidade.PUBLICADA,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(30),
                    autor,
                    docente);
            }

            System.out.println("  Contagem final: " + repository.listaOportunidades.size());
            System.out.println("✓ Todas as 3 oportunidades foram publicadas!");
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }
}
