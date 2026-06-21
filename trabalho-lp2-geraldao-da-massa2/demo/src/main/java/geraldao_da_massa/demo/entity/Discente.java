package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Table(name= "discente")
@SuperBuilder
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "chavefk")

@Getter
@Setter
public class Discente extends Usuario {
    private String matricula;
    private Integer semestreAtual;
    @OneToOne
    private Curso curso;
    @OneToMany
    private List<Oportunidade> listaDeOp;

//    public Discente(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, List<Oportunidade> listaOp, String matricula, Integer semestreAtual, Curso curso, Enum<RolesUsuario> role) {
//        super(id, nome, email, senha, papel, ativo, role);
//        this.matricula = matricula;
//        this.semestreAtual = semestreAtual;
//        this.curso = curso;
//        this.listaDeOp = listaOp;
//    }
}
