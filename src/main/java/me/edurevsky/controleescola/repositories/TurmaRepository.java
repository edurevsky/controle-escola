package me.edurevsky.controleescola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.edurevsky.controleescola.entities.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    
}
