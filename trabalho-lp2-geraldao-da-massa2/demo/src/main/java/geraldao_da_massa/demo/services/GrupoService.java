package geraldao_da_massa.demo.services;


import geraldao_da_massa.demo.entities.*;
import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;
import geraldao_da_massa.demo.entities.enums.StatusGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {

   @Autowired
   private MembroService membroService;


   public void adicionarMembro(Grupo grupo, Usuario usuario){
      System.out.println("adicionado");
      MembroGrupo membro = new MembroGrupo(usuario);

   }
   //na teoria, só na tela do docente vai ter uma opção pra retirar e adiocionar cargos, entao nao acho que seja necessario uma verificacaao
   public void setCargo(int idMembro, CargoNoGrupo cargo){

   }
}
