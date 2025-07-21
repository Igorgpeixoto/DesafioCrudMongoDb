package Desafio.Crud.demo.service;
import Desafio.Crud.demo.model.EstagiarioModel;
import Desafio.Crud.demo.repository.EstagiarioRepository;
import Desafio.Crud.demo.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class EstagiarioService {

    private final EstagiarioRepository estagiarioRepository;
    private final PessoaRepository pessoaRepository;

    public EstagiarioModel criar(EstagiarioModel estagiario) {
        pessoaRepository.findByCpf(estagiario.getCpf())
                .orElseThrow(() -> new IllegalArgumentException("Pessoa com CPF não encontrada."));

        if (estagiarioRepository.findByMatricula(estagiario.getMatricula()).isPresent()) {
            throw new IllegalArgumentException("Matrícula já em uso.");
        }
        return estagiarioRepository.save(estagiario);
    }

    public EstagiarioModel atualizar(String cpf, EstagiarioModel novo) {
        EstagiarioModel existente = buscarPorCpf(cpf);
        existente.setNomeCompleto(novo.getNomeCompleto());
        existente.setDataNascimento(novo.getDataNascimento());
        existente.setDataEntrada(novo.getDataEntrada());
        existente.setSetor(novo.getSetor());
        return estagiarioRepository.save(existente);
    }

    public void deletar(String cpf) {
        EstagiarioModel delete = buscarPorCpf(cpf);
        estagiarioRepository.delete(delete);
    }

    public EstagiarioModel buscarPorCpf(String cpf) {
        return estagiarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Estagiário não encontrado."));
    }

    public List<EstagiarioModel> listar() {
        return estagiarioRepository.findAll();
    }
}