package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.DTOs.inputs.GrupoRequestDTO;
import geraldao_da_massa.demo.entities.enums.StatusGrupo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupos")
@NoArgsConstructor
@Data
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idGrupo;
    private String nome;
    private String tipo;
    private String email;//email?
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusGrupo status; // corrigido: era Enum<StatusGrupo>
    @OneToOne
    private Docente responsavel;
    @OneToMany(mappedBy = "grupo")
    private List<MembroGrupo> membros;

    public Grupo(String nome, String tipo, String email, String descricao,
                 StatusGrupo status, Docente responsavel) {
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.descricao = descricao;
        this.status = status;
        this.responsavel = responsavel;
//        membros = new ArrayList<>();
    }
    public Grupo(GrupoRequestDTO grupoDTO){
        this.nome = grupoDTO.getNome();
        this.tipo = grupoDTO.getTipo();
        this.email = grupoDTO.getEmail();
        this.descricao = grupoDTO.getDescricao();
//        this.status = grupoDTO.getStatus(); //seta posteriormente
        this.responsavel = null;//feito mais tarde no service;-;
//        membros = new ArrayList<>();
    }
}
