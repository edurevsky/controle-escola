package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.Professor;

import java.math.BigDecimal;

@Data
public class ProfessorForm {

    private String nome;
    private String cpf;
    private BigDecimal salario;

    public ProfessorForm(String nome, String cpf, BigDecimal salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    public static Professor convertToProfessor(ProfessorForm professorForm) {
        Professor professor = new Professor();
        professor.setNome(professorForm.getNome());
        professor.setCpf(professorForm.getCpf());
        professor.setSalario(professorForm.getSalario());
        return professor;
    }

}
