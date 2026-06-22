package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.CargoNoGrupo;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Embeddable
@NoArgsConstructor
public class RegistroCargo {
    private CargoNoGrupo cargo;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public RegistroCargo(LocalDateTime now, CargoNoGrupo novoCargo){
        cargo = novoCargo;
        inicio = now;
    }
    public void fecharRegistroAnterior(LocalDateTime date ){
        fim = date;
    }
}
