package geraldao_da_massa.demo.DTOs.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Usado em: PATCH /oportunidades/{id}/reprovar (RF012)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReprovacaoRequestDTO {

    @NotBlank(message = "O motivo da reprovação é obrigatório.")
    private String motivo;
}
