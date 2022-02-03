package me.edurevsky.controleescola.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_cargos")
@Data
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

}
