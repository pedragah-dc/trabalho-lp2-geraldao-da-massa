package geraldao_da_massa.demo.DTOs.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificadoRequestDTO {
    @NotBlank(message="O hash do arquivo é obrigatório.")
    private String hash;

    @NotBlank(message="O caminho do arquivo é obrigatório.")
    private String caminhoArquivo;

}
