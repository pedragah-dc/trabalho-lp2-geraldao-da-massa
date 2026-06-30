package geraldao_da_massa.demo.DTOs.outputs;

import geraldao_da_massa.demo.entities.Discente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DiscenteResponseDTO {

    private String nome;
    private String email;
    private String cursoNome;

    public DiscenteResponseDTO(Discente discente){
        nome = discente.getNome();
        email = discente.getNome();
        cursoNome = discente.getCurso().getNome();
    }
}
