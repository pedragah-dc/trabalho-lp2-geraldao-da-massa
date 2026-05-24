package services;

import entity.Oportunidade;
import entity.Papel;
import entity.Usuario;
import repository.OportunidadeRepository;

import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUtils.lerStringValida;

public class UsuarioService {
    private Integer numUsuarios;



    public UsuarioService(Integer numUser){
        this.numUsuarios = numUser;
    }

    public Integer getNumUsuarios(){
        return numUsuarios;
    }
    public void setNumUsuarios(Integer numUsuarios){
        this.numUsuarios = numUsuarios;
    }


    public void mudarSenha(Usuario usuario, String novaSenha){
        usuario.setSenha(novaSenha);
        System.out.println("MUDADO");

    }
    public List<Oportunidade> obterOportunidades(){
        OportunidadeRepository listaOportunidades = new OportunidadeRepository();
        return listaOportunidades.listaOportunidades;
    }


    public Usuario autocadastroUsuario(UsuarioService servicoUsuario){
        Scanner scanner = new Scanner(System.in);
        Integer id = servicoUsuario.getNumUsuarios();

        String nome = lerStringValida(scanner, "insira o seu nome: ");
        String email = lerStringValida(scanner, "insira o seu email: ");
        String senha =  lerStringValida(scanner, "insira a senha: ");
        String matricula = lerStringValida(scanner, "insira a matricula: ");

        id += 1;

        servicoUsuario.setNumUsuarios(id);

        return new Usuario(id, nome, email, senha, null,  true, null, null);
    }
}
