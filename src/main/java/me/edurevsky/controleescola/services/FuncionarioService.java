package me.edurevsky.controleescola.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.dtos.FuncionarioDTO;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.repositories.FuncionarioRepository;
import me.edurevsky.controleescola.services.contracts.assalariado.AlterarSalario;
import me.edurevsky.controleescola.services.contracts.funcionario.BuscaFuncionario;
import me.edurevsky.controleescola.services.contracts.funcionario.RegistroFuncionario;

@Service
public class FuncionarioService implements RegistroFuncionario, AlterarSalario, BuscaFuncionario {
    
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
    public Boolean removerFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            return false;
        }
        funcionarioRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean alterarSalario(Long id, BigDecimal salario) {
        if (!funcionarioRepository.existsById(id)) {
            return false;
        }
        Funcionario funcionario = funcionarioRepository.getById(id);
        funcionario.setSalario(salario);
        funcionarioRepository.save(funcionario);
        return true;
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
            .orElseThrow( /**TODO */ );
    }

}
