package services;

import entity.AlteracaoPermissao;
import entity.Oportunidade;
import entity.Papel;
import entity.Usuario;
import entity.enums.RolesUsuario;
import entity.enums.TipoOperacao;
import repository.AlteracaoPermissaoRepository;
import repository.OportunidadeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUtils.lerStringValida;

public class UsuarioService {
    private Integer numUsuarios;
    private AlteracaoPermissaoRepository alteracaoRepositorio;

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
    //kkkkkkkkkk
    public List<Oportunidade> obterOportunidades(){
        OportunidadeRepository listaOportunidades = new OportunidadeRepository();
        return listaOportunidades.listaOportunidades;
    }

    public void mudarPermissao(Usuario usuario, RolesUsuario role){
        AlteracaoPermissao alteracao = new AlteracaoPermissao();
        usuario.setRole(role);

        alteracao.setUsuario(usuario);
        alteracao.setDataAlteracao(LocalDateTime.now());
        alteracao.setTipoOperacao(TipoOperacao.ALTERAR);


        alteracaoRepositorio.getListaAlteracaoPermissao().add(alteracao);

    }

    public Usuario autocadastroUsuario(UsuarioService servicoUsuario, String nome, String email, String senha){
        Integer id = servicoUsuario.getNumUsuarios();

        id += 1;

        servicoUsuario.setNumUsuarios(id);

        return new Usuario(id, nome, email, senha, null,  true, null);
    }

    public void excluirUsuario(Usuario usuario, UsuarioService servicoUsuario){
        usuario.setSenha(null);
        usuario.setNome(null);
        usuario.setEmail(null);
        usuario.setId(null);

        Integer novoId = servicoUsuario.getNumUsuarios() - 1;
        servicoUsuario.setNumUsuarios(novoId);
    }
}
