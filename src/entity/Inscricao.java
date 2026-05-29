package entity;

import entity.enums.StatusInscricao;

public class Inscricao {
    private Oportunidade oportunidade;
    private Discente discente;
    private StatusInscricao status;
    private String motivacao;
    private Inscricao sustituidoPor; // RF017: aponta para a inscrição do substituto

    public Inscricao(Oportunidade oportunidade, Discente discente, String motivacao) {
        this.oportunidade = oportunidade;
        this.discente = discente;
        this.motivacao = motivacao;
        this.status = StatusInscricao.PENDENTE;
    }

    public Oportunidade getOportunidade() { return oportunidade; }
    public void setOportunidade(Oportunidade oportunidade) { this.oportunidade = oportunidade; }

    public Discente getDiscente() { return discente; }
    public void setDiscente(Discente discente) { this.discente = discente; }

    public StatusInscricao getStatus() { return status; }
    public void setStatus(StatusInscricao status) { this.status = status; }

    public String getMotivacao() { return motivacao; }
    public void setMotivacao(String motivacao) { this.motivacao = motivacao; }

    public Inscricao getSustituidoPor() { return sustituidoPor; }
    public void setSustituidoPor(Inscricao sustituidoPor) { this.sustituidoPor = sustituidoPor; }

    @Override
    public String toString() {
        return "Inscricao{discente='" + discente.getNome() + "', status=" + status + "}";
    }
}
