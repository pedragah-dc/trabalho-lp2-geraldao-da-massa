package geraldao_da_massa.demo.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;



@Entity
@Getter
@Setter
public class Certificado {
    private String hash;
    private Discente discente;
    private Oportunidade oportunidade;
    private LocalDateTime dataEmissao;
    private Integer cargaHoraria;
    private String caminhoArquivo;
    private Boolean assinado;

    public Certificado(String hash, Discente discente, Oportunidade oportunidade,
                       LocalDateTime dataEmissao, Integer cargaHoraria,
                       String caminhoArquivo, Boolean assinado) {
        this.hash = hash;
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.dataEmissao = dataEmissao;
        this.cargaHoraria = cargaHoraria;
        this.caminhoArquivo = caminhoArquivo;
        this.assinado = assinado;
    }


    @Override
    public String toString() {
        return "Certificados{" +
                "hash='" + hash + '\'' +
                ", discente=" + (discente != null ? discente.getNome() : "null") +
                ", oportunidade=" + (oportunidade != null ? oportunidade.getTitulo() : "null") +
                ", dataEmissao=" + dataEmissao +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}
