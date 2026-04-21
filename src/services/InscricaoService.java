package services;

import entitades.Discente;
import entitades.Inscricao;
import entitades.Oportunidade;
import entitades.enums.StatusInscricao;

public class InscricaoService {
    //o discente pode criar varias inscricoes, entao faz sentido criar uma inscricao no construtor?
    private Inscricao modelSubs;

    public InscricaoService(Oportunidade op, Discente dis, StatusInscricao status, String motiv){
        modelSubs = new Inscricao(op, dis, motiv);
    }
    public void setStatus(StatusInscricao st){
        modelSubs.setStatus(st);
    }
    public Oportunidade getOportunidade() {
        return modelSubs.getOportunidade();
    }

    public void setOportunidade(Oportunidade oportunidade) {
        modelSubs.setOportunidade(oportunidade);
    }

    public void setDiscente(Discente discente) {
       modelSubs.setDiscente(discente);
    }


    //eu acho que é melhor usar o "StatusInscricao status"
    public Enum<StatusInscricao> getStatus() {
        return modelSubs.getStatus();
    }

    public void setStatus(Enum<StatusInscricao> status) {
        modelSubs.setStatus(status);
    }

    public String getMotivacao() {
       return modelSubs.getMotivacao();
    }

    public void setMotivacao(String motivacao) {
        modelSubs.setMotivacao(motivacao);
    }
    //quem rejeita é o docente:/
    public void aprovar(Discente dis){
        modelSubs.aprovar(dis);
    }
    public void rejeitar(){
        modelSubs.rejeitar();
    }

}
