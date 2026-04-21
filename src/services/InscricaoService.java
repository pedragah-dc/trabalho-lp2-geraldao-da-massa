package services;

import entity.Discente;
import entity.Inscricao;
import entity.Oportunidade;
import entity.enums.StatusInscricao;

import java.util.ArrayList;
import java.util.List;


public class InscricaoService {
    private List<Inscricao> modelSubs;

    public InscricaoService(Oportunidade op, Discente dis, String motiv){
        modelSubs = new ArrayList<Inscricao>();
    }
    public void criarInscricao(Oportunidade op, Discente id_dis, String motiv){
        Inscricao node = new Inscricao(op, id_dis, motiv);
        modelSubs.add(node);
        node.setStatus(StatusInscricao.PENDENTE);


    }


    public void aprovar(){

    }
    public void rejeitar(){

    }

}
