package me.edurevsky.controleescola.repositories;

import me.edurevsky.controleescola.entities.AvisosTurma;
import me.edurevsky.controleescola.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisosTurmaRepository extends JpaRepository<AvisosTurma, Long> {

    List<AvisosTurma> findByTurma(Turma turma);

    // AvisosTurma findFirstByDataByTurma(Turma turma);
}
