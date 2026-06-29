package geraldao_da_massa.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notificacoes")
@Getter
@Setter
@NoArgsConstructor
//nao faço ideia do que isso faz ou como deveria se comportar
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String mensagem;
    @OneToOne
    private Usuario destinatario;
    private Boolean lida;

    public Notificacao(Integer id, String mensagem, Usuario destinatario, Boolean lida) {
        this.id = id;
        this.mensagem = mensagem;
        this.destinatario = destinatario;
        this.lida = lida;
    }
}
