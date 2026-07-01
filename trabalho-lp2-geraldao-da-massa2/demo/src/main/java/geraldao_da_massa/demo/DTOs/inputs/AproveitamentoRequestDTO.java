package geraldao_da_massa.demo.DTOs.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AproveitamentoRequestDTO {
    @NotBlank(message="O ID do discente é obrigatório.")
    private String idDiscente;

    @NotBlank(message="A descrição é obrigatória.")
    private String descricao;

    @NotBlank(message="O nome da instituição é obrigatório.")
    private String instituicao;

    @NotBlank(message="A quantidade de horas é obrigatória")
    @Positive(message="A quantidade de horas deve ser maior do que zero.")
    private Integer horas;
}
