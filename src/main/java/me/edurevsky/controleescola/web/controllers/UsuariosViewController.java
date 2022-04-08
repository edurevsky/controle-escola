package me.edurevsky.controleescola.web.controllers;

import me.edurevsky.controleescola.forms.UsuarioForm;
import me.edurevsky.controleescola.repositories.RoleRepository;
import me.edurevsky.controleescola.repositories.UserRepository;
import me.edurevsky.controleescola.entities.AppUser;
import me.edurevsky.controleescola.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuariosViewController {

    private final AppUserService appUserService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final static Integer PAGE_SIZE = 10;

    @Autowired
    public UsuariosViewController(AppUserService appUserService, UserRepository userRepository, RoleRepository roleRepository) {
        this.appUserService = appUserService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public ModelAndView index() {
        return this.paginatedUsuarios(1);
    }

    @GetMapping(value = "/{page}")
    public ModelAndView paginatedUsuarios(@PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("usuarios/index");
        Page<AppUser> users = appUserService.findPaginated(page, PAGE_SIZE);
        List<AppUser> usersList = users.getContent();

        // Title
        mv.addObject("title", "Lista de Usuários");

        // Pagination
        mv.addObject("currentPage", page);
        mv.addObject("totalPages", users.getTotalPages());
        mv.addObject("totalItems", users.getTotalElements());

        mv.addObject("usuariosList", usersList);
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
        appUserService.save(usuarioForm);
        return new ModelAndView("redirect:/usuarios");
    }

    @GetMapping(value = "/{id}/deletar")
    public ModelAndView deleteUsuario(@PathVariable("id") Long id) {
        appUserService.remove(id);
        return new ModelAndView("redirect:/usuarios");
    }
}
