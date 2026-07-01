package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.Discente;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DiscenteResponseDTO extends UsuarioResponseDTO{
    private String matricula;
    private Integer semestreAtual;
    private Integer idCurso;
    private String nomeCurso;


    public DiscenteResponseDTO(Discente discente){
        super(discente);
        this.matricula = discente.getMatricula();
        this.semestreAtual = discente.getSemestreAtual();
        this.idCurso = discente.getCurso().getIdCurso();
        this.nomeCurso = discente.getCurso().getNome();
    }
}
