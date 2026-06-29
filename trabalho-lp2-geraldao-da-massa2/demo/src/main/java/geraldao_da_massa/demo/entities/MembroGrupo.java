package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "membros_de_grupos")
@NoArgsConstructor
public class MembroGrupo {
    //essa classe é sobre um usuario que é membro de algum grupo
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMembro;
    @OneToOne
    private Usuario membro;
    private CargoNoGrupo cargo;
    private LocalDateTime time;

    @OneToOne
    @JoinColumn(name = "historico_membro")
    private HistoricoMembro historico;

    public MembroGrupo(Usuario user) {
        this.membro = user;
        this.cargo = CargoNoGrupo.MEMBRO;
        historico.atualize(LocalDateTime.now(), cargo);

    }

    //USADO SOMENTE PARA COPIA
    public MembroGrupo(MembroGrupo copy) {
        this.membro = copy.getMembro();
        this.cargo = copy.getCargo();
    }

    public Usuario getMembro() {
        return membro;
    }


    public CargoNoGrupo getCargo() {
        return cargo;
    }

    public void setCargo(CargoNoGrupo cargo) {
        historico.atualize(LocalDateTime.now(), cargo);
        this.cargo = cargo;
    }

    public String getNome() {
        return membro.getNome();
    }

}