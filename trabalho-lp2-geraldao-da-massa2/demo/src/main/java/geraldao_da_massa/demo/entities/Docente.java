package geraldao_da_massa.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;

@SuperBuilder
@Entity
@Table(name = "Docente")
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "chavefk")
@Getter
@Setter
public class Docente extends Usuario {

    private String siape;
    private String departamento;
    private HashSet<Grupo> grupos;
    @OneToOne(optional = false)
    private Oportunidade oportunidade;

}
