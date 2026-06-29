package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entities.Certificado;
import geraldao_da_massa.demo.entities.Discente;
import geraldao_da_massa.demo.entities.Inscricao;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.entities.enums.StatusOportunidade;
import geraldao_da_massa.demo.repositories.InscricaoRepository;
import geraldao_da_massa.demo.entities.Certificado;
import geraldao_da_massa.demo.entities.Discente;
import geraldao_da_massa.demo.entities.Inscricao;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.entities.enums.StatusOportunidade;
import geraldao_da_massa.demo.repositories.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class CertificadoService {
    @Autowired
    private InscricaoRepository inscricaoRepository;
    //?
    private List<Certificado> certificadoEmitidos = new ArrayList<>();



    // RF019 - Encerrar oportunidade e gerar lista de participantes para certificação
    public List<Certificado> encerrarEGerarCertificados(Oportunidade oportunidade) {
        if (oportunidade.getStatus() != StatusOportunidade.EM_EXECUCAO
                && oportunidade.getStatus() != StatusOportunidade.APROVADA
                && oportunidade.getStatus() != StatusOportunidade.EM_INSCRICOES) {
            throw new IllegalStateException("Apenas oportunidades ativas podem ser encerradas. Status: "
                    + oportunidade.getStatus());
        }

        List<Inscricao> aprovados = inscricaoRepository.findAll();

        if (aprovados.isEmpty()) {
            throw new IllegalStateException("Não há participantes aprovados para certificar.");
        }

        oportunidade.setStatus(StatusOportunidade.CONCLUIDA);
        System.out.println("[RF019] Oportunidade '" + oportunidade.getTitulo() + "' ENCERRADA.");
        System.out.println("[RF019] Gerando certificados para " + aprovados.size() + " participante(s)...");

        List<Certificado> novos = new ArrayList<>();
        for (Inscricao inscricao : aprovados) {
            Discente discente = inscricao.getDiscente();
            String hash = UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();

            Certificado cert = new Certificado(
                    hash,
                    discente,
                    oportunidade,
                    LocalDateTime.now(),
                    oportunidade.getCargaHoraria(),
                    "certificados/" + hash + ".pdf",
                    false // ainda não assinado
            );

            certificadoEmitidos.add(cert);
            novos.add(cert);
            System.out.println("[RF019]  Certificado gerado: " + discente.getNome()
                    + " | Hash: " + hash
                    + " | " + oportunidade.getCargaHoraria() + "h");
        }

        System.out.println("[RF019] Total de certificados gerados: " + novos.size());
        return novos;
    }

    // Retorna todos os certificados emitidos
    public List<Certificado> listarCertificados() {
        return new ArrayList<>(certificadoEmitidos);
    }

    // Retorna os certificados de um discente específico
    public List<Certificado> listarCertificadosPorDiscente(Discente discente) {
        List<Certificado> resultado = new ArrayList<>();
        for (Certificado c : certificadoEmitidos) {
            if (c.getDiscente().equals(discente)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}
