package geraldao_da_massa.demo.DTOs.inputs;

import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MembroGrupoRequestDTO {
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    private CargoNoGrupo cargo;
}
