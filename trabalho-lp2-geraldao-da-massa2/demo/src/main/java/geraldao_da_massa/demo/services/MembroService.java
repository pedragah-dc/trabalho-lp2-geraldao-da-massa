package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entity.MembroGrupo;
import geraldao_da_massa.demo.entity.enums.CargoNoGrupo;

public class MembroService {

    public void setCargo(MembroGrupo membro, CargoNoGrupo cargo){
        membro.setCargo(cargo);
    }
}
