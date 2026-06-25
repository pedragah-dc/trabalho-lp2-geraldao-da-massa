package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.StatusAproveitamento;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aproveitamentos")
@NoArgsConstructor

public class Aproveitamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAproveitamento;
    @OneToOne
    private Discente discente;
    private String descricao;
    private String instituicao;
    private Integer horas;
    private StatusAproveitamento status; // corrigido: era Enum<StatusAproveitamento>

    public Aproveitamento(Discente discente, String descricao, String instituicao,
                          Integer horas, StatusAproveitamento status) {
        this.discente = discente;
        this.descricao = descricao;
        this.instituicao = instituicao;
        this.horas = horas;
        this.status = status;
    }

    public Discente getDiscente() { return discente; }
    public void setDiscente(Discente discente) { this.discente = discente; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public Integer getHoras() { return horas; }
    public void setHoras(Integer horas) { this.horas = horas; }

    public StatusAproveitamento getStatus() { return status; }
    public void setStatus(StatusAproveitamento status) { this.status = status; }
}
