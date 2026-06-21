package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Discente extends Usuario {
    private String matricula;
    private Integer semestreAtual;
    private Curso curso;
    private List<Oportunidade> listaDeOp;


    public Discente(Integer id, String nome, String email, String senha, Papel papel,
                    Boolean ativo, List<Oportunidade> listaOp, String matricula, Integer semestreAtual,
                    Curso curso, RolesUsuario role) {

        super(id, nome, email, senha, papel, ativo, role);
        this.matricula = matricula;
        this.semestreAtual = semestreAtual;
        this.curso = curso;
        this.listaDeOp = listaOp;
    }
}
