package me.edurevsky.controleescola.dtos;

import java.math.BigDecimal;

import me.edurevsky.controleescola.entities.Funcionario;

public class FuncionarioDTO {
    
    private String nome;

    private String cpf;

    private BigDecimal salario;

    private Long cargo;

    public static Funcionario convertToFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setSalario(funcionarioDTO.getSalario());
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

    public Long getCargo() {
        return cargo;
    }

    public void setCargo(Long cargo) {
        this.cargo = cargo;
    }

}
