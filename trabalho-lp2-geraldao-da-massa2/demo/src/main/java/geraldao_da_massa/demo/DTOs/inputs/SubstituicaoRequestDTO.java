package geraldao_da_massa.demo.DTOs.inputs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Usado em: PATCH /inscricoes/{idInscricao}/substituir (RF017)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubstituicaoRequestDTO {

    @NotNull(message = "O ID do novo discente é obrigatório.")
    private Integer novoDiscenteId;
}
