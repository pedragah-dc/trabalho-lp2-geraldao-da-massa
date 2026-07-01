package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.Notificacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoResponseDTO {
    private Integer id;
    private String mensagem;
    private String destinatario;
    private Boolean lida;


    NotificacaoResponseDTO(Notificacao noti){
        this.id = noti.getId();
        this.mensagem = noti.getMensagem();
        this.destinatario = noti.getDestinatario().getNome();
        this.lida = noti.getLida();
    }
}
