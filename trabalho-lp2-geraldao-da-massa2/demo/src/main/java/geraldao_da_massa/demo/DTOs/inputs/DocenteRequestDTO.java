package geraldao_da_massa.demo.DTOs.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DocenteRequestDTO extends UsuarioRequestDTO {
    @NotBlank(message = "O SIAPE é obrigatório.")
    private String siape;

    @NotBlank(message = "O departamento é obrigatório.")
    private String departamento;

    public DocenteRequestDTO(String nome, String email, String senha, String siape, String departamento) {
        super(nome, email, senha);
        this.siape = siape;
        this.departamento = departamento;
    }
}
