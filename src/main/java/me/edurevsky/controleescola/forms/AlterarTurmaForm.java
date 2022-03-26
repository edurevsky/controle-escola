package me.edurevsky.controleescola.forms;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Turma;

public class AlterarTurmaForm {

    // @NotNull
    private Turma turma;

    public AlterarTurmaForm(Turma turma) {
        this.turma = turma;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public void loadFromAluno(Aluno aluno) {
        this.turma = aluno.getTurma();
    }
    
}
