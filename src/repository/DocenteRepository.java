package repository;

import entity.Docente;
import entity.Papel;

import java.util.ArrayList;
import java.util.List;

public class DocenteRepository {
    public List<Docente> listaDocentes;

    public DocenteRepository(){
        listaDocentes = new ArrayList<Docente>();
        listaDocentes.add(new Docente(
                123,
                "Geraldo",
                "geraldo_algumacoisa@docente.ufma.br",
                "54321",
                new Papel("Dar aula"),
                true,
                "???",
                "Computação"
        ));
    }
}
