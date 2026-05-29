package services;

import entity.Oportunidade;
import entity.Usuario;
import repository.OportunidadeRepository;

import java.util.List;

public class UsuarioService {

    private OportunidadeRepository oportunidadeRepository;

    public UsuarioService(OportunidadeRepository oportunidadeRepository) {
        this.oportunidadeRepository = oportunidadeRepository;
    }

    public void mudarSenha(Usuario usuario, String novaSenha) {
        if (novaSenha == null || novaSenha.isBlank()) {
            throw new IllegalArgumentException("Nova senha não pode ser vazia.");
        }
        usuario.setSenha(novaSenha);
        System.out.println("Senha alterada para o usuário: " + usuario.getNome());
    }

    public List<Oportunidade> obterOportunidades() {
        return oportunidadeRepository.listarTodas();
    }
}
