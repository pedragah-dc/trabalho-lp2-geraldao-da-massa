<<<<<<< HEAD:trabalho-lp2-geraldao-da-massa2/demo/src/main/java/geraldao_da_massa/demo/entities/MembroGrupo.java
package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "membros_de_grupos")
@NoArgsConstructor
@Data
public class MembroGrupo {
    //essa classe é sobre um usuario que é membro de algum grupo
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMembro;
    @OneToOne
    @JoinColumn(name = "usuario")
    private Usuario membro;

    @Enumerated(EnumType.STRING)
    private CargoNoGrupo cargo;
    @JoinColumn(name = "tempo")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "grupo")
    private Grupo grupo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "historico_membro")
    private HistoricoMembro historico;
=======
package entity;

import entity.enums.CargoNoGrupo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class MembroGrupo {
    private Usuario membro;
    private CargoNoGrupo cargo;
    private LocalDateTime time;
    private Historico historico;
>>>>>>> ad2863fb1d92ebc72fd1724ecc049d531ba9e063:src/entity/MembroGrupo.java

    public MembroGrupo(Usuario user) {
        this.membro = user;
        this.cargo = CargoNoGrupo.MEMBRO;
<<<<<<< HEAD:trabalho-lp2-geraldao-da-massa2/demo/src/main/java/geraldao_da_massa/demo/entities/MembroGrupo.java
        historico = new HistoricoMembro();
        historico.atualize(LocalDateTime.now(), cargo);
=======
        historico = new Historico();
        historico.atualize(LocalDateTime.now(), cargo);

>>>>>>> ad2863fb1d92ebc72fd1724ecc049d531ba9e063:src/entity/MembroGrupo.java
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

<<<<<<< HEAD:trabalho-lp2-geraldao-da-massa2/demo/src/main/java/geraldao_da_massa/demo/entities/MembroGrupo.java
}
=======
    //classe aninhadas
    class Historico {
        private ArrayList<RegistroCargo> registros;

        public Historico() {
            registros = new ArrayList<>();
        }

        public void atualize(LocalDateTime date, CargoNoGrupo cargo) {
            fecharRegistroAnterior(date);//perceba que ele fecha o mais recente, o novo cargo ainda nao foi colocado
            registros.add(new RegistroCargo(date, cargo));
        }

        public void fecharRegistroAnterior(LocalDateTime date) {
            int idx = registros.size() - 1; //registro mais recente
            if (!registros.isEmpty()) {
                registros.get(idx).fecharRegistroAnterior(date);
            }

        }

    }
}
>>>>>>> ad2863fb1d92ebc72fd1724ecc049d531ba9e063:src/entity/MembroGrupo.java
