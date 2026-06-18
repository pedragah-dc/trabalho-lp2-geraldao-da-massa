package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.AlteracaoPermissao;

import java.util.ArrayList;
import java.util.List;

public class AlteracaoPermissaoRepository {
    List<AlteracaoPermissao> listaAlteracaoPermissao;

    public AlteracaoPermissaoRepository(){
        listaAlteracaoPermissao = new ArrayList<>();
    }

    public List<AlteracaoPermissao> getListaAlteracaoPermissao(){
        return listaAlteracaoPermissao;
    }
}
