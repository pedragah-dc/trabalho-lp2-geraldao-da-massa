package geraldao_da_massa.demo.DTOs;

import geraldao_da_massa.demo.entities.enums.RolesUsuario;
import lombok.*;

@Data
@AllArgsConstructor
public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private String papel;
    private Boolean ativo;
    private RolesUsuario role;

}
