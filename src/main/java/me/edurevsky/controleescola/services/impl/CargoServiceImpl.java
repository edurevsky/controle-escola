package me.edurevsky.controleescola.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.services.CargoService;

@Service
public class CargoServiceImpl implements CargoService {
    
    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public Cargo findById(Long id) {
        return cargoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cargo com id " + id + " não encontrado."));
    }

    @Override
    public List<Cargo> findByName(String cargo) {
        List<Cargo> cargoEmBuca = cargoRepository.findByCargo(cargo);
        if (!cargoEmBuca.isEmpty()) {
            return cargoEmBuca;
        }
        throw new EntityNotFoundException("Não foi possível achar cargo com nome " + "'" + cargo + "'" + ".");
    }

}
