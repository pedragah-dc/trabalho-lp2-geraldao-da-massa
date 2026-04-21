package repository;

import entity.Docente;
import entity.Oportunidade;
import entity.Usuario;
import entity.enums.StatusOportunidade;
import entity.enums.TiposModalidade;
import entity.enums.TiposOportunidade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
public class OportunidadeRepository {
    public List<Oportunidade> listaOportunidades;

    public OportunidadeRepository() {
        List<Docente> listaDocentes = new DocenteRepository().listaDocentes;
        listaOportunidades = new ArrayList<Oportunidade>();
        for(int i = 0; i < 5; i++){
            listaOportunidades.add(
                    new Oportunidade(
                            "Oportunidade " + i,
                            "Teste",
                            TiposOportunidade.PROJETO,
                            TiposModalidade.HIBRIDO,
                            48,
                            100,
                            StatusOportunidade.PENDENTE,
                            LocalDateTime.now(),
                            LocalDateTime.now().plusDays(3),
                            listaDocentes.get(0),
                            listaDocentes.get(0)
                    )
            );
        }
    }
}
