package geraldao_da_massa.demo.DTOs.inputs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoRequestDTO {

    @NotNull(message = "O ID da oportunidade é obrigatório.")
    private Integer idOportunidade;

    @NotNull(message = "O ID do discente é obrigatório.")
    private Integer idDiscente;

    @NotBlank(message = "Você precisa escrever uma justificativa/motivação para se inscrever.")
    private String motivacao;

}
