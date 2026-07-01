package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.outputs.CertificadoResponseDTO;
import geraldao_da_massa.demo.entities.Certificado;
import geraldao_da_massa.demo.entities.Discente;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.repositories.DiscenteRepository;
import geraldao_da_massa.demo.services.CertificadoService;
import geraldao_da_massa.demo.services.OportunidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// RF019 — Encerramento de oportunidade e emissão de certificados
@RestController
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;
    @Autowired
    private OportunidadesService oportunidadesService;
    @Autowired
    private DiscenteRepository discenteRepository;

    // Encerra a oportunidade e gera certificado para todos os aprovados
    // PATCH /oportunidades/5/encerrar
    @PatchMapping("/oportunidades/{idOportunidade}/encerrar")
    public List<CertificadoResponseDTO> encerrarEGerarCertificados(@PathVariable Integer idOportunidade) {
        Oportunidade oportunidade = oportunidadesService.buscarPorId(idOportunidade);

        List<Certificado> certificados = certificadoService.encerrarEGerarCertificados(oportunidade);
        return certificados.stream()
                .map(CertificadoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Lista todos os certificados já emitidos no sistema
    // GET /certificados
    @GetMapping("/certificados")
    public List<CertificadoResponseDTO> listarTodos() {
        return certificadoService.listarCertificados().stream()
                .map(CertificadoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Lista os certificados de um discente específico
    // GET /discentes/3/certificados
    @GetMapping("/discentes/{idDiscente}/certificados")
    public List<CertificadoResponseDTO> listarPorDiscente(@PathVariable Integer idDiscente) {
        Discente discente = discenteRepository.findById(idDiscente)
                .orElseThrow(() -> new IllegalArgumentException("Discente não encontrado com id: " + idDiscente));

        return certificadoService.listarCertificadosPorDiscente(discente).stream()
                .map(CertificadoResponseDTO::new)
                .collect(Collectors.toList());
    }
}
