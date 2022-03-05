package me.edurevsky.controleescola.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name = "tb_turmas")
@Data
public class Turma {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String turma;

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Aluno> alunos;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

}
