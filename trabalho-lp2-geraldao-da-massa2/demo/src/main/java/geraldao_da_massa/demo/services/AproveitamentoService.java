package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entity.Aproveitamento;
import geraldao_da_massa.demo.entity.Discente;
import geraldao_da_massa.demo.entity.Docente;
import geraldao_da_massa.demo.entity.enums.StatusAproveitamento;
import geraldao_da_massa.demo.repository.AproveitamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AproveitamentoService {
    @Autowired
    private AproveitamentoRepository aproveitamentoRepository;



    public boolean solicitarAproveitamento(Discente discente, String descricao, String instituicao, Integer horas) {
    try {
        Aproveitamento novoAproveitamento = new Aproveitamento(discente, descricao, instituicao, horas, StatusAproveitamento.PENDENTE);
        aproveitamentoRepository.save(novoAproveitamento);
        System.out.println("Solicitação de aproveitamento de horas realizada!");
        return true;
    } catch (Exception e) {
        throw new RuntimeException("Erro ao solicitar aproveitamento: " + e.getMessage());
    }
    }

    public List<Aproveitamento> listarSolicitacoesAproveitamentos(Docente docente) {
        try {
            return (List<Aproveitamento>) aproveitamentoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar aproveitamentos: " + e.getMessage());
        }
    }
}
