package entity;

import entity.enums.CargoNoGrupo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class MembroGrupo {
    private Discente discente;
    private CargoNoGrupo cargo;
    private LocalDateTime time;
    private Historico historico;

    public MembroGrupo(Discente discente) {
        this.discente = discente;
        this.cargo = CargoNoGrupo.MEMBRO;
        historico = new Historico();
        historico.atualize(LocalDateTime.now(), cargo);

    }

    //USADO SOMENTE PARA COPIA
    public MembroGrupo(MembroGrupo copy) {
        this.discente = copy.getDiscente();//acho que seria bom criar uma copia do discente tmb, pq afinal é uma referencia(eu acho)
        this.cargo = copy.getCargo();
    }

    public Discente getDiscente() {
        return discente;
    }


    public CargoNoGrupo getCargo() {
        return cargo;
    }

    public void setCargo(CargoNoGrupo cargo) {
        historico.atualize(LocalDateTime.now(), cargo);
        this.cargo = cargo;
    }

    public String getNome() {
        return discente.getNome();
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
