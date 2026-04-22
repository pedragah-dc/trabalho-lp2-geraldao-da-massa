package test;
import entity.*;
import services.GrupoService;

/**
 * Testes para GrupoService
 */
public class GrupoServiceTest {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE GRUPO ===\n");

        testAdicionarMembroSimples();
        testAdicionarMultiplosMembrosDiferentes();
        testAdicionarDocenteComoMembro();
    }

    public static void testAdicionarMembroSimples() {
        System.out.println("TEST 1: Adicionar Membro em um Grupo");
        try {
            Papel papel = new Papel("Discente");
            Curso curso = new Curso("Engenharia", 1001, 120, "PPC v1.0");
            Usuario membro = new Discente(1, "Ana Silva", "ana@email.com", "123", papel, true, "2024001", 2, curso);

            GrupoService service = new GrupoService();

            System.out.println("  Membro: " + membro.getNome());
            System.out.println("  Email: " + membro.getEmail());
            
            service.adicionarMembro(membro);

            System.out.println("✓ Membro adicionado com sucesso ao grupo!");
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }

    public static void testAdicionarMultiplosMembrosDiferentes() {
        System.out.println("TEST 2: Adicionar Múltiplos Membros (Discentes e Docentes)");
        try {
            GrupoService service = new GrupoService();
            Papel papelDiscente = new Papel("Discente");
            Papel papelDocente = new Papel("Docente");

            // Criando membros discentes
            Curso curso = new Curso("Computação", 1002, 120, "PPC v2.0");
            Discente discente1 = new Discente(1, "Carlos Santos", "carlos@email.com", "pass1", papelDiscente, true, "2024002", 3, curso);
            Discente discente2 = new Discente(2, "Beatriz Costa", "beatriz@email.com", "pass2", papelDiscente, true, "2024003", 2, curso);

            // Criando membro docente
            Docente docente = new Docente(3, "Prof. Lucas", "lucas@email.com", "prof123", papelDocente, true, "777777", "Computação");

            System.out.println("  Adicionando discentes:");
            service.adicionarMembro(discente1);
            System.out.println("    ✓ " + discente1.getNome() + " (" + discente1.getClass().getSimpleName() + ")");
            
            service.adicionarMembro(discente2);
            System.out.println("    ✓ " + discente2.getNome() + " (" + discente2.getClass().getSimpleName() + ")");

            System.out.println("  Adicionando docente:");
            service.adicionarMembro(docente);
            System.out.println("    ✓ " + docente.getNome() + " (" + docente.getClass().getSimpleName() + ")");

            System.out.println("✓ Múltiplos membros adicionados com sucesso!");
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }

    public static void testAdicionarDocenteComoMembro() {
        System.out.println("TEST 3: Adicionar Docente como Coordenador do Grupo");
        try {
            Papel papelDocente = new Papel("Docente");
            Docente coordenador = new Docente(4, "Prof. Thiago", "thiago@email.com", "prof456", papelDocente, true, "888888", "Engenharia");

            GrupoService service = new GrupoService();

            System.out.println("  Coordenador: " + coordenador.getNome());
            System.out.println("  SIAPE: " + coordenador.getSiape());
            System.out.println("  Departamento: " + coordenador.getDepartamento());
            
            service.adicionarMembro(coordenador);

            System.out.println("✓ Docente adicionado como coordenador do grupo!");
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }
}
