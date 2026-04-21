package entity;

import java.time.LocalDateTime;

public class Certificado {
    String uuidHash;
    Discente discente;
    Oportunidade oportunidade;
    LocalDateTime dataEmissao;
    Integer horas;
    String certificadoPath;
    Boolean statusAssinatura;

    public Certificado(String uuidHash, Discente discente, Oportunidade oportunidade, LocalDateTime dataEmissao, Integer horas, String certificadoPath, Boolean statusAssinatura) {
        this.uuidHash = uuidHash;
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.dataEmissao = dataEmissao;
        this.horas = horas;
        this.certificadoPath = certificadoPath;
        this.statusAssinatura = statusAssinatura;
    }

    public String getUuidHash() {
        return uuidHash;
    }

    public void setUuidHash(String uuidHash) {
        this.uuidHash = uuidHash;
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

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public String getCertificadoPath() {
        return certificadoPath;
    }

    public void setCertificadoPath(String certificadoPath) {
        this.certificadoPath = certificadoPath;
    }

    public Boolean getStatusAssinatura() {
        return statusAssinatura;
    }

    public void setStatusAssinatura(Boolean statusAssinatura) {
        this.statusAssinatura = statusAssinatura;
    }
}
