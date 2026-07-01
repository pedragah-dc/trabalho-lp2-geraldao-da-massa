package geraldao_da_massa.demo.DTOs.outputs;

import geraldao_da_massa.demo.entities.Docente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocenteResponseDTO extends UsuarioResponseDTO {
    private String siape;
    private String departamento;

    public DocenteResponseDTO(Docente docente){
        super(docente);

        this.siape = docente.getSiape();
        this.departamento = docente.getDepartamento();
    }
}
