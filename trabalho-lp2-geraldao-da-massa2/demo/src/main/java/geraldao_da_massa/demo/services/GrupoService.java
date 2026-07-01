package geraldao_da_massa.demo.services;


import geraldao_da_massa.demo.DTOs.inputs.GrupoRequestDTO;
import geraldao_da_massa.demo.DTOs.outputs.GrupoResponseDTO;
import geraldao_da_massa.demo.DTOs.outputs.MembroGrupoResponseDTO;
import geraldao_da_massa.demo.entities.*;
import geraldao_da_massa.demo.entities.enums.CargoNoGrupo;
import geraldao_da_massa.demo.entities.enums.StatusGrupo;
import geraldao_da_massa.demo.repositories.DocenteRepository;
import geraldao_da_massa.demo.repositories.GrupoRepository;
import geraldao_da_massa.demo.repositories.MembroGrupoRepository;
import geraldao_da_massa.demo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//fica responsavel por mexer com os membros tmb
@Service
public class GrupoService {

   @Autowired
   private GrupoRepository grupoRepository;
   @Autowired
   private UsuarioRepository usuarioRepository;
   @Autowired
   private MembroGrupoRepository membroGrupoRepository;
   @Autowired
   private DocenteRepository docenteRepository;


   public GrupoResponseDTO criarGrupo(GrupoRequestDTO grupoDTO){
      Grupo grupo = new Grupo(grupoDTO);
      Docente responsavel = docenteRepository.findById(grupoDTO.getIdResponsavel()).
              orElseThrow(() -> new RuntimeException("nao existe esse docente em tal ID"));

      if(grupoRepository.existsGrupoByEmail(grupoDTO.getEmail())){
         throw new RuntimeException("este email ja foi cadastrado");
      }

         grupo.setResponsavel(responsavel);
         grupo.setStatus(StatusGrupo.ATIVO);
//      responsavel.getGrupos().add(grupo);

      GrupoResponseDTO grupoResponseDTO = new GrupoResponseDTO(grupo);
      grupoResponseDTO.setResponsavel(responsavel.getNome());
      //O responsavel pode fazer muitas coisas
      MembroGrupo membroResponsavel = new MembroGrupo(responsavel, grupo);
      membroResponsavel.setCargo(CargoNoGrupo.RESPONSAVEL);

   //salvando alteracoes
      docenteRepository.save(responsavel);
      grupoRepository.save(grupo);
      membroGrupoRepository.save(membroResponsavel);
      return grupoResponseDTO;

   }

   //no drawio, um membro é um usuario, entao tanto docente quanto discente podem ser membros
   //talvez sempre que um membro entrar ele já comece num determinado cargo(ou template de membro) e posteriormente possa mudar
   public MembroGrupoResponseDTO adicionarMembro(int idGrupo, int idUser){
      Grupo grupo = grupoRepository.findById(idGrupo).orElseThrow(() -> new RuntimeException("NÃO EXISTE GRUPO COM ESTE ID"));
      Usuario usuario = usuarioRepository.findById(idUser).orElseThrow(()->new RuntimeException("NAO EXISTE USUARIO COM ESTE ID"));
      //verificar se nao ta adicionando o mesmo cara
      for(MembroGrupo membro: grupo.getMembros()){
         if(membro.getMembro().equals(usuario)){
            throw new RuntimeException("ESTE USUARIO JÁ ESTÁ NESTE GRUPPO");
         }
      }
      MembroGrupo membroNovo = new MembroGrupo(usuario, grupo);
      membroGrupoRepository.save(membroNovo);
      grupo.getMembros().add(membroNovo);
      grupoRepository.save(grupo);
      MembroGrupoResponseDTO dto = new MembroGrupoResponseDTO(membroNovo);
      return dto;
   }
   //errado kkkkkkkk, vai achando q é assim. tem que verificar se tem permissao. como?
   public MembroGrupoResponseDTO setCargo(int id, CargoNoGrupo cargo){
      MembroGrupo membro = membroGrupoRepository.findById(id).orElseThrow(() -> new RuntimeException("membro nao encontrado"));
      membro.setCargo(cargo);
      membroGrupoRepository.save(membro);
      MembroGrupoResponseDTO dto = new MembroGrupoResponseDTO(membro);
      return dto;
   }
}

//força, rapaziada