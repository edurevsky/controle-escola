package me.edurevsky.controleescola.services;

import java.math.BigDecimal;

public interface AlterarSalarioService {
    
    public void updateSalary(Long id, BigDecimal salario);
    
}
