package geraldao_da_massa.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Classe que representa um Certificado emitido para um discente
 * em uma oportunidade (RF019)
 */
@Entity
@Table(name = "certificados")
@NoArgsConstructor
@Getter
@Setter
public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String hash;
    @OneToOne
    private Discente discente;
    @OneToOne
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
