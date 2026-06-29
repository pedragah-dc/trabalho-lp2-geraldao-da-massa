package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.StatusGrupo;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Table(name = "grupos")
@NoArgsConstructor
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String nome;
    private String tipo;
    private String email;
    private String descricao;
    private StatusGrupo status; // corrigido: era Enum<StatusGrupo>
    @OneToOne
    private Docente responsavel;
    @OneToMany
    private ArrayList<MembroGrupo> membros;

    public Grupo(String nome, String tipo, String email, String descricao,
                 StatusGrupo status, Docente responsavel) {
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.descricao = descricao;
        this.status = status;
        this.responsavel = responsavel;
        membros = new ArrayList<>();
    }
}
