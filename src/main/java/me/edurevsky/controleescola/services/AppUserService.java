package me.edurevsky.controleescola.services;

import me.edurevsky.controleescola.entities.AppUser;
import me.edurevsky.controleescola.entities.Role;
import me.edurevsky.controleescola.forms.UsuarioForm;
import me.edurevsky.controleescola.repositories.RoleRepository;
import me.edurevsky.controleescola.repositories.UserRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser save(UsuarioForm usuarioForm) {
        String encodedPassword = passwordEncoder.encode(usuarioForm.getPassword());
        usuarioForm.setPassword(encodedPassword);
        return userRepository.save(UsuarioForm.convertToAppUser(usuarioForm));
    }

    public void remove(Long id) {
        Handlers.handleEntityNotFound(userRepository, id, "");
        AppUser user = userRepository.getById(id);

        if (user.getRoles().contains(adminRole())) {
            throw new RuntimeException("Um admin não pode deletar um usuário que contenha uma autorização admin");
        }
        userRepository.deleteById(id);
    }

    private Role adminRole() {
        return new Role(1, "ADMIN");
    }
}
