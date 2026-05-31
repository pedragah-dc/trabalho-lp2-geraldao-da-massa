package repository;

import entity.Discente;
import entity.Inscricao;
import entity.Oportunidade;
import entity.enums.StatusInscricao;

import java.util.ArrayList;
import java.util.List;

public class InscricaoRepository {

    private List<Inscricao> inscricoes = new ArrayList<>();

    public void salvar(Inscricao inscricao) {
        inscricoes.add(inscricao);
    }

    public List<Inscricao> listarTodas() {
        return new ArrayList<>(inscricoes);
    }

    public List<Inscricao> listarPorOportunidade(Oportunidade oportunidade) {
        List<Inscricao> resultado = new ArrayList<>();
        for (Inscricao i : inscricoes) {
            if (i.getOportunidade().equals(oportunidade)) {
                resultado.add(i);
            }
        }
        return resultado;
    }

    public List<Inscricao> listarAprovadosPorOportunidade(Oportunidade oportunidade) {
        List<Inscricao> resultado = new ArrayList<>();
        for (Inscricao i : inscricoes) {
            if (i.getOportunidade().equals(oportunidade)
                    && i.getStatus() == StatusInscricao.APROVADO) {
                resultado.add(i);
            }
        }
        return resultado;
    }

    public Inscricao buscarPorDiscenteEOportunidade(Discente discente, Oportunidade oportunidade) {
        for (Inscricao i : inscricoes) {
            if (i.getDiscente().equals(discente) && i.getOportunidade().equals(oportunidade)) {
                return i;
            }
        }
        return null;
    }
}
