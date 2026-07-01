package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.DiscenteDiretor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiscenteDiretorResponseDTO extends DiscenteResponseDTO{
    String nomeGrupo;
    String cargo;
    private Date dataInicio;
    private Date dataFim;


    DiscenteDiretorResponseDTO(DiscenteDiretor diretor){
        super(diretor);
        this.nomeGrupo = diretor.getGrupo().getNome();
        this.cargo = diretor.getCargo();
        this.dataInicio = diretor.getDataInicio();
        this.dataFim = diretor.getDataFim();
    }
}
