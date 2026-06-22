package geraldao_da_massa.demo.DTOs;

import geraldao_da_massa.demo.entities.enums.RolesUsuario;
import lombok.*;

@Data
@AllArgsConstructor
public class UsuarioRequestDTO {
    public String nome;
    public String email;
    public String senha;
    public String papel;
    public Boolean ativo;
    public Enum<RolesUsuario> role;

}
