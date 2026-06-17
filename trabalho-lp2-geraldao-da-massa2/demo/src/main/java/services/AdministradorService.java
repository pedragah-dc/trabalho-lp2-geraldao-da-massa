package services;

import entity.*;
import entity.enums.RolesUsuario;
import repository.DocenteRepository;

import java.time.LocalDateTime;

public class AdministradorService {
    private final UsuarioService usuarioService;
    private final DocenteRepository docenterepository;

    public AdministradorService(UsuarioService usuarioService, DocenteRepository docenteRepository){
        this.usuarioService = usuarioService;
        this.docenterepository = docenteRepository;
    }


    public Docente cadastroDocente(String nome, String email, String senha, String siape, String departamento, RolesUsuario role){

        Usuario usuario = usuarioService.autocadastroUsuario(usuarioService, nome, email, senha);

        Docente docente = new Docente(usuario.getId(), usuario.getNome(), usuario.getEmail(),
                usuario.getSenha(), usuario.getPapel(), usuario.getAtivo(), role, siape, departamento);

        docenterepository.listaDocentes.add(docente);

        return docente;
    }

    public void cadastrarPPC(Curso curso, String versaoPPC, Integer cargaHoraria, Administrador admin){
        AlteracaoPermissao alteracaoPermissao = new AlteracaoPermissao(admin, LocalDateTime.now(), null, curso.getVersaoPPC());
        curso.setVersaoPPC(versaoPPC);
        curso.setCargaHoraria(cargaHoraria);
        curso.getListaAlteracaoPPC().add(alteracaoPermissao);

    }

}
