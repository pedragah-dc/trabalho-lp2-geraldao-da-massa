package services;

import entity.Curso;
import entity.Discente;
import entity.Usuario;

import java.util.List;
import java.util.Scanner;

import entity.enums.RolesUsuario;
import repository.DiscenteRepository;

import repository.CursoRepository;

import static utils.ConsoleUtils.lerStringValida;

public class DiscenteService {
    private final DiscenteRepository discenteRepository;
    private final UsuarioService usuarioService;


    public DiscenteService(DiscenteRepository discenteRepository, UsuarioService usuarioService) {
        this.discenteRepository = discenteRepository;
        this.usuarioService = usuarioService;
    }


    public Discente autocadastroDiscente(String nome, String email, String senha, String matricula) {
        Usuario usuario = usuarioService.autocadastroUsuario(usuarioService, nome, email, senha);

        Curso curso = verificaCurso(discenteRepository.listaDiscente, matricula);

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
        else{
            usuarioService.excluirUsuario(usuario, usuarioService);
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

    public Curso verificaCurso (List <Discente> repositorio, String matricula){
        for (Discente d: repositorio){
            if (d.getMatricula().equals(matricula)) {
                return d.getCurso();
            }
        }
        return null;
    }
}

