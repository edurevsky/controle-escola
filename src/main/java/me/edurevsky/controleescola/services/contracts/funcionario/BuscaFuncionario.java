package me.edurevsky.controleescola.services.contracts.funcionario;

import me.edurevsky.controleescola.entities.Funcionario;

public interface BuscaFuncionario {
    
    public Funcionario buscarPorId(Long id);

}
