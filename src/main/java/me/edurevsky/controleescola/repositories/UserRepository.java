package me.edurevsky.controleescola.repositories;

import me.edurevsky.controleescola.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    Boolean existsByUsername(String username);
}
