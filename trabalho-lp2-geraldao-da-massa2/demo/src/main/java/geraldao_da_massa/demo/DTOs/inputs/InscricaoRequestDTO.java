package geraldao_da_massa.demo.DTOs.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Usado em: POST /oportunidades/{idOportunidade}/inscricoes
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoRequestDTO {

    @NotNull(message = "O ID do discente é obrigatório.")
    private Integer discenteId;

    @NotBlank(message = "A motivação é obrigatória.")
    private String motivacao;
}
