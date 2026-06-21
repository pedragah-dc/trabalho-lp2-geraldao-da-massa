package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private String email;
    private String senha;
    @OneToOne
    @JoinColumn(name = "id")
    private Papel papel;
    private Boolean ativo;
    private @Enumerated RolesUsuario role;


    public Usuario(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, RolesUsuario role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
        this.ativo = ativo;
        this.role = role;
    }
}