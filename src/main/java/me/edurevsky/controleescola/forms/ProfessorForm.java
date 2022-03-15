package me.edurevsky.controleescola.forms;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Professor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProfessorForm {

    @NotNull
    @NotBlank
    @NotEmpty
    private String nome;

    @CPF
    private String cpf;

    @NotNull
    private BigDecimal salario;

    public static Professor convertToProfessor(ProfessorForm professorForm) {
        Professor professor = new Professor();
        professor.setNome(professorForm.getNome());
        professor.setCpf(professorForm.getCpf());
        professor.setSalario(professorForm.getSalario());
        return professor;
    }

}
