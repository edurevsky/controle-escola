package me.edurevsky.controleescola.services.impl;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.forms.FuncionarioForm;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.repositories.FuncionarioRepository;
import me.edurevsky.controleescola.services.AlterarCpfService;
import me.edurevsky.controleescola.services.AlterarSalarioService;
import me.edurevsky.controleescola.services.FuncionarioService;
import me.edurevsky.controleescola.services.utils.Handlers;

@Service
public class FuncionarioServiceImpl implements FuncionarioService, AlterarSalarioService, AlterarCpfService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public Funcionario save(FuncionarioForm funcionarioForm) {
        Funcionario funcionario = FuncionarioForm.convertToFuncionario(funcionarioForm);
        funcionario.setCargo(cargoRepository.getById(funcionarioForm.getCargo()));
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void remove(Long id) {
        Handlers.handleEntityNotFound(funcionarioRepository, id, "Funcionario com id " + id + " não encontrado.");
        
        funcionarioRepository.deleteById(id);
    }

    @Override
    public void updateSalary(Long id, BigDecimal salario) {
        Handlers.handleEntityNotFound(funcionarioRepository, id, "Funcionario com id " + id + " não encontrado.");

        Funcionario funcionario = funcionarioRepository.getById(id);
        funcionario.setSalario(salario);
        funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Funcionário com id " + id + " não encontrado."));
    }

    @Override
    public void updateCpf(Long id, String cpf) {
        Handlers.handleEntityNotFound(funcionarioRepository, id, "Funcionario com id " + id + " não encontrado.");

        Funcionario funcionario = funcionarioRepository.getById(id);
        funcionario.setCpf(cpf);
        funcionarioRepository.save(funcionario);
    }

}
