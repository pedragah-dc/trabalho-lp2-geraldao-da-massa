package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.inputs.InscricaoRequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.SubstituicaoRequestDTO;
import geraldao_da_massa.demo.DTOs.outputs.InscricaoResponseDTO;
import geraldao_da_massa.demo.entities.Discente;
import geraldao_da_massa.demo.entities.Inscricao;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.repositories.DiscenteRepository;
import geraldao_da_massa.demo.repositories.OportunidadeRepository;
import geraldao_da_massa.demo.services.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// RF015, RF016, RF017 — Inscrições em oportunidades
@RestController
@RequestMapping("/oportunidades/{idOportunidade}/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;
    @Autowired
    private OportunidadeRepository oportunidadeRepository;
    @Autowired
    private DiscenteRepository discenteRepository;

    // Discente se inscreve em uma oportunidade
    // POST /oportunidades/5/inscricoes
    @PostMapping
    public InscricaoResponseDTO criarInscricao(@PathVariable Integer idOportunidade,
                                                @Valid @RequestBody InscricaoRequestDTO dto) {
        Oportunidade oportunidade = buscarOportunidade(idOportunidade);
        Discente discente = buscarDiscente(dto.getIdDiscente());

        Inscricao inscricao = inscricaoService.criarInscricao(oportunidade, discente, dto.getMotivacao());
        return new InscricaoResponseDTO(inscricao);
    }

    // Lista todas as inscrições de uma oportunidade
    // GET /oportunidades/5/inscricoes
    @GetMapping
    public List<InscricaoResponseDTO> listarPorOportunidade(@PathVariable Integer idOportunidade) {
        Oportunidade oportunidade = buscarOportunidade(idOportunidade);
        return inscricaoService.listarPorOportunidade(oportunidade)
                .stream()
                .map(InscricaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Lista só os aprovados de uma oportunidade
    // GET /oportunidades/5/inscricoes/aprovados
    @GetMapping("/aprovados")
    public List<InscricaoResponseDTO> listarAprovados(@PathVariable Integer idOportunidade) {
        Oportunidade oportunidade = buscarOportunidade(idOportunidade);
        return inscricaoService.listarAprovados(oportunidade)
                .stream()
                .map(InscricaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // RF015 - Responsável aprova uma inscrição
    // PATCH /oportunidades/5/inscricoes/12/aprovar
    @PatchMapping("/{idInscricao}/aprovar")
    public InscricaoResponseDTO aprovar(@PathVariable Integer idOportunidade,
                                         @PathVariable Integer idInscricao) {
        Oportunidade oportunidade = buscarOportunidade(idOportunidade);
        Inscricao inscricao = buscarInscricao(idInscricao);

        inscricaoService.aprovarInscricao(inscricao, oportunidade);
        return new InscricaoResponseDTO(inscricao);
    }

    // RF015 - Responsável rejeita uma inscrição
    // PATCH /oportunidades/5/inscricoes/12/rejeitar
    @PatchMapping("/{idInscricao}/rejeitar")
    public InscricaoResponseDTO rejeitar(@PathVariable Integer idOportunidade,
                                          @PathVariable Integer idInscricao) {
        Inscricao inscricao = buscarInscricao(idInscricao);

        inscricaoService.rejeitarInscricao(inscricao);
        return new InscricaoResponseDTO(inscricao);
    }

    // RF016 - Discente cancela a própria inscrição
    // PATCH /oportunidades/5/inscricoes/12/cancelar
    @PatchMapping("/{idInscricao}/cancelar")
    public InscricaoResponseDTO cancelar(@PathVariable Integer idOportunidade,
                                          @PathVariable Integer idInscricao) {
        Inscricao inscricao = buscarInscricao(idInscricao);

        inscricaoService.cancelarInscricao(inscricao);
        return new InscricaoResponseDTO(inscricao);
    }

    // RF017 - Substitui participante aprovado por outro da lista de interessados
    // PATCH /oportunidades/5/inscricoes/12/substituir
    @PatchMapping("/{idInscricao}/substituir")
    public InscricaoResponseDTO substituir(@PathVariable Integer idOportunidade,
                                            @PathVariable Integer idInscricao,
                                            @Valid @RequestBody SubstituicaoRequestDTO dto) {
        Inscricao inscricaoOriginal = buscarInscricao(idInscricao);
        Discente novoDiscente = buscarDiscente(dto.getNovoDiscenteId());

        Inscricao novaInscricao = inscricaoService.substituirParticipante(inscricaoOriginal, novoDiscente);
        return new InscricaoResponseDTO(novaInscricao);
    }

    // -------------------------------------------------------
    // Helpers de busca — lançam 404 (via exceção) se não encontrar
    // -------------------------------------------------------

    private Oportunidade buscarOportunidade(Integer id) {
        return oportunidadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Oportunidade não encontrada com id: " + id));
    }

    private Discente buscarDiscente(Integer id) {
        return discenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discente não encontrado com id: " + id));
    }

    private Inscricao buscarInscricao(Integer id) {
        // Usa o próprio JpaRepository herdado pelo InscricaoRepository
        return inscricaoService.buscarPorId(id);
    }
}
