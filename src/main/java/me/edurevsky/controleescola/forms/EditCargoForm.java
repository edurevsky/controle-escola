package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.Cargo;

@Data
public class EditCargoForm {

	private Cargo cargo;

	public EditCargoForm loadFromCargo(Cargo cargo) {
		this.cargo = cargo;
		return this;
	}
}
