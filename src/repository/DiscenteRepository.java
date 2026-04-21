package repository;

import entity.Discente;
import entity.Papel;

import repository.CursoRepository;

import java.util.ArrayList;
import java.util.List;

public class DiscenteRepository {
    public List<Discente> listaDiscente;

    public DiscenteRepository() {
        listaDiscente = new ArrayList<Discente>();
        CursoRepository listaCurso = new CursoRepository();
        listaDiscente.add(new Discente(
                123,
                "Diego",
                "diego.ot@discente.ufma.br",
                "12345678",
                new Papel("Aprender"),
                true,
                "20251234",
                3,
                listaCurso.listaCursos.get(0)
        ));
    }
}
