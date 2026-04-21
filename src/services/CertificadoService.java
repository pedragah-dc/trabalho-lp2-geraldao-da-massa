package services;

import entity.Certificado;
import repository.CertificadoRepository;

import java.util.List;

public class CertificadoService {

    public void gerarQRCode(){



    }

    public boolean verificarAutenticidade(String hash){
        List<Certificado> listaCertificado = new CertificadoRepository().listaCertificado;


        for (Certificado certificado:listaCertificado){
            if (certificado.getUuidHash() == hash){
                return true;
            }
        }
    return false;
    }
}
