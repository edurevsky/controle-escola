package me.edurevsky.controleescola.repositories;

import me.edurevsky.controleescola.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
