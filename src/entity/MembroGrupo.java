package entity;

import entity.enums.CargoNoGrupo;

public class MembroGrupo {
    private Discente discente;
    private CargoNoGrupo cargo;


    public MembroGrupo(Discente discente){
        this.discente = discente;
        this.cargo = CargoNoGrupo.MEMBRO;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public CargoNoGrupo getCargo() {
        return cargo;
    }

    public void setCargo(CargoNoGrupo cargo) {
        this.cargo = cargo;
    }
    public String getNome(){
        return discente.getNome();
    }
}
