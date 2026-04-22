package test;

import entity.*;
import services.UsuarioService;

/**
 * Testes para UsuarioService
 */
public class UsuarioServiceTest {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE USUÁRIO ===\n");

        testMudarSenha();
        testMudarSenhaMultiplasVezes();
        testObterOportunidades();
    }

    public static void testMudarSenha() {
        System.out.println("TEST 1: Alterar Senha de Usuário");
        try {
            Papel papel = new Papel("Discente");
            Usuario usuario = new Usuario(1, "João Silva", "joao@email.com", "senhaAntiga123", papel, true);

            System.out.println("  Usuário: " + usuario.getNome());
            System.out.println("  Senha anterior: " + usuario.getSenha());

            UsuarioService service = new UsuarioService();
            String novaSenha = "novaSenha456";
            service.mudarSenha(usuario, novaSenha);

            System.out.println("  Senha nova: " + usuario.getSenha());
            System.out.println("✓ Senha alterada com sucesso!");
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }

    public static void testMudarSenhaMultiplasVezes() {
        System.out.println("TEST 2: Alterar Senha Múltiplas Vezes");
        try {
            Papel papel = new Papel("Docente");
            Usuario usuario = new Usuario(2, "Prof. Maria", "maria@email.com", "senhaInicial", papel, true);

            UsuarioService service = new UsuarioService();

            System.out.println("  Usuário: " + usuario.getNome());
            System.out.println("  Senha inicial: " + usuario.getSenha());

            String[] senhas = {"senha2023", "minhaSenha!", "segura@123", "finalSenha"};
            
            for (int i = 0; i < senhas.length; i++) {
                service.mudarSenha(usuario, senhas[i]);
                System.out.println("  Alteração " + (i + 1) + ": " + usuario.getSenha());
            }

            System.out.println("✓ Múltiplas alterações de senha realizadas!");
            System.out.println("  Senha final: " + usuario.getSenha());
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }

    public static void testObterOportunidades() {
        System.out.println("TEST 3: Obter Oportunidades Disponíveis");
        try {
            UsuarioService service = new UsuarioService();
            var oportunidades = service.obterOportunidades();

            System.out.println("✓ Oportunidades carregadas!");
            System.out.println("  Total de oportunidades: " + oportunidades.size());
            
            if (oportunidades.size() > 0) {
                System.out.println("\n  Primeiras oportunidades:");
                for (int i = 0; i < Math.min(3, oportunidades.size()); i++) {
                    Oportunidade op = oportunidades.get(i);
                    System.out.println("  " + (i + 1) + ". " + op.getTitulo() + 
                                     " - Vagas: " + op.getVagas() +
                                     " - Status: " + op.getStatusOportunidade());
                }
            } else {
                System.out.println("  Nenhuma oportunidade disponível no momento");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("✗ FALHA: " + e.getMessage());
            System.out.println();
        }
    }
}
