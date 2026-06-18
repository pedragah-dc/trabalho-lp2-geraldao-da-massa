package geraldao_da_massa.demo.entity;

import java.time.LocalDateTime;

/**
 * Classe que representa um Certificado emitido para um discente
 * em uma oportunidade (RF019)
 */
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public Oportunidade getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(Oportunidade oportunidade) {
        this.oportunidade = oportunidade;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public Boolean getAssinado() {
        return assinado;
    }

    public void setAssinado(Boolean assinado) {
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
