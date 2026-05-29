package test;

import entity.*;
import entity.enums.*;
import repository.*;
import services.*;

import java.util.List;

public class UsuarioServiceTest {

    public static void main(String[] args) {

        Papel papel = new Papel("DISCENTE");
        CursoRepository cursoRepo = new CursoRepository();
        Discente discente = new Discente(1, "Ana Paula", "ana@disc.ufma.br", "senhaAntiga",
                papel, true, "2022001", 2, cursoRepo.listaCursos.get(0));

        OportunidadeRepository opRepo = new OportunidadeRepository();
        UsuarioService service = new UsuarioService(opRepo);

        System.out.println("=== Mudar senha ===");
        service.mudarSenha(discente, "novaSenha123");
        System.out.println("Senha atualizada: " + discente.getSenha());

        System.out.println("\n=== Listar oportunidades disponíveis ===");
        List<Oportunidade> ops = service.obterOportunidades();
        for (Oportunidade op : ops) {
            System.out.println("  - " + op.getTitulo() + " | Status: " + op.getStatus());
        }

        System.out.println("\n✓ UsuarioServiceTest concluído.");
    }
}
