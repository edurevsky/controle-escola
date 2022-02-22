package me.edurevsky.controleescola.forms;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;

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
    private Long cargo;

    public static Funcionario convertToFuncionario(FuncionarioForm funcionarioForm) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioForm.getNome());
        funcionario.setCpf(funcionarioForm.getCpf());
        funcionario.setSalario(funcionarioForm.getSalario());
        funcionario.setHorarioDeTrabalho(funcionarioForm.getHorarioDeTrabalho());
        funcionario.setCargo(null);
        return funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Turno getHorarioDeTrabalho() {
        return horarioDeTrabalho;
    }

    public void setHorarioDeTrabalho(Turno horarioDeTrabalho) {
        this.horarioDeTrabalho = horarioDeTrabalho;
    }

    public Long getCargo() {
        return cargo;
    }

    public void setCargo(Long cargo) {
        this.cargo = cargo;
    }

}
