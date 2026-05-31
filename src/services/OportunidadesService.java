package services;

import entity.Discente;
import entity.Docente;
import entity.Oportunidade;
import entity.Usuario;
import entity.enums.StatusOportunidade;
import entity.enums.TiposModalidade;
import entity.enums.TiposOportunidade;
import repository.OportunidadeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OportunidadesService {

    private OportunidadeRepository repository;

    public OportunidadesService(OportunidadeRepository repository) {
        this.repository = repository;
    }

    // -------------------------------------------------------
    // RF011 - Criar oportunidade
    // -------------------------------------------------------
    // Qualquer usuário (discente diretor ou docente) pode criar.
    // A oportunidade começa como RASCUNHO e não aparece para ninguém ainda.
    public Oportunidade criarOportunidade(String titulo, String descricao,
                                          TiposOportunidade tipo, TiposModalidade modalidade,
                                          Integer cargaHoraria, Integer vagas,
                                          LocalDateTime inicio, LocalDateTime fim,
                                          LocalDateTime dataInicioInscricoes, LocalDateTime dataFimInscricoes,
                                          Usuario autor, Docente docenteResponsavel) {

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título da oportunidade é obrigatório.");
        }
        if (cargaHoraria == null || cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero.");
        }
        if (vagas == null || vagas <= 0) {
            throw new IllegalArgumentException("Número de vagas deve ser maior que zero.");
        }
        if (inicio == null || fim == null || fim.isBefore(inicio)) {
            throw new IllegalArgumentException("Datas de início e fim são inválidas.");
        }

        Oportunidade nova = new Oportunidade(titulo, descricao, tipo, modalidade,
                cargaHoraria, vagas, inicio, fim,
                dataInicioInscricoes, dataFimInscricoes,
                autor, docenteResponsavel);

        repository.salvar(nova);
        System.out.println("[RF011] Oportunidade criada como RASCUNHO: " + titulo);
        return nova;
    }

    // -------------------------------------------------------
    // RF011 - Submeter para aprovação
    // -------------------------------------------------------
    // Depois de preencher tudo, o criador submete.
    // Muda de RASCUNHO → AGUARDANDO_APROVACAO.
    // O docente responsável recebe uma notificação (simulada com print).
    public void submeterParaAprovacao(Oportunidade oportunidade) {
        if (oportunidade.getStatus() != StatusOportunidade.RASCUNHO) {
            throw new IllegalStateException("Só é possível submeter oportunidades em RASCUNHO. Status atual: "
                    + oportunidade.getStatus());
        }
        oportunidade.setStatus(StatusOportunidade.AGUARDANDO_APROVACAO);
        System.out.println("[RF011] Oportunidade '" + oportunidade.getTitulo()
                + "' submetida. Aguardando aprovação de: "
                + oportunidade.getDocenteResponsavel().getNome());
    }

    // -------------------------------------------------------
    // RF012 - Aprovar oportunidade (papel do Docente)
    // -------------------------------------------------------
    // Docente analisa e aprova → status vai para APROVADA.
    // Em seguida, se o período de inscrições for agora, vai para EM_INSCRICOES.
    public void aprovarOportunidade(Oportunidade oportunidade, Docente docente) {
        if (oportunidade.getStatus() != StatusOportunidade.AGUARDANDO_APROVACAO) {
            throw new IllegalStateException("Oportunidade não está aguardando aprovação. Status: "
                    + oportunidade.getStatus());
        }
        // Verifica se é o docente responsável pela oportunidade
        if (!oportunidade.getDocenteResponsavel().equals(docente)) {
            throw new IllegalStateException("Apenas o docente responsável pode aprovar esta oportunidade.");
        }

        oportunidade.setStatus(StatusOportunidade.APROVADA);
        System.out.println("[RF012] Oportunidade '" + oportunidade.getTitulo()
                + "' APROVADA por " + docente.getNome());

        // Se o período de inscrições já está aberto, abre automaticamente
        LocalDateTime agora = LocalDateTime.now();
        if (oportunidade.getDataInicioInscricoes() != null
                && !agora.isBefore(oportunidade.getDataInicioInscricoes())) {
            oportunidade.setStatus(StatusOportunidade.EM_INSCRICOES);
            System.out.println("[RF012] Inscrições abertas automaticamente para: " + oportunidade.getTitulo());
        }
    }

    // -------------------------------------------------------
    // RF012 - Reprovar oportunidade (papel do Docente)
    // -------------------------------------------------------
    // Docente reprova e DEVE informar o motivo.
    // Criador pode corrigir e resubmeter.
    public void reprovarOportunidade(Oportunidade oportunidade, Docente docente, String motivo) {
        if (oportunidade.getStatus() != StatusOportunidade.AGUARDANDO_APROVACAO) {
            throw new IllegalStateException("Oportunidade não está aguardando aprovação. Status: "
                    + oportunidade.getStatus());
        }
        if (!oportunidade.getDocenteResponsavel().equals(docente)) {
            throw new IllegalStateException("Apenas o docente responsável pode reprovar esta oportunidade.");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("É obrigatório informar o motivo da reprovação.");
        }

        oportunidade.setStatus(StatusOportunidade.REPROVADA);
        oportunidade.setFeedbackReprovacao(motivo);
        System.out.println("[RF012] Oportunidade '" + oportunidade.getTitulo()
                + "' REPROVADA. Motivo: " + motivo);
        System.out.println("[RF012] Notificação enviada ao criador: " + oportunidade.getAutor().getNome());
    }

    // -------------------------------------------------------
    // Utilitários de listagem
    // -------------------------------------------------------

    // Lista todas as oportunidades com inscrições abertas (para discentes verem)
    public List<Oportunidade> listarOportunidadesAbertas() {
        List<Oportunidade> abertas = new ArrayList<>();
        for (Oportunidade op : repository.listarTodas()) {
            if (op.getStatus() == StatusOportunidade.EM_INSCRICOES) {
                abertas.add(op);
            }
        }
        return abertas;
    }

    // Lista oportunidades aguardando aprovação (para o docente ver sua fila)
    public List<Oportunidade> listarAguardandoAprovacao(Docente docente) {
        List<Oportunidade> pendentes = new ArrayList<>();
        for (Oportunidade op : repository.listarTodas()) {
            if (op.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO
                    && op.getDocenteResponsavel().equals(docente)) {
                pendentes.add(op);
            }
        }
        return pendentes;
    }

    public List<Oportunidade> listarTodas() {
        return repository.listarTodas();
    }

    public List<Oportunidade> retornaOportunidadeDiscente(List<Oportunidade> lista, Discente discente ){
        List<Oportunidade> retorno = new ArrayList<>();
        for(Oportunidade i : lista){
            for (Oportunidade j : discente.getListaDeOp()){
                if (i.equals(j)){
                    retorno.add(j);
                }
            }
        }
        return retorno;
    }


}
