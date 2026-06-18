package geraldao_da_massa.demo.services;


import geraldao_da_massa.demo.entity.*;
import geraldao_da_massa.demo.entity.enums.CargoNoGrupo;
import geraldao_da_massa.demo.entity.enums.StatusGrupo;

public class GrupoService {


   public Grupo criarGrupo(String nome, String tipo, String email, String descricao, StatusGrupo status, Docente responsavel){
      //TODO validação
      return new Grupo(nome, tipo, email,descricao, status, responsavel);
   }
   public void adicionarMembro(Grupo grupo, Usuario usuario){
      System.out.println("adicionado");
      MembroGrupo membro = new MembroGrupo(usuario);
      if(grupo != null) grupo.adicionarMembro(membro);
   }
   //na teoria, só na tela do docente vai ter uma opção pra retirar e adiocionar cargos, entao nao acho que seja necessario uma verificacaao
   public void setCargo(MembroGrupo membro, CargoNoGrupo cargo){
      MembroService memberService = new MembroService();
      if(cargo != null && membro != null){
         memberService.setCargo(membro, cargo);
      }
   }
}
