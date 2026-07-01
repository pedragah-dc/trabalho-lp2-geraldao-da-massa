package geraldao_da_massa.demo.DTOs.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DiscenteDiretorRequestDTO extends DiscenteRequestDTO {
    @NotBlank(message = "O grupo é obrigatório.")
    private Integer idGrupo;

    @NotBlank(message = "O cargo é obrigatório.")
    private String cargo;

    public DiscenteDiretorRequestDTO(String nome, String email, String senha, String matricula, Integer semestreAtual, Integer idCurso ,Integer idGrupo, String cargo) {
        super(nome, email, senha, matricula, semestreAtual, idCurso);
        this.idGrupo = idGrupo;
        this.cargo = cargo;
    }
}

