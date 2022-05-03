package me.edurevsky.controleescola.web.controllers;

import lombok.RequiredArgsConstructor;
import me.edurevsky.controleescola.forms.UsuarioForm;
import me.edurevsky.controleescola.repositories.RoleRepository;
import me.edurevsky.controleescola.entities.AppUser;
import me.edurevsky.controleescola.services.AppUserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final AppUserService appUserService;
    private final RoleRepository roleRepository;
    private static final Integer PAGE_SIZE = 10;


    @GetMapping
    public ModelAndView index() {
        return paginatedUsuarios(1);
    }

    @GetMapping(value = "/{page}")
    public ModelAndView paginatedUsuarios(@PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("usuarios/index");
        Page<AppUser> users = appUserService.findPaginated(page, PAGE_SIZE);
        List<AppUser> usersList = users.getContent();

        mv.addObject("title", "Lista de Usuários");

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
        return redirect();
    }

    @GetMapping(value = "/{id}/deletar")
    public ModelAndView deleteUsuario(@PathVariable("id") Long id) {
        appUserService.remove(id);
        return redirect();
    }

    private ModelAndView redirect() {
        return new ModelAndView("redirect:/usuarios");
    }
}
