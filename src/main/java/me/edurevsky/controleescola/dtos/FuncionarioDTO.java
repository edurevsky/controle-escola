package me.edurevsky.controleescola.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;

public class FuncionarioDTO {
    
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

    public static Funcionario convertToFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setSalario(funcionarioDTO.getSalario());
        funcionario.setHorarioDeTrabalho(funcionarioDTO.getHorarioDeTrabalho());
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
