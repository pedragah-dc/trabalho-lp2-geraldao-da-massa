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
//      responsavel.getGrupos().add(grupo);

      GrupoResponseDTO grupoResponseDTO = new GrupoResponseDTO(grupoDTO);
      grupoResponseDTO.setNomeDoResponsavel(responsavel.getNome());
      //O responsavel pode fazer muitas coisas
      MembroGrupo membroResponsavel = new MembroGrupo(responsavel);
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
      Grupo grupo = grupoRepository.findByIdGrupo(idGrupo);
      Usuario usuario = usuarioRepository.findById(idUser);
      MembroGrupo membroNovo = new MembroGrupo(usuario);
      membroGrupoRepository.save(membroNovo);
      grupo.getMembros().add(membroNovo);
      grupoRepository.save(grupo);
      MembroGrupoResponseDTO dto = new MembroGrupoResponseDTO(membroNovo);
      return dto;
   }
   //na teoria, só na tela do docente vai ter uma opção pra retirar e adiocionar cargos, entao nao acho que seja necessario uma verificacaao
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