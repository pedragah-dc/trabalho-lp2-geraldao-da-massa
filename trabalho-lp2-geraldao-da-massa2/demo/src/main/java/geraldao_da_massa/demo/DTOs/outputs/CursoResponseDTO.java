package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CursoResponseDTO {
    private Integer idCurso;
    private String nome;
    private Integer codigo;
    private Integer cargaHoraria;
    private String versaoPPC;



    CursoResponseDTO(Curso curso) {
        this.idCurso = curso.getIdCurso();
        this.nome = curso.getNome();
        this.codigo = curso.getCodigo();
        this.cargaHoraria = curso.getCargaHoraria();
        this.versaoPPC = curso.getVersaoPPC();
    }
}
