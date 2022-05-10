package me.edurevsky.controleescola.repositories;

import java.util.List;

import me.edurevsky.controleescola.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import me.edurevsky.controleescola.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByEstaAtivo(Boolean estaAtivo);
    Boolean existsByCpf(String cpf);
    Aluno findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE Aluno a " +
            "SET a.turma = :turma " +
            "WHERE a.id = :id")
    void updateTurma(@Param("id") Long id, @Param("turma") Turma turma);
}
