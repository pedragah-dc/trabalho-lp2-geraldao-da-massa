package geraldao_da_massa.demo.DTOs.inputs;

import geraldao_da_massa.demo.entities.enums.StatusGrupo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GrupoRequestDTO {
    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull
    private String tipo;
    @NotBlank
    @NotNull
    private String email;//email?
    @NotBlank
    @NotNull
    private String descricao;


    private StatusGrupo status;

    @NotNull
    private int idResponsavel;
}
//too much writing slk