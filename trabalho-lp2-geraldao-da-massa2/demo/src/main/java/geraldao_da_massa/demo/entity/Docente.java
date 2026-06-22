package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;

@SuperBuilder
@Entity
@Table(name = "docente")
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "usuario")
@Getter
@Setter
public class Docente extends Usuario {

    private String siape;
    private String departamento;
    private HashSet<Grupo> grupos;
    @OneToOne(optional = false)
    private Oportunidade oportunidade;

}
