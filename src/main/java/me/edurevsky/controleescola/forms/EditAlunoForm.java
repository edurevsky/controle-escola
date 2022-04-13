package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.entities.enums.Turno;

import javax.validation.constraints.NotEmpty;

@Data
public class EditAlunoForm {

    @NotEmpty(message = "O nome não pode estar em branco")
    private String nome;

    // CPF não é editável
    private String cpf;
    
    private Boolean estaAtivo;

    private Turno turno;

    private Turma turma;

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
}
