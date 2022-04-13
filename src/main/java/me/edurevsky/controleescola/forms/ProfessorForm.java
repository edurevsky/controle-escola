package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.validation.AlreadyRegisteredCpf;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProfessorForm {

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @CPF(message = "O CPF inserido é inválido")
    @AlreadyRegisteredCpf
    private String cpf;

    @NotNull(message = "O salário precisa ser preenchido")
    private BigDecimal salario;

    public static Professor convertToProfessor(ProfessorForm professorForm) {
        Professor professor = new Professor();
        professor.setNome(professorForm.getNome());
        professor.setCpf(professorForm.getCpf());
        professor.setSalario(professorForm.getSalario());
        return professor;
    }

    public ProfessorForm loadFromProfessor(Professor professor) {
        this.nome = professor.getNome();
        this.cpf = professor.getCpf();
        this.salario = professor.getSalario();
        return this;
    }
}
