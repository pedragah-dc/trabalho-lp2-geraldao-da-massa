package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.StatusGrupo;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
public class Grupo {
    private String nome;
    private String tipo;
    private String email;
    private String descricao;
    private @Enumerated StatusGrupo status; // corrigido: era Enum<StatusGrupo>
    private Docente responsavel;
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

    public void adicionarMembro(MembroGrupo membro){

        if(membro != null) membros.add(membro);
    }
}
