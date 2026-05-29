package repository;

import entity.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoRepository {
    public List<Curso> listaCursos; // corrigido: era static, causaria problema em testes

    public CursoRepository() {
        listaCursos = new ArrayList<>();
        listaCursos.add(new Curso("Ciência da Computação", 6767, 500, "PCC_Atual"));
    }
}
