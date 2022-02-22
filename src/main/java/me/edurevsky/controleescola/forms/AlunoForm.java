package me.edurevsky.controleescola.forms;

import java.time.LocalDate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.utils.GeradorDeEmail;

public class AlunoForm {

    @NotBlank
    @NotNull
    @NotEmpty
    private String nome;

    @CPF
    private String cpf;

    private Turno turno;

    @Min(value = 0)
    @Max(value = 10) // deve ser alterado
    private Long turma;

    public static Aluno convertToAluno(AlunoForm alunoForm) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoForm.getNome());
        aluno.setCpf(alunoForm.getCpf());
        aluno.setTurno(alunoForm.getTurno());
        aluno.setTurma(null);
        aluno.setDataMatricula(LocalDate.now());
        aluno.setEstaAtivo(true);
        aluno.setEmail(GeradorDeEmail.gerar(alunoForm.getNome()));
        return aluno;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public void setTurma(Long turma) {
        this.turma = turma;
    }

    public Long getTurma() {
        return turma;
    }

}
