package services;

import entity.Discente;
import entity.Inscricao;
import entity.Oportunidade;
import entity.enums.StatusInscricao;

import java.util.ArrayList;
import java.util.List;


public class InscricaoService {
    private List<Inscricao> modelSubs;

    public InscricaoService() {
        modelSubs = new ArrayList<Inscricao>();
    }
    public void criarInscricao(Oportunidade op, Discente id_dis, String motiv){
        Inscricao node = new Inscricao(op, id_dis, motiv);
        modelSubs.add(node);
        node.setStatus(StatusInscricao.PENDENTE);
        System.out.println("INSCRICAO CRIADA COM SUCESSO");
    }

    public void aprovar(){

    }
    public void rejeitar(){

    }
    //metodos
    public String getInscricao(){
        return modelSubs.get(0).getOportunidade().getTitulo();
    }

}
