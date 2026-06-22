package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.RolesUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Usuarios")
@NoArgsConstructor

@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long id1;

    private String nome;
    private String email;
    private String senha;
    @OneToOne
    @JoinColumn(name = "id")
    private Papel papel;
    private Boolean ativo;
    private Enum<RolesUsuario> role;

}
