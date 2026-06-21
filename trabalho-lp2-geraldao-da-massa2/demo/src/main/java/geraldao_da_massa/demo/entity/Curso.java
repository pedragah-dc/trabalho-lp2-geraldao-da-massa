package geraldao_da_massa.demo.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Entity
@Getter
@Setter
public class Curso {
    private String nome;
    private Integer codigo;
    private Integer cargaHoraria;
    private String versaoPPC;
    private List<AlteracaoPermissao> listaAlteracaoPPC;

    public Curso(String nome, Integer codigo, Integer cargaHoraria, String versaoPPC) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.versaoPPC = versaoPPC;
    }
}
