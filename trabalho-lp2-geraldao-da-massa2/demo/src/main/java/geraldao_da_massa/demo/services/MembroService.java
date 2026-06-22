package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entities.MembroGrupo;
import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;

public class MembroService {

    public void setCargo(MembroGrupo membro, CargoNoGrupo cargo){
        membro.setCargo(cargo);
    }
}
