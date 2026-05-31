package repository;

import entity.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoRepository {
    public List<Curso> listaCursos;

    public CursoRepository() {
        listaCursos = new ArrayList<Curso>();
        listaCursos.add(new Curso("Ciência da Computação",
                6767,
                500,
                "PCC_Atual"));
    }


    public List<Curso> getListaCursos(){
        return listaCursos;
    }
}
