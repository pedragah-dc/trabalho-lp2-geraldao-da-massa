package services;

import entity.Docente;
import entity.Oportunidade;
import entity.Usuario;
import entity.enums.StatusOportunidade;
import entity.enums.TiposModalidade;
import entity.enums.TiposOportunidade;

import java.time.LocalDateTime;

public class DiscenteDiretorService {
    private final OportunidadesService oportunidadeService;

    public DiscenteDiretorService(OportunidadesService oportunidadeService) {
        this.oportunidadeService = oportunidadeService;
    }

    public Oportunidade criarOportunidade(String titulo, String descricao, Enum<TiposOportunidade> tipo,
            Enum<TiposModalidade> modalidade, Integer cargaHoraria, Integer vagas,
            Enum<StatusOportunidade> statusOportunidade, LocalDateTime inicio, LocalDateTime fim, Usuario autor,
            Docente docente) {
        System.out.println("Criando uma Oportunidade...");
        return this.oportunidadeService.publicar(titulo, descricao, tipo, modalidade, cargaHoraria, vagas,
                statusOportunidade, inicio, fim, autor, docente);
    }
}
