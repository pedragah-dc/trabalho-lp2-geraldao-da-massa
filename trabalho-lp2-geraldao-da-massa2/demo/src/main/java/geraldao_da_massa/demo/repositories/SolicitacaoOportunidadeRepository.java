package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Discente;
import geraldao_da_massa.demo.entities.SolicitacaoOportunidade;
import geraldao_da_massa.demo.entities.enums.StatusSolicitacaoOportunidade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoOportunidadeRepository {
    public static List<SolicitacaoOportunidade> listaSolicitacoes;

    public SolicitacaoOportunidadeRepository(List<SolicitacaoOportunidade> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    public SolicitacaoOportunidade criarSolicitacaoOportunidade(SolicitacaoOportunidade solicitacao){
        this.listaSolicitacoes.add(solicitacao);
        return solicitacao;
    }

    public List<SolicitacaoOportunidade> listarSolicitacoesPendentes(){
        List<SolicitacaoOportunidade> solicitacoesPendentes = new ArrayList<>();
        for (SolicitacaoOportunidade solicitacao: this.listaSolicitacoes){
            if(solicitacao.getStatus() == StatusSolicitacaoOportunidade.PENDENTE)
                solicitacoesPendentes.add(solicitacao);
        }
        return solicitacoesPendentes;
    }

    public List<SolicitacaoOportunidade> listarSolicitacoesPendentesAtrasadas(){
        List<SolicitacaoOportunidade> atrasadas = new ArrayList<>();
        LocalDateTime agora = LocalDateTime.now();
        for (SolicitacaoOportunidade solicitacao: this.listaSolicitacoes){
            if(solicitacao.getStatus() == StatusSolicitacaoOportunidade.PENDENTE &&
                    solicitacao.getDataSolicitacao() != null &&
                    agora.isAfter(solicitacao.getDataSolicitacao().plusDays(10))) {
                atrasadas.add(solicitacao);
            }
        }
        return atrasadas;
    }

    public List<SolicitacaoOportunidade> listarPorDiscente(Discente discente){
        List<SolicitacaoOportunidade> resultado = new ArrayList<>();
        for (SolicitacaoOportunidade s: this.listaSolicitacoes){
            if (s.getDiscenteSolicitante() != null && s.getDiscenteSolicitante().equals(discente)){
                resultado.add(s);
            }
        }
        return resultado;
    }
}
