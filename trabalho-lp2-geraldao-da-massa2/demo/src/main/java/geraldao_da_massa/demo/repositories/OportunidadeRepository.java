package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Docente;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.entities.enums.StatusOportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OportunidadeRepository extends JpaRepository<Oportunidade, Integer> {

    // Usado em listarOportunidadesAbertas() — pega só quem está com inscrições abertas
    List<Oportunidade> findAllByStatus(StatusOportunidade status);

    // Usado em listarAguardandoAprovacao() — fila de pendências de um docente específico
    List<Oportunidade> findAllByStatusAndDocenteResponsavel(StatusOportunidade status, Docente docente);
}
