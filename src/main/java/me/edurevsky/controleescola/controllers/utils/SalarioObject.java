package me.edurevsky.controleescola.controllers.utils;

import java.math.BigDecimal;

public class SalarioObject {
    
    private BigDecimal salario;

    public SalarioObject() {

    }

    public SalarioObject(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

}
