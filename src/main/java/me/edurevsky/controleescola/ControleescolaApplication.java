package me.edurevsky.controleescola;

import me.edurevsky.controleescola.repositories.UserRepository;
import me.edurevsky.controleescola.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ControleescolaApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Bean
	BCryptPasswordEncoder fodase() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ControleescolaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AppUser user1 = new AppUser();
		user1.setPassword(fodase().encode("1234"));
		user1.setUsername("admin");
		user1.setCompleteName("admin");
		userRepository.save(user1);
	}

}
