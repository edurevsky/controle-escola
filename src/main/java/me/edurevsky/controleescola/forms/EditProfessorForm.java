package me.edurevsky.controleescola.forms;

import me.edurevsky.controleescola.entities.Professor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EditProfessorForm {

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotNull(message = "O salário precisa ser preenchido")
    private BigDecimal salario;

    public EditProfessorForm() {

    }

    public EditProfessorForm(String nome, BigDecimal salario) {
        this.nome = nome;
        this.salario = salario;
    }

    public EditProfessorForm loadFromProfessor(Professor professor) {
        this.nome = professor.getNome();
        this.salario = professor.getSalario();
        return this;
    }

    public static Professor update(Professor professor, EditProfessorForm professorForm) {
        professor.setNome(professorForm.getNome());
        professor.setSalario(professorForm.getSalario());
        return professor;
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
}
