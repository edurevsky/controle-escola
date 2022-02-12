package me.edurevsky.controleescola.services.contracts.cargos;

import java.util.List;

import me.edurevsky.controleescola.entities.Cargo;

public interface BuscaCargo {
    
    public Cargo buscarPorId(Long id);

    public List<Cargo> buscarPorNomeDoCargo(String cargo);

}
