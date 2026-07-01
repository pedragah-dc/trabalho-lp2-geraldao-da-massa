package geraldao_da_massa.demo.DTOs.inputs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembroGrupoRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório.")
    @Positive(message = "O ID do usuário deve ser maior que zero.")
    private Integer idUsuario;

    @NotNull(message = "O ID do grupo é obrigatório.")
    @Positive(message = "O ID do grupo deve ser maior que zero.")
    private Integer idGrupo;

    @NotBlank(message = "O cargo é obrigatório.")
    private String cargo;
}
