package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entity.Aproveitamento;
import geraldao_da_massa.demo.entity.Discente;
import geraldao_da_massa.demo.entity.Docente;
import geraldao_da_massa.demo.entity.enums.StatusAproveitamento;
import geraldao_da_massa.demo.repository.AproveitamentoRepository;

import java.util.List;

public class AproveitamentoService {
    private AproveitamentoRepository aproveitamentoRepository = new AproveitamentoRepository();

    public AproveitamentoService() {
        this.aproveitamentoRepository = new AproveitamentoRepository();
    }

    public boolean solicitarAproveitamento(Discente discente, String descricao, String instituicao, Integer horas) {
    try {
        Aproveitamento novoAproveitamento = new Aproveitamento(discente, descricao, instituicao, horas, StatusAproveitamento.PENDENTE);
        aproveitamentoRepository.listaAproveitamentos.add(novoAproveitamento);
        System.out.println("Solicitação de aproveitamento de horas realizada!");
        return true;
    } catch (Exception e) {
        throw new RuntimeException("Erro ao solicitar aproveitamento: " + e.getMessage());
    }
    }

    public List<Aproveitamento> listarSolicitacoesAproveitamentos(Docente docente) {
        try {
            return aproveitamentoRepository.getAproveitamentosPendentes();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar aproveitamentos: " + e.getMessage());
        }
    }
}
