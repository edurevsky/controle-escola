package me.edurevsky.controleescola.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import me.edurevsky.controleescola.exceptions.appexceptions.NotImplementedException;
import me.edurevsky.controleescola.forms.EditFuncionarioForm;
import me.edurevsky.controleescola.services.utils.CpfHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.forms.FuncionarioForm;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.repositories.FuncionarioRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final CpfHandler cpfHandler;
    private static final String NOT_FOUND_MESSAGE = "Funcionario com id %d não encontrado.";

    @Transactional
    public Funcionario save(FuncionarioForm funcionarioForm) {
        cpfHandler.ifAlreadyRegistered_ThrowException(funcionarioForm.getCpf());
        return funcionarioRepository.save(FuncionarioForm.convertToFuncionario(funcionarioForm));
    }

    @Transactional
    public Funcionario update(Long id, FuncionarioForm funcionarioForm) {
        Handlers.handleEntityNotFound(funcionarioRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        Funcionario funcionario = funcionarioRepository.getById(id);

//        if (!funcionarioForm.getCpf().equals(funcionario.getCpf())) {
//            cpfHandler.ifAlreadyRegistered_ThrowException(funcionarioForm.getCpf());
//        }
        BeanUtils.copyProperties(funcionarioForm, funcionario);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Long id, EditFuncionarioForm funcionarioForm) {
        Handlers.handleEntityNotFound(funcionarioRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Funcionario funcionario = funcionarioRepository.getById(id);
        return funcionarioRepository.save(EditFuncionarioForm.update(funcionario, funcionarioForm));
    }

    @Transactional
    public void remove(Long id) {
        Handlers.handleEntityNotFound(funcionarioRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        
        funcionarioRepository.deleteById(id);
    }

    @Transactional
    public void updateSalary(Long id, BigDecimal salario) {
        throw new NotImplementedException();
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário com id " + id + " não encontrado."));
    }

    @Transactional
    public void updateCpf(Long id, String cpf) {
        throw new NotImplementedException();
    }

    @Transactional
    public Funcionario updateCargo(Long idFuncionario, Cargo cargo) {
        Handlers.handleEntityNotFound(funcionarioRepository, idFuncionario, String.format(NOT_FOUND_MESSAGE, idFuncionario));
        
        Funcionario funcionario = funcionarioRepository.getById(idFuncionario);
        
        funcionario.setCargo(cargo);
        
        return funcionario;
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Page<Funcionario> findPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return funcionarioRepository.findAll(pageable);
    }
}
