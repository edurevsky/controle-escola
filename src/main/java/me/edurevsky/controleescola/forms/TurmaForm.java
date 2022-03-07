package me.edurevsky.controleescola.forms;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Turma;

@Getter
@Setter
public class TurmaForm {

    private String turma;

    private String conteudo;

    public static Turma convertToTurma(TurmaForm turmaForm) {
        Turma turma = new Turma();
        turma.setTurma(turmaForm.getTurma());
        turma.setConteudo(turmaForm.getConteudo());
        return turma;
    }

}
