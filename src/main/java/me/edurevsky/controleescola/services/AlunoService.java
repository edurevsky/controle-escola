package me.edurevsky.controleescola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.dtos.AlunoDTO;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.services.contracts.aluno.BuscaAluno;
import me.edurevsky.controleescola.services.contracts.aluno.RegistroAluno;
import me.edurevsky.controleescola.services.contracts.aluno.TransferenciaDeTurma;

@Service
public class AlunoService implements RegistroAluno, BuscaAluno, TransferenciaDeTurma {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaService turmaService;

    @Override
    public Page<Aluno> buscarTodos(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }

    @Override
    public Aluno registrarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = AlunoDTO.convertToAluno(alunoDTO);
        aluno.setTurma(turmaService.buscarPorId(alunoDTO.getTurma()));
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno buscaPorId(Long id) {
        return alunoRepository.findById(id)
            .orElseThrow( /**TODO: Tratamento de erro */ );
    }

    @Override
    public Boolean removerAluno(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean transferirTurma(Long idAluno, Long idTurma) {
        if (!alunoRepository.existsById(idAluno)) {
            return false;
        }
        if (turmaService.buscarPorId(idTurma) == null) {
            return false;
        }
        Aluno alunoEmTransferencia = alunoRepository.findById(idAluno).get();
        alunoEmTransferencia.setTurma( turmaService.buscarPorId(idTurma) );
        alunoRepository.save(alunoEmTransferencia);
        return true;
    }

}
