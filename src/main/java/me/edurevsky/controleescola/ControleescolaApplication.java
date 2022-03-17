package me.edurevsky.controleescola;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.forms.CargoForm;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleescolaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ControleescolaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
