package services;

import entity.Papel;
import entity.Usuario;

public class AdministradorService {



    public Usuario cadastroGestor(UsuarioService servicoUsuario){
        Integer id = servicoUsuario.getNumUsuarios();

        System.out.println("insira o seu nome: ");
        String nome = System.console().readLine();
        System.out.println("insira o seu email: ");

        //verificação de email e envio do email para o usuário

        String email = System.console().readLine();
        System.out.println("insira a sua senha: ");
        String senha = System.console().readLine();

        System.out.println("insira a descrição do cargo: ");
        String descricao = System.console().readLine();
        Papel papel = new Papel(descricao);

        System.out.println("insira a sua matricula: ");
        String matricula = System.console().readLine();

        //    if (verificaMatricula(matricula)) != NULL{
        //      if (verificaMatricula(matricula)) == "DOCENTE"{}
        //      else if (verificaMatricula(matricula)) == "COORDENADOR"{}
        //      else if (verificaMatricula(matricula)) == "SECRETARIA"{}
        //      else if (verificaMatricula(matricula)) == "COMISSÃO{}
        //    }


        id += 1;


        return new Usuario(id, nome, email, senha, papel, true);

    }
}
