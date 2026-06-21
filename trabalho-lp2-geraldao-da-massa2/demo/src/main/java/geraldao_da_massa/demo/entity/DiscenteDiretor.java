package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
public class DiscenteDiretor extends Discente {
    private Grupo grupo;
    private String cargo;
    private Date dataInicio;
    private Date dataFim;

    public DiscenteDiretor(Integer id, String nome, String email, String senha,
                           Papel papel, Boolean ativo, List<Oportunidade> listaDeOp, RolesUsuario role,
                           String matricula, Integer semestreAtual, Curso curso, Grupo grupo,
                           String cargo, Date dataInicio, Date dataFim) {

        super(id, nome, email, senha, papel, ativo, listaDeOp, matricula, semestreAtual, curso, role);
        this.grupo = grupo;
        this.cargo = cargo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
