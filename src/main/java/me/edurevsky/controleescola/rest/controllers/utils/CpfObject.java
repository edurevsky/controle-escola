package me.edurevsky.controleescola.rest.controllers.utils;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class CpfObject {
    
    @CPF
    public String cpf;

}
