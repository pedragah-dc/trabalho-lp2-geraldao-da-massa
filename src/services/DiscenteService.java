package services;

import entity.Curso;
import entity.Discente;
import entity.Usuario;

import java.util.List;
import java.util.Scanner;

import entity.enums.RolesUsuario;
import repository.DiscenteRepository;
import services.UsuarioService;
import services.CursoService;

import repository.CursoRepository;

import static utils.ConsoleUtils.lerInteiroValido;
import static utils.ConsoleUtils.lerStringValida;

public class DiscenteService {
    private static DiscenteRepository discenteRepository;
    private static CursoRepository cursoRepository;
    private static CursoService cursoService;
    private static UsuarioService usuarioService;



    public Discente autocadastroDiscente() {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = usuarioService.autocadastroUsuario(usuarioService);


        String matricula = lerStringValida(scanner, "insira a matricula: ");
        Curso curso = cursoService.escolheCursos(cursoRepository.getListaCursos(), scanner);

        if (verificaMatriculaDiscente(matricula, discenteRepository.listaDiscente)) {
            Discente discente = new Discente(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getPapel(),
                    usuario.getAtivo(),
                    null,
                    matricula,
                    0,
                    curso,
                    RolesUsuario.DISCENTE
            );
            discenteRepository.listaDiscente.add(discente);
            return discente;

        }
        else {
            usuario = usuarioService.mataUsuario(usuario, usuarioService);
            return null;
        }
    }

    public Boolean verificaMatriculaDiscente (String matricula, List < Discente > repositorio){
        for (Discente d : repositorio) {
            if (d.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }
}

