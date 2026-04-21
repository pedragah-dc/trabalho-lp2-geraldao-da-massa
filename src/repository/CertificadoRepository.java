package repository;


import entity.Certificado;
import repository.DiscenteRepository;
import repository.OportunidadeRepository;


import java.time.LocalDateTime;
import java.util.List;

public class CertificadoRepository {
    public List<Certificado> listaCertificado;

    public CertificadoRepository(){
        listaCertificado.add(
                new Certificado(
                        "1234",
                        new DiscenteRepository().listaDiscente.getFirst(),
                        new OportunidadeRepository().listaOportunidades.getFirst(),
                        LocalDateTime.now(),
                        90,
                        "C:\\Users\\super\\OneDrive\\Desktop\\Documentos\\GitHub",
                        false
                )
        );
    }

}
