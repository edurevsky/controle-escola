package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.services.contracts.cargos.BuscaCargo;

@Service
public class CargoService implements BuscaCargo {
    
    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public Cargo buscarPorId(Long id) {
        return cargoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cargo com id " + id + " não encontrado."));
    }

    @Override
    public List<Cargo> buscarPorNomeDoCargo(String cargo) {
        List<Cargo> cargoEmBuca = cargoRepository.findByCargo(cargo);
        if (!cargoEmBuca.isEmpty()) {
            return cargoEmBuca;
        }
        throw new EntityNotFoundException("Não foi possível achar cargo com nome " + "'" + cargo + "'" + ".");
    }

}
