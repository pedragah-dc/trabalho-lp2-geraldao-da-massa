package services;

import entity.Docente;
import entity.Papel;
import entity.Usuario;
import entity.enums.RolesUsuario;
import repository.DocenteRepository;

import java.util.Scanner;

import static utils.ConsoleUtils.lerStringValida;

public class AdministradorService {
    static UsuarioService usuarioService;
    static DocenteRepository docenterepository;


    public Docente cadastroDocente(UsuarioService servicoUsuario, String nome, String email, String senha, String siape, String departamento, RolesUsuario role){
        Scanner scanner = new Scanner(System.in);

        Usuario usuario = usuarioService.autocadastroUsuario(usuarioService, nome, email, senha);

        Docente docente = new Docente(usuario.getId(), usuario.getNome(), usuario.getEmail(),
                usuario.getSenha(), usuario.getPapel(), usuario.getAtivo(), role, siape, departamento);

        docenterepository.listaDocentes.add(docente);

        return docente;
    }

}
