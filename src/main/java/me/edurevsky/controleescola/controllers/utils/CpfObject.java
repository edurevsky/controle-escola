package me.edurevsky.controleescola.controllers.utils;

import org.hibernate.validator.constraints.br.CPF;

public class CpfObject {
    
    @CPF
    public String cpf;

    public CpfObject() {

    }

    public CpfObject(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
