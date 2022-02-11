package me.edurevsky.controleescola.services.contracts.assalariado;

import java.math.BigDecimal;

public interface AlterarSalario {
    
    public void alterarSalario(Long id, BigDecimal salario);
    
}
