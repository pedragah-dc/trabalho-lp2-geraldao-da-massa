package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.Aproveitamento;
import geraldao_da_massa.demo.entity.enums.StatusAproveitamento;

import java.util.ArrayList;
import java.util.List;

public class AproveitamentoRepository {
    public List<Aproveitamento> listaAproveitamentos;

    public AproveitamentoRepository() {
        this.listaAproveitamentos = new ArrayList<>();
    }

    public List<Aproveitamento> getAproveitamentosPendentes() {
        List<Aproveitamento> pendentes = new ArrayList<>();
        for (Aproveitamento a : listaAproveitamentos) {
            if (a.getStatus() == StatusAproveitamento.PENDENTE) {
                pendentes.add(a);
            }
        }
        return pendentes;
    }
}
