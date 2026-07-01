package geraldao_da_massa.demo.DTOs.inputs;

import geraldao_da_massa.demo.entities.Discente;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DiscenteRequestDTO extends UsuarioRequestDTO {

    @NotBlank(message = "A matrícula é obrigatória.")
    private String matricula;

    @NotNull(message = "O semestre atual é obrigatório.")
    @Min(value = 1, message = "O semestre mínimo é 1.")
    @Max(value = 14, message = "Semestre inválido.")
    private Integer semestreAtual;

    @NotNull(message = "O ID do curso é obrigatório.")
    private Integer cursoId;

    public DiscenteRequestDTO(String nome, String email, String senha, String matricula, Integer semestreAtual, Integer cursoId) {
        super(nome, email, senha);
        this.matricula = matricula;
        this.semestreAtual = semestreAtual;
        this.cursoId = cursoId;
    }
}