package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.DTOs.inputs.DiscenteRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Table(name= "discente")
@SuperBuilder
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "usuario")
@Data
public class Discente extends Usuario {
    private String matricula;
    private Integer semestreAtual;
    @OneToOne
    private Curso curso;
    @OneToMany
    private List<Oportunidade> listaDeOp;

    public Discente(DiscenteRequestDTO dto) {
        super(dto);
        matricula = dto.getMatricula();
        semestreAtual = dto.getSemestreAtual();

    }
}
