package geraldao_da_massa.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name  ="cursos")
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


    //nao sei se essa classe deveria apontar de volta pro curso
    @OneToMany
    private List<AlteracaoPermissao> listaAlteracaoPPC;

    public Curso(String nome, Integer codigo, Integer cargaHoraria, String versaoPPC) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.versaoPPC = versaoPPC;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getVersaoPPC() {
        return versaoPPC;
    }

    public void setVersaoPPC(String versaoPPC) {
        this.versaoPPC = versaoPPC;
    }

    //desativei pois nao entendi muito o que ele faria aq
//    public List<AlteracaoPermissao> getListaAlteracaoPPC() {
//        return listaAlteracaoPPC;
//    }
//    public void setListaAlteracaoPPC(List<AlteracaoPermissao> listaAlteracaoPPC) {
//        this.listaAlteracaoPPC = listaAlteracaoPPC;
//    }
}
