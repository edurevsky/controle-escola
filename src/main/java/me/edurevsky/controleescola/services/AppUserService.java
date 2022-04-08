package me.edurevsky.controleescola.services;

import me.edurevsky.controleescola.entities.AppUser;
import me.edurevsky.controleescola.entities.Role;
import me.edurevsky.controleescola.forms.UsuarioForm;
import me.edurevsky.controleescola.repositories.RoleRepository;
import me.edurevsky.controleescola.repositories.UserRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String NOT_FOUND_MESSAGE = "Usuário com id %d não encontrado";

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

    public AppUser findById(Long id) {
        Handlers.handleEntityNotFound(userRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        return userRepository.getById(id);
    }

    public void remove(Long id) {
        Handlers.handleEntityNotFound(userRepository, id, "");
        AppUser user = userRepository.getById(id);

        if (user.getRoles().contains(adminRole())) {
            throw new RuntimeException("Um admin não pode deletar um usuário que contenha uma autorização admin");
        }
        userRepository.deleteById(id);
    }

    public Page<AppUser> findPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return userRepository.findAll(pageable);
    }

    private Role adminRole() {
        return new Role(1, "ADMIN");
    }
}
