package me.edurevsky.controleescola.forms;

import me.edurevsky.controleescola.entities.Cargo;

public class EditCargoForm {

	private Cargo cargo;

	public EditCargoForm(Cargo cargo) {
		this.cargo = cargo;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public EditCargoForm loadFromCargo(Cargo cargo) {
		this.cargo = cargo;
		return this;
	}
}
