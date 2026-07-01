package geraldao_da_massa.demo.DTOs.inputs;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoRequestDTO {
    @NotBlank(message="O conteúdo da notificação é obrigatório.")
    private String mensagem;

    @NotBlank(message="O destinatário da notificação é obrigatório")
    private String destinatario;
}
