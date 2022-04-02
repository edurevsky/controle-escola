package me.edurevsky.controleescola.web.controllers;

import me.edurevsky.controleescola.forms.UsuarioForm;
import me.edurevsky.controleescola.repositories.RoleRepository;
import me.edurevsky.controleescola.repositories.UserRepository;
import me.edurevsky.controleescola.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuariosViewController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final static Integer PAGE_SIZE = 10;

    @Autowired
    public UsuariosViewController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    private BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping
    public ModelAndView index() {
        return this.paginatedUsuarios(1);
    }

    @GetMapping(value = "/{page}")
    public ModelAndView paginatedUsuarios(@PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("usuarios/index");
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        Page<AppUser> users = userRepository.findAll(pageable);

        // Title
        mv.addObject("title", "Lista de Usuários");

        // Pagination
        mv.addObject("currentPage", page);
        mv.addObject("totalPages", users.getTotalPages());
        mv.addObject("totalItems", users.getTotalElements());

        mv.addObject("usuariosList", users.getContent());
        return mv;
    }

    @GetMapping(value = "/registrar")
    public ModelAndView newUsuarioGet(UsuarioForm usuarioForm) {
        ModelAndView mv = new ModelAndView("usuarios/new");
        mv.addObject("title", "Registrar Usuário");
        mv.addObject("rolesList", roleRepository.findAll());
        return mv;
    }

    @PostMapping(value = "/registrar")
    public ModelAndView newUsuarioPost(@Valid @ModelAttribute UsuarioForm usuarioForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.newUsuarioGet(usuarioForm);
        }
        AppUser user = new AppUser();
        user.setUsername(usuarioForm.getUsername());
        user.setPassword(encoder().encode(usuarioForm.getPassword()));
        user.setRoles(usuarioForm.getRoles());
        userRepository.save(user);
        return new ModelAndView("redirect:/usuarios");
    }
}
