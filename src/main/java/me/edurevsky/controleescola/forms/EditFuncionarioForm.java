package me.edurevsky.controleescola.forms;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EditFuncionarioForm {

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotNull(message = "O salário precisa ser preenchido")
    private BigDecimal salario;

    private Turno horarioDeTrabalho;

    private Cargo cargo;

    public EditFuncionarioForm() {

    }

    public EditFuncionarioForm(String nome, BigDecimal salario, Turno horarioDeTrabalho, Cargo cargo) {
        this.nome = nome;
        this.salario = salario;
        this.horarioDeTrabalho = horarioDeTrabalho;
        this.cargo = cargo;
    }

    public EditFuncionarioForm loadFromFuncionario(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.salario = funcionario.getSalario();
        this.horarioDeTrabalho = funcionario.getHorarioDeTrabalho();
        this.cargo = funcionario.getCargo();
        return this;
    }

    public static Funcionario update(Funcionario funcionario, EditFuncionarioForm editFuncionarioForm) {
        funcionario.setNome(editFuncionarioForm.getNome());
        funcionario.setSalario(editFuncionarioForm.getSalario());
        funcionario.setHorarioDeTrabalho(editFuncionarioForm.getHorarioDeTrabalho());
        funcionario.setCargo(editFuncionarioForm.getCargo());
        return funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
