package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.StatusInscricao;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Inscricao {
    private Oportunidade oportunidade;
    private Discente discente;
    private StatusInscricao status;
    private String motivacao;
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
