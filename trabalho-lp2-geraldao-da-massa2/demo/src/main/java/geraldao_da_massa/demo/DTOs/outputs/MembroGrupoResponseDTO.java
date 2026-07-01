package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.MembroGrupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MembroGrupoResponseDTO {
    private Integer idMembro;
    private String nomeMembro;
    private String cargo;
    private LocalDateTime dataMembro;

    MembroGrupoResponseDTO(MembroGrupo membroGrupo) {
        this.idMembro = membroGrupo.getIdMembro();
        this.nomeMembro = membroGrupo.getNome();
        this.cargo = membroGrupo.getCargo().name();
        this.dataMembro = membroGrupo.getTime();
    }
}
