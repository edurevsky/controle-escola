package me.edurevsky.controleescola.services.contracts.funcionario;

import me.edurevsky.controleescola.dtos.FuncionarioDTO;
import me.edurevsky.controleescola.entities.Funcionario;

public interface RegistroDeFuncionario {
    
    public Funcionario registrarFuncionario(FuncionarioDTO funcionarioDTO);

}
