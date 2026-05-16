package services;

import entity.Oportunidade;
import entity.Papel;
import entity.Usuario;
import repository.OportunidadeRepository;

import java.util.List;

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
        Integer id = servicoUsuario.getNumUsuarios();

        System.out.println("insira o seu nome: ");
        String nome = System.console().readLine();
        System.out.println("insira o seu email: ");

        //verificação de email e envio do email para o usuário

        String email = System.console().readLine();
        System.out.println("insira a sua senha: ");
        String senha = System.console().readLine();
        System.out.println("insira a sua descrição: ");

        System.out.println("insira a sua matricula: ");
        String matricula = System.console().readLine();

        //    if (verificaMatricula(matricula)) != NULL{
        //      if (verificaMatricula(matricula)) == "DISCENTE"{}
        //      else if (verificaMatricula(matricula)) == "DOCENTE"{}
        //    }


        id += 1;

        servicoUsuario.setNumUsuarios(id);

        return new Usuario(id, nome, email, senha, null,  true);

    }








}
