package me.edurevsky.controleescola.services;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.forms.FuncionarioForm;

public interface FuncionarioService {
    
    public Funcionario save(FuncionarioForm funcionarioForm);

    public void remove(Long id);

    public Funcionario findById(Long id);

}
