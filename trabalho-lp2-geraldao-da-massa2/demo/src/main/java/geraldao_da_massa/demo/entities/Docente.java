package geraldao_da_massa.demo.entities;

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
//    private HashSet<Grupo> grupos;
    @OneToOne
    private Oportunidade oportunidade;
}
