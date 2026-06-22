package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entities.AlteracaoPermissao;
import geraldao_da_massa.demo.entities.Curso;
import geraldao_da_massa.demo.entities.Docente;
import geraldao_da_massa.demo.entities.SolicitacaoOportunidade;
import geraldao_da_massa.demo.entities.enums.StatusSolicitacaoOportunidade;
import geraldao_da_massa.demo.repositories.SolicitacaoOportunidadeRepository;

import java.time.LocalDateTime;
import java.util.List;

public class CoordenadorService {

	private final SolicitacaoOportunidadeRepository solicitacaoRepo;

	public CoordenadorService(SolicitacaoOportunidadeRepository solicitacaoRepo) {
		this.solicitacaoRepo = solicitacaoRepo;
	}

    public void cadastrarPPC(Curso curso, String versaoPPC, Integer cargaHoraria, Docente coordenador){
        
        AlteracaoPermissao alteracaoPermissao = new AlteracaoPermissao(coordenador, LocalDateTime.now(), null, curso.getVersaoPPC());
        curso.setVersaoPPC(versaoPPC);
        curso.setCargaHoraria(cargaHoraria);
        curso.getListaAlteracaoPPC().add(alteracaoPermissao);

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
