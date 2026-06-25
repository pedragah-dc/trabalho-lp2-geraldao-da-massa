package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.DTOs.UsuarioRequestDTO;
import geraldao_da_massa.demo.entities.AlteracaoPermissao;
import geraldao_da_massa.demo.entities.Usuario;
import geraldao_da_massa.demo.entities.enums.RolesUsuario;
import geraldao_da_massa.demo.entities.enums.TipoOperacao;
import geraldao_da_massa.demo.repositories.AlteracaoPermissaoRepository;

import java.time.LocalDateTime;

import geraldao_da_massa.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    private static Integer numUsuarios;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AlteracaoPermissaoRepository alteracaoRepositorio;




    public UsuarioService(){
        this.numUsuarios = 0;
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
        alteracaoRepositorio.save(alteracao);
    }

    public void mudarPermissao(Usuario usuario, RolesUsuario role){
        usuario.setRole(role);
        AlteracaoPermissao alteracao = new AlteracaoPermissao(usuario, LocalDateTime.now(), TipoOperacao.ALTERAR, null);
        alteracaoRepositorio.save(alteracao);
    }

    public void removerPermissao(Usuario usuario){
        usuario.setRole(null);
        AlteracaoPermissao alteracao = new AlteracaoPermissao(usuario, LocalDateTime.now(), TipoOperacao.REMOVER, null);
        alteracaoRepositorio.save(alteracao);
    }

    public Usuario autocadastroUsuario(UsuarioRequestDTO userDTO){
        //TODO FAZER VERIFICACAO
        Usuario user = new Usuario();
        user.setNome(userDTO.getNome());
        user.setSenha(userDTO.getSenha());
        user.setEmail(userDTO.getEmail());
        usuarioRepository.save(user);
        return user;
    }

    public void excluirUsuario(Usuario usuario, UsuarioService servicoUsuario){
        usuario.setSenha(null);
        usuario.setNome(null);
        usuario.setEmail(null);
        usuario.setId(null);

        Integer novoId = servicoUsuario.getNumUsuarios() - 1;
        servicoUsuario.setNumUsuarios(novoId);
    }
    //metodo pra encontrar um usuario
    public Usuario findUsuario(String name){
        return usuarioRepository.findByNome(name);
    }
}
