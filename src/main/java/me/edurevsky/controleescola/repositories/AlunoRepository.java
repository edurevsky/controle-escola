package me.edurevsky.controleescola.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import me.edurevsky.controleescola.entities.Aluno;

@Repository
public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long> {

}
