package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entities.MembroGrupo;
import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;
import geraldao_da_massa.demo.repositories.MembroGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroService {

    @Autowired
    private MembroGrupoRepository membroGrupoRepository;

    public void setCargo(CargoNoGrupo cargo){
        //lembro disso nao
        //vou descartar esse servico(talvez)
    }
}
