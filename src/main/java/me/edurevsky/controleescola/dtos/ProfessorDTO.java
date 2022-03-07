package me.edurevsky.controleescola.dtos;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.entities.Turma;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Setter
public class ProfessorDTO {

    private String nome;

    private String cpf;

    public List<Turma> turmas;

    public ProfessorDTO() {

    }

    public ProfessorDTO(@NotNull Professor professor) {
        this.nome = professor.getNome();
        this.cpf = professor.getCpf();
        this.turmas = professor.getTurmas();
    }

}
