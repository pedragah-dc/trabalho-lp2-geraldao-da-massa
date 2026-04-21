package services;

import entity.Oportunidade;
import repository.OportunidadeRepository;

import java.time.LocalDateTime;
import java.util.List;

public class OportunidadesService {

    public void publicar(Oportunidade oportunidade, OportunidadeRepository lista){
        lista.listaOportunidades.add(oportunidade);
        System.out.println("PUBLICADO!");
    }

    public void fecharInscricoes(Oportunidade oportunidade){
        if (!oportunidade.isFinalizada()){
            oportunidade.setFim(LocalDateTime.now());
            System.out.println("finalizado");
        }
    }
}
