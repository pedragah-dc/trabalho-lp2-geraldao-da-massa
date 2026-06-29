package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.DTOs.inputs.DocenteRequestDTO;
import geraldao_da_massa.demo.entities.*;
import geraldao_da_massa.demo.repositories.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository admRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DocenteRepository docenterepository;
    @Autowired
    private CursoRepository cursoRepository;

    public UsuarioResponseDTO cadastrarAdmin(UsuarioRequestDTO dto){
        Administrador adm = new Administrador();
        adm.setNome(dto.getNome());
        adm.setAtivo(true);
        adm.setRole(dto.getRole());
        adm.setEmail(dto.getEmail());
        adm.setSenha(dto.getSenha());
        admRepository.save(adm);
        UsuarioResponseDTO userResponse = new UsuarioResponseDTO(adm);

        return userResponse;
    }


    public Docente cadastroDocente(Integer idAdm, DocenteRequestDTO docDTO){
        //TODO FAZER SISTEMA DE VERIFICACAO
        Administrador adm = admRepository.findById(idAdm).
                orElseThrow(() -> new RuntimeException("ADMINISTRADOR NAO ENCONTRADO: "+idAdm));

        if(docDTO.getSiape().isBlank() || docDTO.getDepartamento().isBlank()){
            throw new RuntimeException("INFORMAÇÕES DO DOCENTE FALTANDO");
        }

        Docente docente;
        docente = new Docente();
        //docente.setId(usuario.getId());
        docente.setNome(docDTO.getNome());
        docente.setEmail(docDTO.getEmail());
        docente.setSenha(docDTO.getSenha());
        docente.setPapel(new Papel(docDTO.getPapel()));
        docente.setAtivo(true);
        docente.setRole(docDTO.getRole());
        docente.setSiape(docDTO.getSiape());

        if(docente != null){
            docenterepository.save(docente);
            return docente;
        }

        return null;
    }


    public void cadastrarPPC(Integer id, PPCrequestDTO ppcDTO){
        Administrador admin = admRepository.findById(id).
                orElseThrow(() -> new RuntimeException("ADMINISTRADOR NAO ENCONTRADO: "+id));
        Curso curso = null;
        if(ppcDTO.getCursoNome() != null){
            if(!ppcDTO.getCursoNome().isBlank()){
                curso = cursoRepository.findByNome(ppcDTO.getCursoNome());
            }
        }
        if(curso != null){
            AlteracaoPermissao alteracaoPermissao = new AlteracaoPermissao(admin, LocalDateTime.now(), null, curso.getVersaoPPC());
            curso.setVersaoPPC(ppcDTO.getVersaoPPC());
            curso.setCargaHoraria(ppcDTO.getCargaHoraria());
            curso.getListaAlteracaoPPC().add(alteracaoPermissao);
            cursoRepository.save(curso);
        }
        if(curso == null){
            throw new RuntimeException("CURSO NAO ENCONTRADO");
        }

    }

}
