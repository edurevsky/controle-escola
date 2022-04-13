package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.Cargo;

@Data
public class CargoForm {

    private String cargo;

    public CargoForm(String cargo) {
        this.cargo = cargo;
    }

    public static Cargo convertToCargo(CargoForm cargoForm) {
        Cargo cargo = new Cargo();
        cargo.setCargo(cargoForm.getCargo());
        return cargo;
    }

    public CargoForm loadFromCargo(Cargo cargo) {
        this.cargo = cargo.getCargo();
        return this;
    }
}
