package me.edurevsky.controleescola.forms;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Cargo;

@Getter
@Setter
@RequiredArgsConstructor
public class CargoForm {

    private String cargo;

    public static Cargo convertToCargo(CargoForm cargoForm) {
        Cargo cargo = new Cargo();
        cargo.setCargo(cargo.getCargo());
        return cargo;
    }

}
