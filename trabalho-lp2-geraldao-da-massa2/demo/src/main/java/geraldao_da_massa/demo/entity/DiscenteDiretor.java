package geraldao_da_massa.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "discentediretor")
@SuperBuilder
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "chavefk")

@Getter
@Setter
public class DiscenteDiretor extends Discente {

    @OneToOne
    private Grupo grupo;
    private String cargo;
    private Date dataInicio;
    private Date dataFim;

//    public DiscenteDiretor(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, List<Oportunidade> listaDeOp, Enum<RolesUsuario> role, String matricula, Integer semestreAtual, Curso curso, Grupo grupo, String cargo, Date dataInicio, Date dataFim) {
//        super(id, nome, email, senha, papel, ativo, listaDeOp, matricula, semestreAtual, curso, role);
//        this.grupo = grupo;
//        this.cargo = cargo;
//        this.dataInicio = dataInicio;
//        this.dataFim = dataFim;
//    }
}
