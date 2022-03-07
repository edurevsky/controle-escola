package me.edurevsky.controleescola.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.entities.Turma;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDTO {

    private String turma;
    private Professor professor;
    private List<Aluno> alunos;

    public TurmaDTO (Turma turma) {
        this.turma = turma.getTurma();
        this.professor = turma.getProfessor();
        this.alunos = turma.getAlunos();
    }

}
