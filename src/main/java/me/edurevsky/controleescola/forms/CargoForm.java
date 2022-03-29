package me.edurevsky.controleescola.forms;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Cargo;

@Getter
@Setter
public class CargoForm {

    private String cargo;

    public CargoForm(String cargo) {
        this.cargo = cargo;
    }

    public static Cargo convertToCargo(CargoForm cargoForm) {
        return new Cargo().withCargo(cargoForm.getCargo());
    }

    public CargoForm loadFromCargo(Cargo cargo) {
        this.cargo = cargo.getCargo();
        return this;
    }

}
