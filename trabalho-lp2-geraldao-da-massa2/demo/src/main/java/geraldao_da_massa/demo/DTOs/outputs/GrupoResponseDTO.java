package geraldao_da_massa.demo.DTOs.outputs;

import geraldao_da_massa.demo.DTOs.inputs.GrupoRequestDTO;
import lombok.Data;

@Data
public class GrupoResponseDTO {
    private String nomeDogrupo;
    private String nomeDoResponsavel;
    private String email;

    public GrupoResponseDTO(GrupoRequestDTO dto){
        nomeDogrupo= dto.getNome();
        email = dto.getEmail();
    }
}
