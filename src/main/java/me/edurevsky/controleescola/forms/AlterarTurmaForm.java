package me.edurevsky.controleescola.forms;

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
