package geraldao_da_massa.demo.DTOs.inputs;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoOportunidadeRequestDTO {
    @NotNull(message = "O ID de discente é obrigatório.")
    @Positive(message = "O ID de discente deve ser maior que zero.")
    private Integer idDiscente;

    @NotNull(message = "O ID da oportunidade é obrigatório.")
    @Positive(message = "O ID da oportunidade deve ser maior que zero.")
    private Integer idOportunidade;
}
