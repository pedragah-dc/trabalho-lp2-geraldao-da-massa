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


    public Docente cadastroDocente(UsuarioService servicoUsuario){
        Scanner scanner = new Scanner(System.in);

        RolesUsuario role;

        Usuario usuario = usuarioService.autocadastroUsuario(usuarioService);

        String siape = lerStringValida(scanner, "insira a matricula siape: ");
        String departamento = lerStringValida(scanner, "insira a departamento: ");

        System.out.println("Escolha a função do docente: ");
        System.out.println("1 - Docente");
        System.out.println("2 - Coordenador");
        System.out.println("3 - Comissão");
        System.out.println("3 - Secretaria");
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                role = RolesUsuario.DOCENTE;
                break;
            case 2:
                role = RolesUsuario.COORDENADOR;
                break;
            case 3:
                role = RolesUsuario.COMISSAO;
                break;
            case 4:
                role = RolesUsuario.SECRETARIA;
                break;
            default:
                System.out.println("Opção inválida!");
                return null;

        }

        Docente docente = new Docente(usuario.getId(), usuario.getNome(), usuario.getEmail(),
                usuario.getSenha(), usuario.getPapel(), usuario.getAtivo(), role, siape, departamento);

        docenterepository.listaDocentes.add(docente);

        return docente;
    }

}
