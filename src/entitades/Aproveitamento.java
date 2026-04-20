package entitades;

import entitades.enums.StatusAproveitamento;

public class Aproveitamento {
    private Discente discente;
    private String descricao;
    private String instituicao;
    private Integer horas;
    private Enum<StatusAproveitamento> status;

    public Aproveitamento(Discente discente, String descricao, String instituicao, Integer horas, Enum<StatusAproveitamento> status) {
        this.discente = discente;
        this.descricao = descricao;
        this.instituicao = instituicao;
        this.horas = horas;
        this.status = status;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Enum<StatusAproveitamento> getStatus() {
        return status;
    }

    public void setStatus(Enum<StatusAproveitamento> status) {
        this.status = status;
    }
}
