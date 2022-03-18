package me.edurevsky.controleescola.forms;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Cargo;
import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class FuncionarioForm {
    
    @NotBlank
    private String nome;

    @CPF
    private String cpf;

    @NotNull
    private BigDecimal salario;

    @NotNull
    private Turno horarioDeTrabalho;

    @NotNull
    private Cargo cargo;

    public FuncionarioForm(String nome, String cpf, BigDecimal salario, Turno horarioDeTrabalho, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.horarioDeTrabalho = horarioDeTrabalho;
        this.cargo = cargo;
    }

    public static Funcionario convertToFuncionario(FuncionarioForm funcionarioForm) {
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioForm, funcionario);
        return funcionario;
    }

}
