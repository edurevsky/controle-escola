package me.edurevsky.controleescola.services;

import java.util.List;

import me.edurevsky.controleescola.entities.Cargo;

public interface CargoService {
    
    public Cargo findById(Long id);

    public List<Cargo> findByName(String cargo);

}
