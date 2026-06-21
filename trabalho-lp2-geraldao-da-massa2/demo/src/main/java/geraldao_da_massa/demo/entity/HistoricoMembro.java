package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.CargoNoGrupo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "historico")
public class HistoricoMembro {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @OneToOne(mappedBy = "membroid")
        private MembroGrupo membro;
        private ArrayList<RegistroCargo> registros;

        public HistoricoMembro () {
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


