package repository;

import entity.Aproveitamento;

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
            if (a.getStatus() == entity.enums.StatusAproveitamento.PENDENTE) {
                pendentes.add(a);
            }
        }
        return pendentes;
    }
}
