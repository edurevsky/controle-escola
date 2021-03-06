package me.edurevsky.controleescola.web.controllers;

import lombok.RequiredArgsConstructor;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.forms.EditCargoForm;
import me.edurevsky.controleescola.forms.EditFuncionarioForm;
import me.edurevsky.controleescola.forms.FuncionarioForm;
import me.edurevsky.controleescola.services.CargoService;
import me.edurevsky.controleescola.services.FuncionarioService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

	private final FuncionarioService funcionarioService;
	private final CargoService cargoService;
	private static final int PAGE_SIZE = 10;

	@GetMapping
	public ModelAndView index() {
		return this.paginatedFuncionarios(1);
	}

	@GetMapping(value = "/{page}")
	public ModelAndView paginatedFuncionarios(@PathVariable("page") Integer page) {
		ModelAndView mv = new ModelAndView("funcionarios/index");
		Page<Funcionario> funcionariosPage = funcionarioService.findPaginated(page, PAGE_SIZE);
		List<Funcionario> funcionariosList = funcionariosPage.getContent();

		mv.addObject("title", "Lista de Funcionários");

		mv.addObject("currentPage", page);
		mv.addObject("totalPages", funcionariosPage.getTotalPages());
		mv.addObject("totalItems", funcionariosPage.getTotalElements());

		mv.addObject("funcionariosList", funcionariosList);
		return mv;
	}

	@GetMapping(value = "/registrar")
	public ModelAndView newFuncionarioGet(FuncionarioForm funcionarioForm) {
		ModelAndView mv = new ModelAndView("funcionarios/new");
		mv.addObject("title", "Registrar Funcionário");
		mv.addObject("cargosList", cargoService.findAll());
		mv.addObject("turnos", Turno.values());
		return mv;
	}

	@PostMapping(value = "/registrar")
	public ModelAndView newFuncionarioPost(@Valid @ModelAttribute FuncionarioForm funcionarioForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return this.newFuncionarioGet(funcionarioForm);
		}
		funcionarioService.save(funcionarioForm);
		return redirect();
	}

	@GetMapping(value = "/{id}/deletar")
	public ModelAndView deleteFuncionario(@PathVariable("id") Long id) {
		funcionarioService.remove(id);
		return redirect();
	}

	@GetMapping(value = "/{id}/editar")
	public ModelAndView editFuncionarioGet(@PathVariable("id") Long id, EditFuncionarioForm funcionarioForm) {
		Funcionario funcionario = funcionarioService.findById(id);
		if (Objects.isNull(funcionario)) {
			return redirect();
		}
		funcionarioForm.loadFromFuncionario(funcionario);
		ModelAndView mv = new ModelAndView("funcionarios/edit");
		mv.addObject("title", "Editar funcionário");
		mv.addObject("id", id);
		mv.addObject("cpf", funcionario.getCpf());
		mv.addObject("turnos", Turno.values());
		mv.addObject("cargosList", cargoService.findAll());
		return mv;
	}

	@PostMapping(value = "/{id}/editar")
	public ModelAndView editFuncionarioPost(@PathVariable("id") Long id, @Valid @ModelAttribute EditFuncionarioForm funcionarioForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return this.editFuncionarioGet(id, funcionarioForm);
		}
		funcionarioService.update(id, funcionarioForm);
		return redirect();
	}

	@GetMapping(value = "/{id}/detalhes")
	public ModelAndView detailsFuncionario(@PathVariable Long id) {
		Funcionario funcionario = funcionarioService.findById(id);
		if (Objects.isNull(funcionario)) {
			return redirect();
		}

		ModelAndView mv = new ModelAndView("funcionarios/details");
		mv.addObject("title", String.format("Detalhes de %s", funcionario.getNome()));
		mv.addObject("funcionario", funcionario);
		return mv;
	}

	@GetMapping(value = "/{id}/editar-cargo")
	public ModelAndView editCargoGet(@PathVariable("id") Long id, EditCargoForm editCargoForm) {
		Funcionario funcionario = funcionarioService.findById(id);
		if (Objects.isNull(funcionario)) {
			return redirect();
		}
		
		editCargoForm.loadFromCargo(funcionario.getCargo());
		ModelAndView mv = new ModelAndView("funcionarios/editcargo");
		mv.addObject("cargosList", cargoService.findAll());
		mv.addObject("title", "Editar Cargo");
		return mv;
	}

	@PostMapping(value = "/{id}/editar-cargo")
	public ModelAndView editCargoPost(@PathVariable("id") Long id, @Valid @ModelAttribute EditCargoForm editCargoForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return editCargoGet(id, editCargoForm);
		}
		funcionarioService.updateCargo(id, editCargoForm.getCargo());
		return new ModelAndView("redirect:/funcionarios/{id}/detalhes");
	}

	private ModelAndView redirect() {
		return new ModelAndView("redirect:/funcionarios");
	}

}
