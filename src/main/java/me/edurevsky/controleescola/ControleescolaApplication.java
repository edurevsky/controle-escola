package me.edurevsky.controleescola;

import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleescolaApplication implements CommandLineRunner {

	@Autowired
	TurmaService turmaService;

	public static void main(String[] args) {
		SpringApplication.run(ControleescolaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		TurmaForm t = new TurmaForm();
		for (int i = 1; i < 22; i++) {
			t.setTurma(String.format("Turma %d", i));
			t.setConteudo("Conteúdo de Exemplo");
			t.setProfessor(null);
			turmaService.save(t);
		}

	}

}
