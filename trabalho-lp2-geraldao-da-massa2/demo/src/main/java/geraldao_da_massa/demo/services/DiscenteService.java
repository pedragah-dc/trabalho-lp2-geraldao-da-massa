package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.DTOs.UsuarioRequestDTO;
import geraldao_da_massa.demo.entities.*;
import geraldao_da_massa.demo.entities.enums.RolesUsuario;
import geraldao_da_massa.demo.entities.enums.StatusSolicitacaoOportunidade;
import geraldao_da_massa.demo.repositories.CursoRepository;
import geraldao_da_massa.demo.repositories.DiscenteRepository;
import geraldao_da_massa.demo.repositories.SolicitacaoOportunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepository discenteRepository;
    //estranhamente nao tem curso service...
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioService usuarioService;

    public SolicitacaoOportunidade criarSolicitacaoOportunidade(Discente discente, Oportunidade oportunidade){
        try{
            SolicitacaoOportunidade solicitacao = new SolicitacaoOportunidade(discente, oportunidade);
            return solicitacao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean reenviarSolicitacaoOportunidade(SolicitacaoOportunidade solicitacao) throws Exception {
        if (solicitacao.getStatus() != StatusSolicitacaoOportunidade.ESPERANDO_REENVIO)
            throw new Exception("Não é possível realizar reenvio desta solicitação no momento.");
        
        // ficou grande demais entao vou explicar kkkkk
        // pega a data de agora e verifica se é depois do dia do indeferimento + 5 dias
        // o requisito fala que após o indeferimento tem 5 dias pra reenvio então ta fazendo exatamente isso
        if(LocalDateTime.now().isAfter(solicitacao.getDataIndeferimento().plusDays(5)))
            throw new Exception("Prazo para reenvio esgotado!");

        solicitacao.setStatus(StatusSolicitacaoOportunidade.PENDENTE);
        return true;
    }
    public List<SolicitacaoOportunidade> listarSolicitacoesDoDiscente(Discente discente, SolicitacaoOportunidadeRepository repo){
        return repo.listarPorDiscente(discente);
    }
    public DiscenteService(DiscenteRepository discenteRepository, UsuarioService usuarioService) {
        this.discenteRepository = discenteRepository;
        this.usuarioService = usuarioService;
    }


    public Discente autocadastroDiscente(String nome, String email, String senha, String matricula) {

        try{

            UsuarioRequestDTO dto = new UsuarioRequestDTO(nome, email, matricula, null, true, RolesUsuario.DISCENTE);

            Usuario usuario = usuarioService.autocadastroUsuario(dto);
    
            Curso curso = verificaCurso(matricula);
            Discente discente = new Discente(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getPapel(),
                    usuario.getAtivo(),
                    null,
                    matricula,
                    0,
                    curso,
                    RolesUsuario.DISCENTE
            );

            discenteRepository.save(discente);
            return discente;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar discente", e);
        }
    }

    public Boolean verificaMatriculaDiscente (String matricula, List < Discente > repositorio){
        for (Discente d : repositorio) {
            if (d.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    //verificar se o curso existe???
    //pegar o curso que o discente está cursando??
    //ah ta,  matricula diz qual curso o aluno está, muito foda, sabia disso nao
    public Curso verificaCurso (String matricula){
        //rapaz, eu acho que vamo ter que quebrar essa matricula pra descobrir o codigo do curso e apartir disso pesquisar no banco
        Integer codigoMatricula = Integer.parseInt(matricula.substring(1, 4));
        for(Curso curso: cursoRepository.findAll()){
            if(curso.getCodigo().equals(codigoMatricula)){
                return curso;
            }
        }
        return null;
    }
}