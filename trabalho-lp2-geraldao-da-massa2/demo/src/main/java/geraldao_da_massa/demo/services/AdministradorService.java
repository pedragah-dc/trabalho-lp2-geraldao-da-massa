package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.DTOs.DocenteRequestDTO;
import geraldao_da_massa.demo.entities.*;
import geraldao_da_massa.demo.repositories.CursoRepository;
import geraldao_da_massa.demo.repositories.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdministradorService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DocenteRepository docenterepository;
    @Autowired
    private CursoRepository cursoRepository;



    public Docente cadastroDocente(DocenteRequestDTO docDTO){
        //TODO FAZER SISTEMA DE VERIFICACAO
        if(docDTO.getSiape().isBlank() || docDTO.getDepartamento().isBlank()){
            throw new RuntimeException("INFORMAÇÕES DO DOCENTE FALTANDO");
        }
        Usuario usuario = usuarioService.autocadastroUsuario(docDTO);
        //se o cadastro for com êxito, entao usuario foi salvo no banco
        Docente docente = null;
        if(usuario != null){
            docente = new Docente();
            docente.setId(usuario.getId());
            docente.setNome(usuario.getNome());
            docente.setEmail(usuario.getEmail());
            docente.setSenha(usuario.getSenha());
            docente.setPapel(new Papel(docDTO.getPapel()));
            docente.setAtivo(usuario.getAtivo());
            docente.setRole(docDTO.getRole());
            docente.setSiape(docDTO.getSiape());
        }

        if(docente != null){
            docenterepository.save(docente);
            return docente;
        }

        return null;
    }


    public void cadastrarPPC(String cursoNome, String versaoPPC, Integer cargaHoraria, Administrador admin){
        Curso curso = null;
        if(cursoNome != null){
            if(!cursoNome.isBlank()){
                curso = cursoRepository.findByNome(cursoNome);
            }
        }
        if(curso != null){
            AlteracaoPermissao alteracaoPermissao = new AlteracaoPermissao(admin, LocalDateTime.now(), null, curso.getVersaoPPC());
            curso.setVersaoPPC(versaoPPC);
            curso.setCargaHoraria(cargaHoraria);
            curso.getListaAlteracaoPPC().add(alteracaoPermissao);
            cursoRepository.save(curso);
        }
        if(curso == null){
            throw new RuntimeException("CURSO NAO ENCONTRADO");
        }

    }

}
