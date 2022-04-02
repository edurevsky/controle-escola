package me.edurevsky.controleescola.repositories;

import me.edurevsky.controleescola.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
