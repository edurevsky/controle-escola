package me.edurevsky.controleescola.forms;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Cargo;

@Getter
@Setter
public class CargoForm {

    private String cargo;

    public static Cargo convertToCargo(CargoForm cargoForm) {
        return new Cargo().withCargo(cargoForm.getCargo());
    }

}
