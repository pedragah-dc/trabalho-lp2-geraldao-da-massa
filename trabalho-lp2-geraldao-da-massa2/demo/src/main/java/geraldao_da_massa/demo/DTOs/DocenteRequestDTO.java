package geraldao_da_massa.demo.DTOs;

import geraldao_da_massa.demo.entities.enums.RolesUsuario;
import lombok.Data;

@Data
public class DocenteRequestDTO extends UsuarioRequestDTO {
    private String siape;
    private String departamento;

    public DocenteRequestDTO(String nome, String email, String senha, String papel, Boolean ativo, Enum<RolesUsuario> role, String siape, String departamento) {
        super(nome, email, senha, papel, ativo, role);
        this.siape = siape;
        this.departamento = departamento;
    }
}
