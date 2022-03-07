package me.edurevsky.controleescola.services;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.forms.FuncionarioForm;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.repositories.FuncionarioRepository;
import me.edurevsky.controleescola.services.utils.Handlers;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private static final String NOT_FOUND_MESSAGE = "Funcionario com id %d não encontrado.";

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
    }

    public Funcionario save(FuncionarioForm funcionarioForm) {
        Funcionario funcionario = FuncionarioForm.convertToFuncionario(funcionarioForm);
        funcionario.setCargo(cargoRepository.getById(funcionarioForm.getCargo()));
        return funcionarioRepository.save(funcionario);
    }

    public void remove(Long id) {
        Handlers.handleEntityNotFound(funcionarioRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        
        funcionarioRepository.deleteById(id);
    }

    public void updateSalary(Long id, BigDecimal salario) {
        Handlers.handleEntityNotFound(funcionarioRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Funcionario funcionario = funcionarioRepository.getById(id);
        funcionario.setSalario(salario);
        funcionarioRepository.save(funcionario);
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Funcionário com id " + id + " não encontrado."));
    }

    public void updateCpf(Long id, String cpf) {
        Handlers.handleEntityNotFound(funcionarioRepository, id, "Funcionario com id " + id + " não encontrado.");

        Funcionario funcionario = funcionarioRepository.getById(id);
        funcionario.setCpf(cpf);
        funcionarioRepository.save(funcionario);
    }

}
