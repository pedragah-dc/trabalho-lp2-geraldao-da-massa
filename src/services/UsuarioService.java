package services;

import entity.Oportunidade;
import entity.Usuario;
import repository.OportunidadeRepository;

import java.util.List;

public class UsuarioService {
    public void mudarSenha(Usuario usuario, String novaSenha){
        usuario.setSenha(novaSenha);
    }
    public List<Oportunidade> obterOportunidades(){
        OportunidadeRepository listaOportunidades = new OportunidadeRepository();
        return listaOportunidades.listaOportunidades;
    }
}
