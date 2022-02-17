package me.edurevsky.controleescola.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import me.edurevsky.controleescola.entities.Aluno;

@Repository
public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long> {

    public List<Aluno> findByEstaAtivo(Boolean estaAtivo);

}
