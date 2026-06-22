package geraldao_da_massa.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Papel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
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
