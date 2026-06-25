package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Discente;
import geraldao_da_massa.demo.entities.Inscricao;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.entities.SolicitacaoOportunidade;
import geraldao_da_massa.demo.entities.enums.StatusInscricao;
import org.springframework.data.jpa.repository.JpaRepository;

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