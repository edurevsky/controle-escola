package me.edurevsky.controleescola.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.entities.Turma;

import javax.validation.constraints.NotNull;
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

    public TurmaDTO(@NotNull Turma turma) {
        this.turma = turma.getTurma();
        this.conteudo = turma.getConteudo();
        this.professor = turma.getProfessor();
        this.alunos = turma.getAlunos();
    }

}
