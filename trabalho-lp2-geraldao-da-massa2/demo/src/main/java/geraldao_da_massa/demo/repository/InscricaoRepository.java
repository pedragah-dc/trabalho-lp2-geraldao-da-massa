package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.Discente;
import geraldao_da_massa.demo.entity.Inscricao;
import geraldao_da_massa.demo.entity.Oportunidade;
import geraldao_da_massa.demo.entity.SolicitacaoOportunidade;
import geraldao_da_massa.demo.entity.enums.StatusInscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {


    boolean findByDiscente(Discente discente);
    boolean findByOportunidade(Oportunidade oportunidade);
    int findAllByStatus(StatusInscricao status);
    Collection<Object> findAllByOportunidade(Oportunidade oportunidade);
    Inscricao findByOportunidadeAndDiscente(Discente novoDiscente, Oportunidade oportunidade);
    List<SolicitacaoOportunidade> findAllByDiscente(Discente discente);
}
//foda