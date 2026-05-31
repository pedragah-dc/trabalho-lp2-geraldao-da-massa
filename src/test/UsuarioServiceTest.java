package test;

import entity.*;
import entity.enums.*;
import repository.*;
import services.*;

import java.util.List;
import repository.AlteracaoPermissaoRepository;
import services.UsuarioService;

public class UsuarioServiceTest {

    public static void main(String[] args) {
        // Executa os testes
        testMudarSenha();
        testMudarSenhaMultiplasVezes();
    }

    public static void testMudarSenha() {
        System.out.println("TEST 1: Alterar Senha de Usuário");
        try {
            Papel papel = new Papel("Discente");
            Usuario usuario = new Usuario(1, "João Silva", "joao@email.com", "senhaAntiga123", papel, true, null);

            System.out.println("=== Mudar senha ===");
            UsuarioService service = new UsuarioService(new AlteracaoPermissaoRepository());
            service.mudarSenha(usuario, "novaSenha123");
            System.out.println("Senha atualizada: " + usuario.getSenha());
            System.out.println("  Usuário: " + usuario.getNome());
            System.out.println("  Senha anterior: " + usuario.getSenha());

            String novaSenha = "novaSenha456";
            service.mudarSenha(usuario, novaSenha);

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
            Usuario usuario = new Usuario(2, "Prof. Maria", "maria@email.com", "senhaInicial", papel, true, null);

            UsuarioService service = new UsuarioService(new AlteracaoPermissaoRepository());

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
}