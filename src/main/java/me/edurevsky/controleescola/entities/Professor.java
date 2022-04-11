package me.edurevsky.controleescola.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_professores")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Professor extends PessoaFisica {

    private BigDecimal salario;

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
