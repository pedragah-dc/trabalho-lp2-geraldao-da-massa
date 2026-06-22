package geraldao_da_massa.demo.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notificacao {
    private Integer id;
    private String mensagem;
    private Usuario destinatario;
    private Boolean lida;

    public Notificacao(Integer id, String mensagem, Usuario destinatario, Boolean lida) {
        this.id = id;
        this.mensagem = mensagem;
        this.destinatario = destinatario;
        this.lida = lida;
    }
}
