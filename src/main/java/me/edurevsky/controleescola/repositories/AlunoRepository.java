package me.edurevsky.controleescola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.edurevsky.controleescola.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}
