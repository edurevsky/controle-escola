package me.edurevsky.controleescola.repositories;

import me.edurevsky.controleescola.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Boolean existsByCpf(String cpf);

    Professor findByEmail(String email);

}
