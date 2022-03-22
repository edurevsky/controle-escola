package me.edurevsky.controleescola.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
    @OneToMany(targetEntity = Funcionario.class, mappedBy = "cargo")
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

    // On Cargo delete
    public void setAllFuncionariosCargoNull() {
        funcionarios.forEach(funcionario -> funcionario.setCargo(null));
    }

}
