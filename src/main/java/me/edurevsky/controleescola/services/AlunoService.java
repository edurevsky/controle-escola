package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.dtos.AlunoDTO;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.services.contracts.pessoafisica.AlterarCpf;
import me.edurevsky.controleescola.services.utils.Handlers;

@Service
public class AlunoService implements AlterarCpf {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaService turmaService;
    public Aluno registrarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = AlunoDTO.convertToAluno(alunoDTO);
        aluno.setTurma(turmaService.buscarPorId(alunoDTO.getTurma()));
        return alunoRepository.save(aluno);
    }
    public void removerAluno(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, "Aluno com id " + id + " não encontrado");

        alunoRepository.deleteById(id);
    }

    public Page<Aluno> buscarTodos(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }

    public Aluno buscaPorId(Long id) {
        return alunoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Aluno com id " + id + " não encontrado."));
    }

    public void transferirTurma(Long idAluno, Long idTurma) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, "Aluno com id " + idAluno + " não encontrado.");
        this.handleTurmaNotFound(idTurma);

        Aluno alunoEmTransferencia = alunoRepository.findById(idAluno).get();
        alunoEmTransferencia.setTurma(turmaService.buscarPorId(idTurma));
        alunoRepository.save(alunoEmTransferencia);
    }

    @Override
    public void alterarCpf(Long idAluno, String cpf) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, "Aluno com id " + idAluno + " não encontrado.");

        Aluno aluno = alunoRepository.findById(idAluno).get();
        aluno.setCpf(cpf);
        alunoRepository.save(aluno);
    }

    public void alterarEstaAtivo(Long idAluno) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, "Aluno com id " + idAluno + " não encontrado.");

        Aluno aluno = alunoRepository.findById(idAluno).get();
        if (aluno.getEstaAtivo()) {
            aluno.setEstaAtivo(false);
        } else {
            aluno.setEstaAtivo(true);
        }
        alunoRepository.save(aluno);
    }

    private void handleTurmaNotFound(Long idTurma) {
        if (turmaService.buscarPorId(idTurma) == null) {
            throw new EntityNotFoundException("Turma com id " + idTurma + " não encontrada.");
        }
    }

    public List<Aluno> buscarPorAtividade(Boolean estaAtivo) {
        return alunoRepository.findByEstaAtivo(estaAtivo);
    }

}
