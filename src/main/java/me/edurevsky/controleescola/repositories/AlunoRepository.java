package me.edurevsky.controleescola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.edurevsky.controleescola.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}
