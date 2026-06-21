package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.CargoNoGrupo;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@Setter
public class MembroGrupo {
    private Usuario membro;
    private CargoNoGrupo cargo;
    private LocalDateTime time;
    private Historico historico;

    public MembroGrupo(Usuario user) {
        this.membro = user;
        this.cargo = CargoNoGrupo.MEMBRO;
        historico = new Historico();
        historico.atualize(LocalDateTime.now(), cargo);

    }
    public void setCargo(CargoNoGrupo cargo) {
        historico.atualize(LocalDateTime.now(), cargo);
        this.cargo = cargo;
    }


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
