package me.edurevsky.controleescola.forms;

import me.edurevsky.controleescola.entities.Turma;

import javax.validation.constraints.NotNull;

public class AlterarTurmaForm {

    @NotNull
    private Long turmaId;

    public AlterarTurmaForm(Long turmaId) {
        this.turmaId = turmaId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }
}
