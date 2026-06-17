package repository;

import entity.Docente;
import entity.Oportunidade;
import entity.enums.TiposModalidade;
import entity.enums.TiposOportunidade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OportunidadeRepository {

    private List<Oportunidade> listOportunidades;

    public OportunidadeRepository() {
        listOportunidades = new ArrayList<>();

        // Dados de exemplo para testes
        // Agora o construtor de Oportunidade não recebe mais StatusOportunidade
        // porque o status inicial é sempre RASCUNHO (definido internamente)
        List<Docente> docentes = new DocenteRepository().listaDocentes;
        if (!docentes.isEmpty()) {
            Docente docente = docentes.get(0);
            for (int i = 0; i < 3; i++) {
                listOportunidades.add(new Oportunidade(
                        "Oportunidade Exemplo " + i,
                        "Descrição de teste " + i,
                        TiposOportunidade.PROJETO,
                        TiposModalidade.HIBRIDO,
                        48,
                        20,
                        LocalDateTime.now().plusDays(10),
                        LocalDateTime.now().plusDays(40),
                        LocalDateTime.now().plusDays(1),
                        LocalDateTime.now().plusDays(8),
                        docente,
                        docente
                ));
            }
        }
    }

    public void salvar(Oportunidade oportunidade) {
        listOportunidades.add(oportunidade);
    }

    public List<Oportunidade> listarTodas() {
        return new ArrayList<>(listOportunidades);
    }

    public Oportunidade buscarPorTitulo(String titulo) {
        for (Oportunidade op : listOportunidades) {
            if (op.getTitulo().equalsIgnoreCase(titulo)) {
                return op;
            }
        }
        return null;
    }
}
