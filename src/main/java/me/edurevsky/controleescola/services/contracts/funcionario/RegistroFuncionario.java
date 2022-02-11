package me.edurevsky.controleescola.services.contracts.funcionario;

import me.edurevsky.controleescola.dtos.FuncionarioDTO;
import me.edurevsky.controleescola.entities.Funcionario;

public interface RegistroFuncionario {
    
    public Funcionario registrarFuncionario(FuncionarioDTO funcionarioDTO);

    public void removerFuncionario(Long id);

}
