package services;

import entity.Discente;
import entity.Oportunidade;
import entity.SolicitacaoOportunidade;
import entity.enums.StatusSolicitacaoOportunidade;
import repository.SolicitacaoOportunidadeRepository;

import java.time.LocalDateTime;
import java.util.List;

public class DiscenteService {
    public SolicitacaoOportunidade criarSolicitacaoOportunidade(Discente discente, Oportunidade oportunidade){
        try{
            SolicitacaoOportunidade solicitacao = new SolicitacaoOportunidade(discente, oportunidade);
            return solicitacao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean reenviarSolicitacaoOportunidade(SolicitacaoOportunidade solicitacao) throws Exception {
        if (solicitacao.getStatus() != StatusSolicitacaoOportunidade.ESPERANDO_REENVIO)
            throw new Exception("Não é possível realizar reenvio desta solicitação no momento.");
        
        // ficou grande demais entao vou explicar kkkkk
        // pega a data de agora e verifica se é depois do dia do indeferimento + 5 dias
        // o requisito fala que após o indeferimento tem 5 dias pra reenvio então ta fazendo exatamente isso
        if(LocalDateTime.now().isAfter(solicitacao.getDataIndeferimento().plusDays(5)))
            throw new Exception("Prazo para reenvio esgotado!");

        solicitacao.setStatus(StatusSolicitacaoOportunidade.PENDENTE);
        return true;
    }
    public List<SolicitacaoOportunidade> listarSolicitacoesDoDiscente(Discente discente, SolicitacaoOportunidadeRepository repo){
        return repo.listarPorDiscente(discente);
    }
}
