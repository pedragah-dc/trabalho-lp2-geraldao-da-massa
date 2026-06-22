package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Aproveitamento;
import geraldao_da_massa.demo.entities.enums.StatusAproveitamento;

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
