package me.edurevsky.controleescola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.dtos.FuncionarioDTO;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.repositories.FuncionarioRepository;
import me.edurevsky.controleescola.services.contracts.funcionario.RegistroFuncionario;

@Service
public class FuncionarioService implements RegistroFuncionario {
    
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

    

}
