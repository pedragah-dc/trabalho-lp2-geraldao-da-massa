package geraldao_da_massa.demo.DTOs;

import geraldao_da_massa.demo.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private String nome;
    private String email;
    public UsuarioResponseDTO(Usuario user){
        nome = user.getNome();
        email = user.getEmail();
    }
}
