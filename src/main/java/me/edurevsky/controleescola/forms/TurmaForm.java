package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.entities.Turma;
import org.springframework.beans.BeanUtils;

@Data
public class TurmaForm {

    private String turma;
    private String conteudo;
    private Professor professor;

    public static Turma convertToTurma(TurmaForm turmaForm) {
        Turma turma = new Turma();
        BeanUtils.copyProperties(turmaForm, turma);
        return turma;
    }

    public TurmaForm loadFromTurma(Turma turma) {
        this.turma = turma.getTurma();
        this.conteudo = turma.getConteudo();
        this.professor = turma.getProfessor();
        return this;
    }
}
