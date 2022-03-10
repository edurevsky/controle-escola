package me.edurevsky.controleescola.forms;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Professor;

import java.math.BigDecimal;

@Getter
@Setter
public class ProfessorForm {

    private String nome;
    private String cpf;
    private BigDecimal salario;

    public static Professor convertToProfessor(ProfessorForm professorForm) {
        Professor professor = new Professor();
        professor.setNome(professorForm.getNome());
        professor.setCpf(professorForm.getCpf());
        professor.setSalario(professorForm.getSalario());
        return professor;
    }

}
