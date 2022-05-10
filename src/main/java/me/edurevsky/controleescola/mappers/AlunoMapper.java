package me.edurevsky.controleescola.mappers;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.forms.EditAlunoForm;
import me.edurevsky.controleescola.utils.GeradorDeEmail;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AlunoMapper {

    public Aluno convertToAluno(AlunoForm alunoForm) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoForm.getNome());
        aluno.setCpf(alunoForm.getCpf());
        aluno.setTurno(alunoForm.getTurno());
        aluno.setTurma(alunoForm.getTurma());
        aluno.setDataMatricula(LocalDate.now());
        aluno.setEstaAtivo(true);
        aluno.setEmail(GeradorDeEmail.gerarEmailParaAluno(alunoForm.getNome()));
        return aluno;
    }

    public Aluno update(Aluno aluno, EditAlunoForm alunoForm) {
        aluno.setNome(alunoForm.getNome());
        aluno.setEstaAtivo(alunoForm.getEstaAtivo());
        aluno.setTurno(alunoForm.getTurno());
        aluno.setTurma(alunoForm.getTurma());
        aluno.setEmail(GeradorDeEmail.gerarEmailParaAluno(alunoForm.getNome()));
        return aluno;
    }
}
