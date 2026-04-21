package services;

import entity.Docente;
import entity.Oportunidade;
import entity.Usuario;
import entity.enums.StatusOportunidade;
import entity.enums.TiposModalidade;
import entity.enums.TiposOportunidade;

import java.util.Date;

public class DocenteService {
    public Oportunidade criarOportunidade(String titulo, String descricao, Enum<TiposOportunidade> tipo, Enum<TiposModalidade> modalidade, Integer cargaHoraria, Integer vagas, Enum<StatusOportunidade> statusOportunidade, Date inicio, Date fim, Usuario autor, Docente docente){
        return new Oportunidade(titulo, descricao, tipo, modalidade, cargaHoraria, vagas, statusOportunidade, inicio, fim, autor, docente);
    }
}
