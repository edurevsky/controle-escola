package me.edurevsky.controleescola.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_turmas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String turma;

    private String conteudo;

    @JsonIgnore
    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    // On Turma delete
    public void setAllAlunosTurmaNull() {
        alunos.forEach(aluno -> aluno.setTurma(null));
    }
}
