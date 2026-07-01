package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.DTOs.inputs.OportunidadeRequestDTO;
import geraldao_da_massa.demo.entities.Discente;
import geraldao_da_massa.demo.entities.Docente;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.entities.Usuario;
import geraldao_da_massa.demo.entities.enums.StatusOportunidade;
import geraldao_da_massa.demo.repositories.DocenteRepository;
import geraldao_da_massa.demo.repositories.OportunidadeRepository;
import geraldao_da_massa.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


//Vou imaginar assim: Um serviço só tem acesso ao seu repositorio e se ele quiser consultar outro, terá que utilizar um serviço pra isso

@Service
public class OportunidadesService {
    @Autowired
    private OportunidadeRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private DocenteRepository docenteRepository;

    public OportunidadesService(OportunidadeRepository repository) {
        this.repository = repository;
    }

    // -------------------------------------------------------
    // RF011 - Criar oportunidade
    // -------------------------------------------------------
    // Qualquer usuário (discente diretor ou docente) pode criar.
    // A oportunidade começa como RASCUNHO e não aparece para ninguém ainda.
    public boolean criarOportunidade(OportunidadeRequestDTO oportunidade, int id) {

        if (oportunidade.getTitulo() == null || oportunidade.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Título da oportunidade é obrigatório.");
        }
        if (oportunidade.getCargaHoraria() == null || oportunidade.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero.");
        }
        if (oportunidade.getVagas() == null || oportunidade.getVagas() <= 0) {
            throw new IllegalArgumentException("Número de vagas deve ser maior que zero.");
        }

        // Corrigido: validação reativada (estava comentada e não bloqueava nada)
        if (oportunidade.getInicio() == null || oportunidade.getFim() == null
                || oportunidade.getFim().isBefore(oportunidade.getInicio())) {
            throw new IllegalArgumentException("Datas de início e fim são inválidas.");
        }

        // Busca o autor (quem está criando) pelo id da rota
        Usuario responsavel = userRepository.findById(id);

        // Corrigido: antes o docente responsável era passado como null.
        // Agora busca o docente pelo docenteResponsavelId que já vem no DTO.
        Docente docenteResponsavel = docenteRepository.findById(oportunidade.getDocenteResponsavelId().intValue());
        if (docenteResponsavel == null) {
            throw new IllegalArgumentException("Docente responsável não encontrado para o id informado.");
        }

        Oportunidade nova = new Oportunidade(oportunidade.getTitulo(), oportunidade.getDescricao(), oportunidade.getTipo(), oportunidade.getModalidade(),
                oportunidade.getCargaHoraria(), oportunidade.getVagas(), oportunidade.getInicio(), oportunidade.getFim(),
                oportunidade.getDataInicioInscricoes(), oportunidade.getDataFimInscricoes(),
                responsavel, docenteResponsavel);
        repository.save(nova);

        System.out.println("[RF011] Oportunidade criada como RASCUNHO: " + oportunidade.getTitulo());
        return true;
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
        repository.save(oportunidade);
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
        // Comparamos pelo id (não por equals()) porque Docente não tem
        // equals()/hashCode() customizado — comparar objetos carregados em
        // consultas diferentes pelo equals() padrão do Java sempre falharia
        if (!oportunidade.getDocenteResponsavel().getId().equals(docente.getId())) {
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

        repository.save(oportunidade);
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
        if (!oportunidade.getDocenteResponsavel().getId().equals(docente.getId())) {
            throw new IllegalStateException("Apenas o docente responsável pode reprovar esta oportunidade.");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("É obrigatório informar o motivo da reprovação.");
        }

        oportunidade.setStatus(StatusOportunidade.REPROVADA);
        oportunidade.setFeedbackReprovacao(motivo);
        repository.save(oportunidade);
        System.out.println("[RF012] Oportunidade '" + oportunidade.getTitulo()
                + "' REPROVADA. Motivo: " + motivo);
        System.out.println("[RF012] Notificação enviada ao criador: " + oportunidade.getAutor().getNome());
    }

    // -------------------------------------------------------
    // Utilitários de listagem
    // -------------------------------------------------------

    // Lista todas as oportunidades com inscrições abertas (para discentes verem)
    public List<Oportunidade> listarOportunidadesAbertas() {
        return repository.findAllByStatus(StatusOportunidade.EM_INSCRICOES);
    }

    // Lista oportunidades aguardando aprovação (para o docente ver sua fila)
    public List<Oportunidade> listarAguardandoAprovacao(Docente docente) {
        return repository.findAllByStatusAndDocenteResponsavel(StatusOportunidade.AGUARDANDO_APROVACAO, docente);
    }

    public List<Oportunidade> listarTodas() {
        return repository.findAll();
    }

    public boolean fecharInscricoes(Oportunidade oportunidade){
        oportunidade.setStatus(StatusOportunidade.ARQUIVADA);
        repository.save(oportunidade);
        return true;
    }

    // Usado pelo controller para buscar a oportunidade pelo id antes de submeter/aprovar/reprovar
    public Oportunidade buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Oportunidade não encontrada com id: " + id));
    }

}
