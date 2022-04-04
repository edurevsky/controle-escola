package me.edurevsky.controleescola.forms;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.entities.enums.Turno;

import javax.validation.constraints.NotEmpty;

public class EditAlunoForm {

    @NotEmpty(message = "O nome não pode estar em branco")
    private String nome;

    // CPF não é editável
    private String cpf;
    
    private Boolean estaAtivo;

    private Turno turno;

    private Turma turma;


    public EditAlunoForm() {

    }

    public EditAlunoForm(String nome, String cpf, Boolean estaAtivo, Turno turno, Turma turma) {
        this.nome = nome;
        this.cpf = cpf;
        this.estaAtivo = estaAtivo;
        this.turno = turno;
        this.turma = turma;
    }

    public static Aluno update(Aluno aluno, EditAlunoForm alunoForm) {
        aluno.setNome(alunoForm.getNome());
        aluno.setEstaAtivo(alunoForm.getEstaAtivo());
        aluno.setTurno(alunoForm.getTurno());
        aluno.setTurma(alunoForm.getTurma());
        return aluno;
    }

    public EditAlunoForm loadFromAluno(Aluno aluno) {
        this.nome = aluno.getNome();
        this.cpf = aluno.getCpf();
        this.estaAtivo = aluno.getEstaAtivo();
        this.turno = aluno.getTurno();
        this.turma = aluno.getTurma();
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getEstaAtivo() {
        return estaAtivo;
    }

    public void setEstaAtivo(Boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
