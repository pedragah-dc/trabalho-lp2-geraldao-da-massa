package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entity.AlteracaoPermissao;
import geraldao_da_massa.demo.entity.Usuario;
import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import geraldao_da_massa.demo.entity.enums.TipoOperacao;
import geraldao_da_massa.demo.repository.AlteracaoPermissaoRepository;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
@Getter
@Setter

public class UsuarioService {
    private Integer numUsuarios;

    private AlteracaoPermissaoRepository alteracaoRepositorio;



    public UsuarioService(){
        this.numUsuarios = 0;
        this.alteracaoRepositorio = new AlteracaoPermissaoRepository();
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
