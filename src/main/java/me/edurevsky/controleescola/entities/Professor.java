package me.edurevsky.controleescola.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Table(name = "tb_professores")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Professor extends PessoaFisica {

    private BigDecimal salario;

    private String email;

    @OneToMany(mappedBy = "professor")
    private List<Turma> turmas = new ArrayList<>();

    // On Professor delete
    public void setAllTurmasProfessorNull() {
        turmas.forEach(turma -> turma.setProfessor(null));
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getCpf() {
        return super.getCpf();
    }
}
