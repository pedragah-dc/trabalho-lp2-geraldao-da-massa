package services;

import entity.Discente;
import entity.Oportunidade;
import entity.SolicitacaoOportunidade;
import entity.enums.StatusSolicitacaoOportunidade;
import repository.SolicitacaoOportunidadeRepository;

import java.time.LocalDateTime;
import java.util.List;
import entity.Curso;
import entity.Discente;
import entity.Usuario;

import java.util.List;
import java.util.Scanner;

import entity.enums.RolesUsuario;
import repository.DiscenteRepository;

import repository.CursoRepository;

import static utils.ConsoleUtils.lerStringValida;

public class DiscenteService {
    private static DiscenteRepository discenteRepository;
    private static UsuarioService usuarioService;
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

    public Discente autocadastroDiscente(String nome, String email, String senha, String matricula) {
        Usuario usuario = usuarioService.autocadastroUsuario(usuarioService, nome, email, senha);

        Curso curso = verificaCurso(discenteRepository.listaDiscente, matricula);

        if (verificaMatriculaDiscente(matricula, discenteRepository.listaDiscente)) {
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
            discenteRepository.listaDiscente.add(discente);
            return discente;

        }
        else{
            usuarioService.excluirUsuario(usuario, usuarioService);
            return null;
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

    public Curso verificaCurso (List <Discente> repositorio, String matricula){
        for (Discente d: repositorio){
            if (d.getMatricula().equals(matricula)) {
                return d.getCurso();
            }
        }
        return null;
    }
}