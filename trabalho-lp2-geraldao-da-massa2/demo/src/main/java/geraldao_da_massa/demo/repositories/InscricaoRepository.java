package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Discente;
import geraldao_da_massa.demo.entities.Inscricao;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.entities.SolicitacaoOportunidade;
import geraldao_da_massa.demo.entities.enums.StatusInscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {

    // Corrigido: "exists" retorna boolean de verdade (verifica se já existe
    // uma inscrição desse discente NESSA oportunidade especifica)
    boolean existsByOportunidadeAndDiscente(Oportunidade oportunidade, Discente discente);

    // Corrigido: agora retorna List<Inscricao>, que é o tipo real do resultado
    List<Inscricao> findAllByOportunidade(Oportunidade oportunidade);

    // Novo: usado pelo RF019 para buscar só quem foi aprovado naquela oportunidade
    List<Inscricao> findByOportunidadeAndStatus(Oportunidade oportunidade, StatusInscricao status);

    Inscricao findByOportunidadeAndDiscente(Discente novoDiscente, Oportunidade oportunidade);

    List<SolicitacaoOportunidade> findAllByDiscente(Discente discente);
}
