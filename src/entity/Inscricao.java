package entity;

import entity.enums.StatusInscricao;

public class Inscricao {
    Oportunidade oportunidade;
    Discente discente;//hmmm acoplamento
    Enum<StatusInscricao> status;
    String motivacao;

    public Inscricao(Oportunidade oportunidade, Discente discente, Enum<StatusInscricao> status, String motivacao) {
        this.oportunidade = oportunidade;
        this.discente = discente;
        this.status = status;
        this.motivacao = motivacao;
    }
    //eu não acho que faça sentido ter o enum de status declarado na criação de uma inscrição
    //vou criar um ou construtor
    public Inscricao(Oportunidade oportunidade, Discente discente, String motivacao){
        this.oportunidade = oportunidade;
        this.discente = discente;
        this.motivacao = motivacao;
    }

    public Oportunidade getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(Oportunidade oportunidade) {
        this.oportunidade = oportunidade;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public Enum<StatusInscricao> getStatus() {
        return status;
    }

    public void setStatus(Enum<StatusInscricao> status) {
        this.status = status;
    }

    public String getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(String motivacao) {
        this.motivacao = motivacao;
    }

    public void aprovar(Discente discente){
    }
    public void rejeitar(){

    }
}
