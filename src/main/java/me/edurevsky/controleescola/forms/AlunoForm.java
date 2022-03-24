package me.edurevsky.controleescola.forms;

import java.time.LocalDate;

import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Turma;
import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.utils.GeradorDeEmail;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class AlunoForm {

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @CPF(message = "O CPF inserido é inválido")
    private String cpf;

    private Turno turno;

    private Turma turma;

    public static Aluno convertToAluno(AlunoForm alunoForm) {
        Aluno aluno = new Aluno();
        BeanUtils.copyProperties(alunoForm, aluno);
        aluno.setDataMatricula(LocalDate.now());
        aluno.setEstaAtivo(true);
        aluno.setEmail(GeradorDeEmail.gerarEmailParaAluno(alunoForm.getNome()));
        return aluno;
    }

}
