package services;

import entity.Certificados;
import entity.Discente;
import entity.Inscricao;
import entity.Oportunidade;
import entity.enums.StatusInscricao;
import entity.enums.StatusOportunidade;
import repository.InscricaoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CertificadoService {

    private InscricaoRepository inscricaoRepository;
    private List<Certificados> certificadosEmitidos = new ArrayList<>();

    public CertificadoService(InscricaoRepository inscricaoRepository) {
        this.inscricaoRepository = inscricaoRepository;
    }

    // RF019 - Encerrar oportunidade e gerar lista de participantes para certificação
    public List<Certificados> encerrarEGerarCertificados(Oportunidade oportunidade) {
        if (oportunidade.getStatus() != StatusOportunidade.EM_EXECUCAO
                && oportunidade.getStatus() != StatusOportunidade.APROVADA
                && oportunidade.getStatus() != StatusOportunidade.EM_INSCRICOES) {
            throw new IllegalStateException("Apenas oportunidades ativas podem ser encerradas. Status: "
                    + oportunidade.getStatus());
        }

        List<Inscricao> aprovados = inscricaoRepository.listarAprovadosPorOportunidade(oportunidade);

        if (aprovados.isEmpty()) {
            throw new IllegalStateException("Não há participantes aprovados para certificar.");
        }

        oportunidade.setStatus(StatusOportunidade.CONCLUIDA);
        System.out.println("[RF019] Oportunidade '" + oportunidade.getTitulo() + "' ENCERRADA.");
        System.out.println("[RF019] Gerando certificados para " + aprovados.size() + " participante(s)...");

        List<Certificados> novos = new ArrayList<>();
        for (Inscricao inscricao : aprovados) {
            Discente discente = inscricao.getDiscente();
            String hash = UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();

            Certificados cert = new Certificados(
                    hash,
                    discente,
                    oportunidade,
                    LocalDateTime.now(),
                    oportunidade.getCargaHoraria(),
                    "certificados/" + hash + ".pdf",
                    false // ainda não assinado
            );

            certificadosEmitidos.add(cert);
            novos.add(cert);
            System.out.println("[RF019]  Certificado gerado: " + discente.getNome()
                    + " | Hash: " + hash
                    + " | " + oportunidade.getCargaHoraria() + "h");
        }

        System.out.println("[RF019] Total de certificados gerados: " + novos.size());
        return novos;
    }

    // Retorna todos os certificados emitidos
    public List<Certificados> listarCertificados() {
        return new ArrayList<>(certificadosEmitidos);
    }

    // Retorna os certificados de um discente específico
    public List<Certificados> listarCertificadosPorDiscente(Discente discente) {
        List<Certificados> resultado = new ArrayList<>();
        for (Certificados c : certificadosEmitidos) {
            if (c.getDiscente().equals(discente)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}
