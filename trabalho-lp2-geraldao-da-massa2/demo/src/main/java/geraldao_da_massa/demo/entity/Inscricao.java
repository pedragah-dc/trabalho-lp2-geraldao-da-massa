package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.StatusInscricao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "inscricoes")
@Getter
@Setter
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idInscricao;
    //pergunta: inscricao só pode se inscrever numa oportunidade, e oportunidades podem ter varias inscricoes?
    @OneToOne
    private Oportunidade oportunidade;
    //TODO pergunta dps
    @OneToOne
    private Discente discente;
    private StatusInscricao status;
    private String motivacao;
    @OneToOne
    private Inscricao sustituidoPor; // RF017: aponta para a inscrição do substituto

    public Inscricao(Oportunidade oportunidade, Discente discente, String motivacao) {
        this.oportunidade = oportunidade;
        this.discente = discente;
        this.motivacao = motivacao;
        this.status = StatusInscricao.PENDENTE;
    }

    @Override
    public String toString() {
        return "Inscricao{discente='" + discente.getNome() + "', status=" + status + "}";
    }
}
