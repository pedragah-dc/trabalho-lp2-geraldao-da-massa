<<<<<<< HEAD:trabalho-lp2-geraldao-da-massa2/demo/src/main/java/geraldao_da_massa/demo/entities/RegistroCargo.java
package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;
import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Embeddable
@NoArgsConstructor
=======
package entity;

import entity.enums.CargoNoGrupo;

import java.time.LocalDateTime;

>>>>>>> ad2863fb1d92ebc72fd1724ecc049d531ba9e063:src/entity/RegistroCargo.java
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
