package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.forms.CargoForm;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.repositories.CargoRepository;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;
    private static final String NOT_FOUND_MESSAGE = "Cargo com id %d não encontrado";
    private static final String NAME_NOT_FOUND_MESSAGE = "Cargo com nome %s não encontrado";

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo save(CargoForm cargoForm) {
        return cargoRepository.save(CargoForm.convertToCargo(cargoForm));
    }

    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    public Cargo findById(Long id) {
        return cargoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
    }

    public List<Cargo> findByName(String cargo) {
        List<Cargo> cargoEmBuca = cargoRepository.findByCargo(cargo);
        if (!cargoEmBuca.isEmpty()) {
            return cargoEmBuca;
        }
        throw new EntityNotFoundException(String.format(NAME_NOT_FOUND_MESSAGE, cargo));
    }

    public void remove(Long id) {
        Handlers.handleEntityNotFound(cargoRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        cargoRepository.deleteById(id);
    }

}
