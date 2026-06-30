package geraldao_da_massa.demo.DTOs.inputs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DiscenteRequestDTO extends UsuarioRequestDTO {
    @NotBlank
    @NotNull
    private String matricula;//isso nao é feito no service atraves de um calculo? enfim
    @NotBlank
    @NotNull
    private String cursoNome;
    @NotNull
    private Integer semestreAtual;

}
