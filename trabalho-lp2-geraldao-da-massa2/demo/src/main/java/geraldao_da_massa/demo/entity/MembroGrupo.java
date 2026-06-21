package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.CargoNoGrupo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "membrogrupo")
public class MembroGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMembro;
    @OneToOne
    private Usuario membro;
    private CargoNoGrupo cargo;
    private LocalDateTime time;

    @OneToOne
    @JoinColumn(name = "membroid")
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