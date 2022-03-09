package me.edurevsky.controleescola.forms;

import java.time.LocalDate;

import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.utils.GeradorDeEmail;

@Getter
@Setter
public class AlunoForm {

    @NotBlank
    @NotNull
    @NotEmpty
    private String nome;

    @CPF
    private String cpf;

    private Turno turno;

    public static Aluno convertToAluno(AlunoForm alunoForm) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoForm.getNome());
        aluno.setCpf(alunoForm.getCpf());
        aluno.setTurno(alunoForm.getTurno());
        aluno.setDataMatricula(LocalDate.now());
        aluno.setEstaAtivo(true);
        aluno.setEmail(GeradorDeEmail.gerarEmailParaAluno(alunoForm.getNome()));
        return aluno;
    }

}
