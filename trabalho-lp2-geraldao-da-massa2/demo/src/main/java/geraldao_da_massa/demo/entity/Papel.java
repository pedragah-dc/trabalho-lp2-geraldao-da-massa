package geraldao_da_massa.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class Papel {
    @Id
    private Integer id;
    private String descricao;

    public Papel(String descricao) {
        this.descricao = descricao;
    }
}
