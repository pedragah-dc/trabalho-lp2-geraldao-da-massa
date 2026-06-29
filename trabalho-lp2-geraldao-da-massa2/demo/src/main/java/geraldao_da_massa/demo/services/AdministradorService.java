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
    private UsuarioService usuarioService;
    @Autowired
    private final DocenteRepository docenterepository;

    public AdministradorService(UsuarioService usuarioService, DocenteRepository docenteRepository){
        this.usuarioService = usuarioService;
        this.docenterepository = docenteRepository;
    }


    public Docente cadastroDocente(DocenteRequestDTO docDTO){
        //TODO FAZER SISTEMA DE VERIFICACAO
        Usuario usuario = usuarioService.autocadastroUsuario(docDTO);

        Docente docente = new Docente();
        docente.setId(usuario.getId());
        docente.setNome(usuario.getNome());
        docente.setEmail(usuario.getEmail());
        docente.setSenha(usuario.getSenha());docente.setPapel(new Papel(docDTO.getPapel()));
        docente.setAtivo(usuario.getAtivo()); docente.setRole(docDTO.getRole());
        docente.setSiape(docDTO.getSiape());

        docenterepository.save(docente);

        return docente;
    }

    public void cadastrarPPC(Curso curso, String versaoPPC, Integer cargaHoraria, Administrador admin){
        AlteracaoPermissao alteracaoPermissao = new AlteracaoPermissao(admin, LocalDateTime.now(), null, curso.getVersaoPPC());
        curso.setVersaoPPC(versaoPPC);
        curso.setCargaHoraria(cargaHoraria);
        curso.getListaAlteracaoPPC().add(alteracaoPermissao);

    }

}
