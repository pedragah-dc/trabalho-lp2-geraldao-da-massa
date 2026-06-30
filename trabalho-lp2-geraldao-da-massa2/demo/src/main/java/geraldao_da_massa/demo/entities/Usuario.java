package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.DTOs.inputs.UsuarioRequestDTO;
import geraldao_da_massa.demo.entities.enums.RolesUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String senha;

    @Embedded
    private Papel papel;
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private RolesUsuario role;

    public Usuario(UsuarioRequestDTO dto){
        nome = dto.getNome();
        email = dto.getEmail();
        senha = dto.getSenha();
        role = RolesUsuario.DISCENTE;
        ativo = true;
    }
}
