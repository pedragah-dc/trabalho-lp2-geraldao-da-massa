package geraldao_da_massa.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name  ="curso")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCurso;
    private String nome;
    private Integer codigo;
    private Integer cargaHoraria;
    private String versaoPPC;


    //por que precisa disso? nao poderia ser um repo, ou melhor delegar isso pra um serviço?
    @OneToMany(mappedBy = "listappc")
    private List<AlteracaoPermissao> listaAlteracaoPPC;

    public Curso(String nome, Integer codigo, Integer cargaHoraria, String versaoPPC) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.versaoPPC = versaoPPC;
    }
}