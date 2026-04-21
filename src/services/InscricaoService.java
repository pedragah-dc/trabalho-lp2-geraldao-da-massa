package services;

import entity.Discente;
import entity.Inscricao;
import entity.Oportunidade;
import entity.enums.StatusInscricao;

import java.util.ArrayList;
import java.util.List;


public class InscricaoService {
    private List<Inscricao> listIncricoes;

    public InscricaoService() {
        listIncricoes = new ArrayList<Inscricao>();
    }
    public void criarInscricao(Oportunidade oportunidade, Discente discente, String motivo){
        Inscricao inscricao = new Inscricao(oportunidade, discente, motivo);
        listIncricoes.add(inscricao);
        inscricao.setStatus(StatusInscricao.PENDENTE);
    }

    public void aprovar(){

    }
    public void rejeitar(){

    }
    //metodos
    public String getInscricao(){
        return listIncricoes.get(0).getOportunidade().getTitulo();
    }

}
