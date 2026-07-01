package geraldao_da_massa.demo.DTOs.inputs;


import geraldao_da_massa.demo.entities.enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlteracaoPermissaoRequestDTO {
    private Integer idPermissao;
    private Integer idUsuario;
    private TipoOperacao tipoOperacao;
    private String nomePPC;
}
