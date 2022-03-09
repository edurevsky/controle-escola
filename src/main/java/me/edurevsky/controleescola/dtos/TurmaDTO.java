package me.edurevsky.controleescola.dtos;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.entities.Turma;

import java.util.List;

@Getter
@Setter
public class TurmaDTO {

    private String turma;
    private String conteudo;
    private Professor professor;
    private List<Aluno> alunos;

    public TurmaDTO() {

    }

    public TurmaDTO(Turma turma) {
        this.turma = turma.getTurma();
        this.conteudo = turma.getConteudo();
        this.professor = turma.getProfessor();
        this.alunos = turma.getAlunos();
    }

}
