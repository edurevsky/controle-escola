package me.edurevsky.controleescola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.edurevsky.controleescola.entities.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    
}
