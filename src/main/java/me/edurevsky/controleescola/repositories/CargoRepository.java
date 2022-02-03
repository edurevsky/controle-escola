package me.edurevsky.controleescola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.edurevsky.controleescola.entities.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    
}
