package me.edurevsky.controleescola.services;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.dtos.FuncionarioDTO;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.repositories.FuncionarioRepository;
import me.edurevsky.controleescola.services.contracts.assalariado.AlterarSalario;
import me.edurevsky.controleescola.services.contracts.funcionario.BuscaFuncionario;
import me.edurevsky.controleescola.services.contracts.funcionario.RegistroFuncionario;
import me.edurevsky.controleescola.services.contracts.pessoafisica.AlterarCpf;

@Service
public class FuncionarioService implements RegistroFuncionario, AlterarSalario, BuscaFuncionario, AlterarCpf {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public Funcionario registrarFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = FuncionarioDTO.convertToFuncionario(funcionarioDTO);
        funcionario.setCargo(cargoRepository.getById(funcionarioDTO.getCargo()));
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void removerFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Funcionário com id " + id + " não encontrado.");
        }
        funcionarioRepository.deleteById(id);
    }

    @Override
    public void alterarSalario(Long id, BigDecimal salario) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Funcionário com id " + id + " não encontrado.");
        }
        Funcionario funcionario = funcionarioRepository.getById(id);
        funcionario.setSalario(salario);
        funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Funcionário com id " + id + " não encontrado."));
    }

    @Override
    public void alterarCpf(Long id, String cpf) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Funcionário com id " + id + " não encontrado");
        }
        Funcionario funcionario = funcionarioRepository.getById(id);
        funcionario.setCpf(cpf);
        funcionarioRepository.save(funcionario);
    }

}
