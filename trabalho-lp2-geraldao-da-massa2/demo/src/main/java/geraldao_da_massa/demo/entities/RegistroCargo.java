package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;

import java.time.LocalDateTime;

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
