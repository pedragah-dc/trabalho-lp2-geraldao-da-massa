package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.Docente;
import geraldao_da_massa.demo.entity.Papel;
import geraldao_da_massa.demo.entity.enums.RolesUsuario;

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
                RolesUsuario.DOCENTE,
                "1234567",
                "Computação"
        ));
    }
}
