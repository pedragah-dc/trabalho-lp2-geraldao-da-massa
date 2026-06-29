package geraldao_da_massa.demo.DTOs.outputs;

import geraldao_da_massa.demo.entities.Certificado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificadoResponseDTO {

    private String hash;
    private String discente;
    private String oportunidade;
    private LocalDateTime dataEmissao;
    private Integer cargaHoraria;
    private String caminhoArquivo;

    public CertificadoResponseDTO(Certificado certificado) {
        this.hash = certificado.getHash();
        this.discente = certificado.getDiscente().getNome();
        this.oportunidade = certificado.getOportunidade().getTitulo();
        this.dataEmissao = certificado.getDataEmissao();
        this.cargaHoraria = certificado.getCargaHoraria();
        this.caminhoArquivo = certificado.getCaminhoArquivo();
    }
}
