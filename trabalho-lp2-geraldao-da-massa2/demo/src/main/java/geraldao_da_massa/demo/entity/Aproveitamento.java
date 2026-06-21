package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.StatusAproveitamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter

public class Aproveitamento {
    private Discente discente;
    private String descricao;
    private String instituicao;
    private Integer horas;
    private @Enumerated StatusAproveitamento status; // corrigido: era Enum<StatusAproveitamento>

    public Aproveitamento(Discente discente, String descricao, String instituicao,
                          Integer horas, StatusAproveitamento status) {
        this.discente = discente;
        this.descricao = descricao;
        this.instituicao = instituicao;
        this.horas = horas;
        this.status = status;
    }
}
