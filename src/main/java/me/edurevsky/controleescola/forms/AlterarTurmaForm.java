package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Turma;

@Data
public class AlterarTurmaForm {

    private Turma turma;

    public void loadFromAluno(Aluno aluno) {
        this.turma = aluno.getTurma();
    }
}
