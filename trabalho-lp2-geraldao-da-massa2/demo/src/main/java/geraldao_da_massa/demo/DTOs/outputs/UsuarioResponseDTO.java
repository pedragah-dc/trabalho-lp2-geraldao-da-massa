package geraldao_da_massa.demo.DTOs.outputs;

import geraldao_da_massa.demo.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Integer id;
    private String nome;
    private String email;
    private Boolean ativo;
    private String descricao;
    private String role;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.ativo = usuario.getAtivo();
        this.descricao = usuario.getPapel().getDescricao();
        this.role = usuario.getRole().name();
    }
}