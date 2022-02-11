package me.edurevsky.controleescola.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.dtos.AlunoDTO;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.services.contracts.aluno.BuscaAluno;
import me.edurevsky.controleescola.services.contracts.aluno.RegistroAluno;
import me.edurevsky.controleescola.services.contracts.aluno.TransferenciaDeTurma;
import me.edurevsky.controleescola.services.contracts.pessoafisica.AlterarCpf;

@Service
public class AlunoService implements RegistroAluno, BuscaAluno, TransferenciaDeTurma, AlterarCpf {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaService turmaService;

    @Override
    public Aluno registrarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = AlunoDTO.convertToAluno(alunoDTO);
        aluno.setTurma(turmaService.buscarPorId(alunoDTO.getTurma()));
        return alunoRepository.save(aluno);
    }

    @Override
    public void removerAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno com id " + id + " não encontrado.");
        }
        alunoRepository.deleteById(id);
    }

    @Override
    public Page<Aluno> buscarTodos(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }

    @Override
    public Aluno buscaPorId(Long id) {
        return alunoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Aluno com id " + id + " não encontrado."));
    }

    @Override
    public void transferirTurma(Long idAluno, Long idTurma) {
        if (!alunoRepository.existsById(idAluno)) {
            throw new EntityNotFoundException("Aluno com id " + idAluno + " não encontrado.");
        }
        if (turmaService.buscarPorId(idTurma) == null) {
            throw new EntityNotFoundException("Turma com id " + idTurma + " não encontrada.");
        }
        Aluno alunoEmTransferencia = alunoRepository.findById(idAluno).get();
        alunoEmTransferencia.setTurma(turmaService.buscarPorId(idTurma));
        alunoRepository.save(alunoEmTransferencia);
    }

    @Override
    public void alterarCpf(Long idAluno, String cpf) {
        if (!alunoRepository.existsById(idAluno)) {
            throw new EntityNotFoundException("Aluno com id " + idAluno + " não encontrado.");
        }
        Aluno aluno = alunoRepository.findById(idAluno).get();
        aluno.setCpf(cpf);
        alunoRepository.save(aluno);
    }

}
