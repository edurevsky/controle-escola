package me.edurevsky.controleescola.services.contracts.assalariado;

import java.math.BigDecimal;

public interface AlterarSalario {
    
    public Boolean alterarSalario(Long id, BigDecimal salario);
    
}
