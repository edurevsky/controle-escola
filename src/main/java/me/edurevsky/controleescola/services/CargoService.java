package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.forms.CargoForm;
import me.edurevsky.controleescola.repositories.FuncionarioRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.repositories.CargoRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author edurevsky
 * @version 1.0
 */
@Service
public class CargoService {

    private final CargoRepository cargoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private static final String NOT_FOUND_MESSAGE = "Cargo com id %d não encontrado";
    private static final String NAME_NOT_FOUND_MESSAGE = "Cargo com nome %s não encontrado";

    @Autowired
    public CargoService(CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository) {
        this.cargoRepository = cargoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Cargo save(CargoForm cargoForm) {
        return cargoRepository.save(CargoForm.convertToCargo(cargoForm));
    }

    @Transactional
    public Cargo update(Long id, CargoForm cargoForm) {
        Cargo cargo = cargoRepository.getById(id);
        BeanUtils.copyProperties(cargoForm, cargo);
        return cargoRepository.save(cargo);
    }

    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    /**
     * Returns a Cargo object if the id exists,
     * else throws a Exception
     *
     * @param id the cargo id
     * @return Cargo
     * @throws EntityNotFoundException if id not exists
     */
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

    /**
     * If the id exists, removes the cargo and
     * sets all the related funcionarios cargo to null
     *
     * @param id The cargo id
     */
    @Transactional
    public void remove(Long id) {
        Handlers.handleEntityNotFound(cargoRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        List<Funcionario> funcionarios = cargoRepository.getById(id).getFuncionarios();
        funcionarios.forEach((funcionario) -> funcionario.setCargo(null));
        funcionarioRepository.saveAll(funcionarios);
        cargoRepository.deleteById(id);
    }

    public Page<Cargo> findPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return cargoRepository.findAll(pageable);
    }

}
