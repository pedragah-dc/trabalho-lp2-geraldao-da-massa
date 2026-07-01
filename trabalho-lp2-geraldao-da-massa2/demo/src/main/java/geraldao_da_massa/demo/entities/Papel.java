package geraldao_da_massa.demo.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Papel {
    private String descricao;

    public Papel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
