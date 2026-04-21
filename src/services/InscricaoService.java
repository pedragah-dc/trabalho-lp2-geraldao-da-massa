package services;

import entitades.Discente;
import entitades.Inscricao;
import entitades.Oportunidade;
import entitades.enums.StatusInscricao;


public class InscricaoService {
    //o discente pode criar varias inscricoes, entao faz sentido criar uma inscricao no construtor?
    private Inscricao modelSubs;
    //seria melhor ter uma lista de inscricoes e lidar com cada uma
    public InscricaoService(Oportunidade op, Discente dis, String motiv){
        //acoplamento forte
        modelSubs = new Inscricao(op, dis, motiv);
        setStatus(StatusInscricao.PENDENTE);//poderia ser o valor default
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
