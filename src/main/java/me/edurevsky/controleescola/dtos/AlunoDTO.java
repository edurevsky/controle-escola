package me.edurevsky.controleescola.dtos;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.entities.enums.Turno;

@Getter
@Setter
public class AlunoDTO {

    private String nome;
    private String cpf;
    private String email;
    private Boolean estaAtivo;
    private Turno turno;
    private Turma turma;

    public AlunoDTO() {

    }

    public AlunoDTO(Aluno aluno) {
        this.nome = aluno.getNome();
        this.cpf = aluno.getCpf();
        this.email = aluno.getEmail();
        this.estaAtivo = aluno.getEstaAtivo();
        this.turno = aluno.getTurno();
        this.turma = aluno.getTurma();
    }

}
