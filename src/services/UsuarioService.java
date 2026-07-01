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
    private static Integer numUsuarios;
    private AlteracaoPermissaoRepository alteracaoRepositorio;

    public UsuarioService(AlteracaoPermissaoRepository alteracaoRepositorio){
        this.numUsuarios = 0;
        this.alteracaoRepositorio = alteracaoRepositorio;
    }

    public Integer getNumUsuarios(){
        return numUsuarios;
    }
    public void setNumUsuarios(Integer numUsuarios){
        this.numUsuarios = numUsuarios;
    }

    public boolean mudarSenha(Usuario usuario, String novaSenha) {
        if (novaSenha == null || novaSenha.isBlank()) {
            throw new IllegalArgumentException("Nova senha não pode ser vazia.");
        }
        usuario.setSenha(novaSenha);
        return true;
    }

    public void atribuirPermissao(Usuario usuario, RolesUsuario role){
        usuario.setRole(role);
        AlteracaoPermissao alteracao = new AlteracaoPermissao(usuario, LocalDateTime.now(), TipoOperacao.ATRIBUIR, null);
        alteracaoRepositorio.getListaAlteracaoPermissao().add(alteracao);
    }

    public void mudarPermissao(Usuario usuario, RolesUsuario role){
        usuario.setRole(role);
        AlteracaoPermissao alteracao = new AlteracaoPermissao(usuario, LocalDateTime.now(), TipoOperacao.ALTERAR, null);
        alteracaoRepositorio.getListaAlteracaoPermissao().add(alteracao);
    }

    public void removerPermissao(Usuario usuario){
        usuario.setRole(null);
        AlteracaoPermissao alteracao = new AlteracaoPermissao(usuario, LocalDateTime.now(), TipoOperacao.REMOVER, null);
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
