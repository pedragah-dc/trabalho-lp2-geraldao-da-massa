package services;

import entity.Docente;
import entity.Oportunidade;
import entity.Usuario;
import entity.enums.StatusOportunidade;
import entity.enums.TiposModalidade;
import entity.enums.TiposOportunidade;

import java.time.LocalDateTime;


public class DiscenteDiretorService {
    public class DocenteService {
        public Oportunidade criarOportunidade(String titulo, String descricao, Enum<TiposOportunidade> tipo, Enum<TiposModalidade> modalidade, Integer cargaHoraria, Integer vagas, Enum<StatusOportunidade> statusOportunidade, LocalDateTime inicio, LocalDateTime fim, Usuario autor, Docente docente){
            System.out.println("criado uma op");
            return new Oportunidade(titulo, descricao, tipo, modalidade, cargaHoraria, vagas, statusOportunidade, inicio, fim, autor, docente);
        }
    }
}
