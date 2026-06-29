package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.DTOs.inputs.DocenteRequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.PPCrequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.UsuarioRequestDTO;
import geraldao_da_massa.demo.DTOs.outputs.UsuarioResponseDTO;
import geraldao_da_massa.demo.entities.*;
import geraldao_da_massa.demo.entities.enums.RolesUsuario;
import geraldao_da_massa.demo.repositories.AdministradorRepository;
import geraldao_da_massa.demo.repositories.CursoRepository;
import geraldao_da_massa.demo.repositories.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

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
        adm.setEmail(dto.getEmail());
        adm.setSenha(dto.getSenha());
        adm.setRole(RolesUsuario.ADMINISTRADOR);
        adm.setPapel(new Papel("um administrador"));
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

        docente.setNome(docDTO.getNome());
        docente.setEmail(docDTO.getEmail());
        docente.setSenha(docDTO.getSenha());
        docente.setPapel(new Papel(""));
        docente.setAtivo(true);
        docente.setRole(RolesUsuario.DOCENTE);
//        docente.setGrupos(new HashSet<Grupo>());
        docente.setDepartamento(docDTO.getDepartamento());
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
