package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.forms.CargoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.repositories.CargoRepository;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo save(CargoForm cargoForm) {
        return cargoRepository.save(CargoForm.convertToCargo(cargoForm));
    }

    public Cargo findById(Long id) {
        return cargoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cargo com id " + id + " não encontrado."));
    }

    public List<Cargo> findByName(String cargo) {
        List<Cargo> cargoEmBuca = cargoRepository.findByCargo(cargo);
        if (!cargoEmBuca.isEmpty()) {
            return cargoEmBuca;
        }
        throw new EntityNotFoundException("Não foi possível achar cargo com nome " + "'" + cargo + "'" + ".");
    }

}
