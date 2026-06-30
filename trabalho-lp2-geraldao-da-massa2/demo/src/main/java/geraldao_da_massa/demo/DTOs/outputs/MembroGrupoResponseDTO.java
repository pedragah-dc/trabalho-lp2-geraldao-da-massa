package geraldao_da_massa.demo.DTOs.outputs;

import geraldao_da_massa.demo.entities.MembroGrupo;
import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;
import lombok.Data;

@Data
public class MembroGrupoResponseDTO {
    private String nome;
    private String nomeGrupo;
    private CargoNoGrupo cargo;

    public MembroGrupoResponseDTO(MembroGrupo membro){
        nome = membro.getNome();
        nomeGrupo = membro.getGrupo().getNome();
        cargo = membro.getCargo();
    }
}
