package me.edurevsky.controleescola.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "tb_cargos")
@Getter
@Setter
@AllArgsConstructor
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    @JsonIgnore
    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Cargo() {

    }

    // On Cargo delete
    public void setAllFuncionariosCargoNull() {
        funcionarios.forEach(funcionario -> funcionario.setCargo(null));
    }

}
