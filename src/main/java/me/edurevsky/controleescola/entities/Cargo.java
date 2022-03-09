package me.edurevsky.controleescola.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Table(name = "tb_cargos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    @JsonIgnore
    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios = new ArrayList<>();

    /**
     * Método Builder
     * @param cargo o nome do cargo
     * @return Objeto Cargo
     */
    public Cargo withCargo(String cargo) {
        this.cargo = cargo;
        return this;
    }

}
