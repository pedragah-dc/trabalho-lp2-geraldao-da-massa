package geraldao_da_massa.demo.DTOs.inputs;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlteracaoPermissaoRequestDTO {
    @NotBlank
    private String role;
    @NotBlank
    private String siape;
}
