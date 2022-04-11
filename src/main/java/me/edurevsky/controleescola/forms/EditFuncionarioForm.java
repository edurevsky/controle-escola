package me.edurevsky.controleescola.forms;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.embeddables.HorarioDeTrabalho;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalTime;

public class EditFuncionarioForm {

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotNull(message = "O salário precisa ser preenchido")
    private BigDecimal salario;

    private LocalTime horarioInicio;

    private LocalTime horarioFinal;

    private Cargo cargo;

    public EditFuncionarioForm() {

    }

    public EditFuncionarioForm(String nome, BigDecimal salario, LocalTime horarioInicio, LocalTime horarioFinal, Cargo cargo) {
        this.nome = nome;
        this.salario = salario;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.cargo = cargo;
    }

    public EditFuncionarioForm loadFromFuncionario(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.salario = funcionario.getSalario();
        this.horarioInicio = funcionario.getHorarioDeTrabalho().getHorarioInicio();
        this.horarioFinal = funcionario.getHorarioDeTrabalho().getHorarioFinal();
        this.cargo = funcionario.getCargo();
        return this;
    }

    public static Funcionario update(Funcionario funcionario, EditFuncionarioForm editFuncionarioForm) {
        funcionario.setNome(editFuncionarioForm.getNome());
        funcionario.setSalario(editFuncionarioForm.getSalario());
        funcionario.setCargo(editFuncionarioForm.getCargo());

        HorarioDeTrabalho horarioDeTrabalho = new HorarioDeTrabalho();
        horarioDeTrabalho.setHorarioInicio(editFuncionarioForm.getHorarioInicio());
        horarioDeTrabalho.setHorarioFinal(editFuncionarioForm.getHorarioFinal());

        funcionario.setHorarioDeTrabalho(horarioDeTrabalho);
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

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
