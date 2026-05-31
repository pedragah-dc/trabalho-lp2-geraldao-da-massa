package services;

import entity.Discente;
import entity.Docente;
import entity.Oportunidade;
import entity.Usuario;
import entity.enums.StatusOportunidade;
import entity.enums.TiposModalidade;
import entity.enums.TiposOportunidade;
import repository.OportunidadeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OportunidadesService {

    private OportunidadeRepository listaOportunidades = new OportunidadeRepository();

    public OportunidadesService(OportunidadeRepository listaOportunidades) {
        this.listaOportunidades = listaOportunidades;
    }

    public List<Oportunidade> listarOportunidadesAtivas(){
        List<Oportunidade> oportunidadesAtivas = new ArrayList<>();
        for (Oportunidade oportunidade : listaOportunidades.listaOportunidades) {
            if (!oportunidade.isFinalizada()) {
                oportunidadesAtivas.add(oportunidade);
            }
        }
        return oportunidadesAtivas;
    }

    public Oportunidade publicar(String titulo, String descricao, Enum<TiposOportunidade> tipo, Enum<TiposModalidade> modalidade, Integer cargaHoraria, Integer vagas, Enum<StatusOportunidade> statusOportunidade, LocalDateTime inicio, LocalDateTime fim, Usuario autor, Docente docente){
        Oportunidade oportunidade = new Oportunidade(titulo, descricao, tipo, modalidade, cargaHoraria, vagas, statusOportunidade, inicio, fim, autor, docente);
        listaOportunidades.listaOportunidades.add(oportunidade);
        System.out.println("PUBLICADO!");
        return oportunidade;
    }

    public void fecharInscricoes(Oportunidade oportunidade){
        if (!oportunidade.isFinalizada()){
            oportunidade.setFim(LocalDateTime.now());
            System.out.println("finalizado");
        }
    }

    public List<Oportunidade> retornaOportunidadeDiscente(List<Oportunidade> lista, Discente discente ){
        List<Oportunidade> retorno = new ArrayList<>();
        for(Oportunidade i : lista){
            for (Oportunidade j : discente.getListaDeOp()){
                if (i.equals(j)){
                    retorno.add(j);
                }
            }
        }
        return retorno;
    }


}
