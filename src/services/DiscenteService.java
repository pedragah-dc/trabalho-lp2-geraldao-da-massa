package services;

import entity.Curso;
import entity.Discente;
import entity.Usuario;

import java.util.Scanner;

import entity.enums.RolesUsuario;
import services.UsuarioService;

import static utils.ConsoleUtils.lerInteiroValido;
import static utils.ConsoleUtils.lerStringValida;

public class DiscenteService {

    public Discente autocadastroDiscente(Usuario usuario){
        Scanner scanner = new Scanner(System.in);

        String matricula = lerStringValida(scanner, "insira a matricula: ");
        Integer semestreAtual = lerInteiroValido(scanner, "insira o semestre atual: ", 1, 12);
        String curso = lerStringValida(scanner, "insira o curso: ");

        // checa a matricula, semestre e curso no repositório pra descobrir se o discente existe
        // se o discente já existe, informa isso ao usuário

        return new Discente(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), null, true, RolesUsuario.DISCENTE, null, matricula, semestreAtual, new Curso("Ciência da Computação", 1234, 3600, "3.5"));
    }

}
