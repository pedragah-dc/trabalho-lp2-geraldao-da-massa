package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.AlteracaoPermissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlteracaoPermissaoResponseDTO {
    private Integer idPermissao;
    private String usuario;
    private LocalDateTime dataAlteracao;
    private String tipoOperacao;
    private String nomePPC;


    AlteracaoPermissaoResponseDTO(AlteracaoPermissao alteracao){
        this.idPermissao = alteracao.getIdPermissao();
        this.usuario = alteracao.getUsuario().getNome();
        this.dataAlteracao = alteracao.getDataAlteracao();
        this.tipoOperacao = alteracao.getTipoOperacao().name();
        this.nomePPC = alteracao.getNomePPC();
    }
}

