package me.edurevsky.controleescola.forms;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.entities.Turma;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class TurmaForm {

    private String turma;
    private String conteudo;
    private Professor professor;

    public static Turma convertToTurma(TurmaForm turmaForm) {
        Turma turma = new Turma();
        BeanUtils.copyProperties(turmaForm, turma);
        return turma;
    }

}
