package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.StatusAproveitamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aproveitamento")
@NoArgsConstructor

@Getter
@Setter
public class Aproveitamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
