package services;

import entity.SolicitacaoOportunidade;
import repository.SolicitacaoOportunidadeRepository;
import entity.enums.StatusSolicitacaoOportunidade;

import java.time.LocalDateTime;
import java.util.List;

public class CoordenadorService {

	private final SolicitacaoOportunidadeRepository solicitacaoRepo;

	public CoordenadorService(SolicitacaoOportunidadeRepository solicitacaoRepo) {
		this.solicitacaoRepo = solicitacaoRepo;
	}

	public List<SolicitacaoOportunidade> listarSolicitacoesPendentesAtrasadas() {
		return solicitacaoRepo.listarSolicitacoesPendentesAtrasadas();
	}

    public boolean solicitarReenvio(SolicitacaoOportunidade solicitacao) {
        solicitacao.setStatus(StatusSolicitacaoOportunidade.ESPERANDO_REENVIO);
        solicitacao.setDataIndeferimento(LocalDateTime.now());
        return true;
    }

    public boolean aprovarSolicitacao(SolicitacaoOportunidade solicitacao) {
        if (solicitacao.getStatus() == StatusSolicitacaoOportunidade.PENDENTE || solicitacao.getStatus() == StatusSolicitacaoOportunidade.ESPERANDO_REENVIO) {
            solicitacao.setDataAprovacao(LocalDateTime.now());
            solicitacao.setStatus(StatusSolicitacaoOportunidade.APROVADA);
            return true;
        }
        return false;
    }

    public boolean reprovarSolicitacao(SolicitacaoOportunidade solicitacao) {
        solicitacao.setStatus(StatusSolicitacaoOportunidade.REPROVADA);
        return true;
    }
}
