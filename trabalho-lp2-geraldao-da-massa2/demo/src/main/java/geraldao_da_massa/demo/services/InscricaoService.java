package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entities.Discente;
import geraldao_da_massa.demo.entities.Inscricao;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.entities.enums.StatusInscricao;
import geraldao_da_massa.demo.entities.enums.StatusOportunidade;
import geraldao_da_massa.demo.repositories.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {
    @Autowired
    private InscricaoRepository inscricaoRepository;


    // Discente solicita inscrição em oportunidade aberta
    public Inscricao criarInscricao(Oportunidade oportunidade, Discente discente, String motivacao) {

        if (oportunidade.getStatus() != StatusOportunidade.EM_INSCRICOES) {
            throw new IllegalStateException("Inscrições não estão abertas para esta oportunidade. Status: "
                    + oportunidade.getStatus());
        }

        // Corrigido: agora verifica se ESSE discente já tem inscrição NESSA oportunidade
        // (antes verificava discente OU oportunidade isoladamente, no banco inteiro)
        if (inscricaoRepository.existsByOportunidadeAndDiscente(oportunidade, discente)) {
            throw new IllegalStateException("Discente já possui inscrição nesta oportunidade.");
        }

        Inscricao inscricao = new Inscricao(oportunidade, discente, motivacao);
        inscricaoRepository.save(inscricao);
        System.out.println("[INSCRICAO] " + discente.getNome() + " inscrito em '" + oportunidade.getTitulo() + "'");
        return inscricao;
    }

    // RF015 - Responsável APROVA inscrição
    public void aprovarInscricao(Inscricao inscricao, Oportunidade oportunidade) {
        if (inscricao.getStatus() != StatusInscricao.PENDENTE) {
            throw new IllegalStateException("Apenas inscrições PENDENTES podem ser aprovadas. Status: "
                    + inscricao.getStatus());
        }

        // Conta quantos já estão APROVADOS nessa oportunidade para checar vaga
        long vagasOcupadas = inscricaoRepository
                .findByOportunidadeAndStatus(oportunidade, StatusInscricao.APROVADO)
                .size();

        if (vagasOcupadas >= oportunidade.getVagas()) {
            throw new IllegalStateException("Não há vagas disponíveis. Vagas: " + oportunidade.getVagas()
                    + ", Aprovados: " + vagasOcupadas);
        }

        inscricao.setStatus(StatusInscricao.APROVADO);
        inscricaoRepository.save(inscricao);
        System.out.println("[RF015] Inscrição de '" + inscricao.getDiscente().getNome() + "' APROVADA.");
    }

    // RF015 - Responsável REJEITA inscrição
    public void rejeitarInscricao(Inscricao inscricao) {
        if (inscricao.getStatus() != StatusInscricao.PENDENTE) {
            throw new IllegalStateException("Apenas inscrições PENDENTES podem ser rejeitadas. Status: "
                    + inscricao.getStatus());
        }
        inscricao.setStatus(StatusInscricao.REJEITADO);
        inscricaoRepository.save(inscricao);
        System.out.println("[RF015] Inscrição de '" + inscricao.getDiscente().getNome() + "' REJEITADA.");
    }

    // RF016 - Discente CANCELA a própria inscrição antes do início
    public void cancelarInscricao(Inscricao inscricao) {
        if (inscricao.getStatus() == StatusInscricao.CANCELADO) {
            throw new IllegalStateException("Esta inscrição já está cancelada.");
        }
        if (inscricao.getOportunidade().isFinalizada()) {
            throw new IllegalStateException("Não é possível cancelar após o encerramento da atividade.");
        }
        if (inscricao.getOportunidade().getStatus() == StatusOportunidade.EM_EXECUCAO) {
            throw new IllegalStateException("Não é possível cancelar uma inscrição de atividade em execução.");
        }

        inscricao.setStatus(StatusInscricao.CANCELADO);
        inscricaoRepository.save(inscricao);
        System.out.println("[RF016] Inscrição de '" + inscricao.getDiscente().getNome()
                + "' em '" + inscricao.getOportunidade().getTitulo() + "' CANCELADA.");
    }

    // RF017 - Substituir participante aprovado por outro discente
    // O substituto deve ter uma inscrição existente (PENDENTE ou REJEITADA) na mesma oportunidade
    // conforme o requisito: "selecionar outro discente da lista de interessados"
    public Inscricao substituirParticipante(Inscricao inscricaoOriginal, Discente novoDiscente) {
        if (inscricaoOriginal.getStatus() != StatusInscricao.APROVADO) {
            throw new IllegalStateException("Só é possível substituir inscrições APROVADAS. Status atual: "
                    + inscricaoOriginal.getStatus());
        }

        Oportunidade oportunidade = inscricaoOriginal.getOportunidade();

        if (oportunidade.getStatus() == StatusOportunidade.CONCLUIDA
                || oportunidade.getStatus() == StatusOportunidade.CANCELADA) {
            throw new IllegalStateException("Não é possível substituir participantes em oportunidade "
                    + oportunidade.getStatus());
        }

        // O substituto precisa ter inscrição na oportunidade (é da "lista de interessados")
        Inscricao inscricaoSubstituto = inscricaoRepository.findByOportunidadeAndDiscente(novoDiscente, oportunidade);
        if (inscricaoSubstituto == null) {
            throw new IllegalStateException("O discente '" + novoDiscente.getNome()
                    + "' não possui inscrição nesta oportunidade. "
                    + "O substituto deve ser alguém da lista de interessados.");
        }
        if (inscricaoSubstituto.getStatus() == StatusInscricao.APROVADO) {
            throw new IllegalStateException("O discente '" + novoDiscente.getNome()
                    + "' já está aprovado nesta oportunidade.");
        }
        if (inscricaoSubstituto.getStatus() == StatusInscricao.CANCELADO) {
            throw new IllegalStateException("O discente '" + novoDiscente.getNome()
                    + "' cancelou a própria inscrição e não pode ser substituto.");
        }

        // Marca o original como substituído
        inscricaoOriginal.setStatus(StatusInscricao.SUBSTITUIDO);
        inscricaoOriginal.setSustituidoPor(inscricaoSubstituto);

        // Aprova o substituto
        inscricaoSubstituto.setStatus(StatusInscricao.APROVADO);

        inscricaoRepository.save(inscricaoOriginal);
        inscricaoRepository.save(inscricaoSubstituto);

        System.out.println("[RF017] '" + inscricaoOriginal.getDiscente().getNome()
                + "' substituído por '" + novoDiscente.getNome()
                + "' em '" + oportunidade.getTitulo() + "'");
        return inscricaoSubstituto;
    }

    // Reativados: agora funcionam porque o repositório retorna o tipo certo (List<Inscricao>)
    public List<Inscricao> listarPorOportunidade(Oportunidade oportunidade) {
        return inscricaoRepository.findAllByOportunidade(oportunidade);
    }

    public List<Inscricao> listarAprovados(Oportunidade oportunidade) {
        return inscricaoRepository.findByOportunidadeAndStatus(oportunidade, StatusInscricao.APROVADO);
    }

    // Usado pelo controller para buscar a inscrição pelo id antes de aprovar/rejeitar/cancelar/substituir
    public Inscricao buscarPorId(Integer id) {
        return inscricaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inscrição não encontrada com id: " + id));
    }
}
