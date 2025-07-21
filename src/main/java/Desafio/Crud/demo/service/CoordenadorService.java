package Desafio.Crud.demo.service;

import Desafio.Crud.demo.model.CoordenadorModel;
import Desafio.Crud.demo.model.EstagiarioModel;
import Desafio.Crud.demo.repository.CoordenadorRepository;
import Desafio.Crud.demo.repository.EstagiarioRepository;
import Desafio.Crud.demo.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoordenadorService {
    private final CoordenadorRepository repository;
    private final PessoaRepository pessoaRepository;
    private final EstagiarioRepository estagiarioRepository;

    public CoordenadorModel criar(CoordenadorModel c) {
        if (repository.findByMatricula(c.getMatricula()).isPresent()) {
            throw new IllegalArgumentException("Matrícula já existe.");
        }
        if (pessoaRepository.findByCpf(c.getCpf()).isEmpty()) {
            throw new IllegalArgumentException("Pessoa não cadastrada.");
        }
        return repository.save(c);
    }

    public CoordenadorModel atualizar(String cpf, CoordenadorModel coordenadorAtualizado) {
        CoordenadorModel existente = buscar(cpf);
        existente.setNomeCompleto(coordenadorAtualizado.getNomeCompleto());
        existente.setDataNascimento(coordenadorAtualizado.getDataNascimento());
        existente.setSetor(coordenadorAtualizado.getSetor());
        return repository.save(existente);
    }

    public CoordenadorModel buscar(String cpf) {
        Optional<CoordenadorModel> coordenadorModel = repository.findByCpf(cpf);
        return coordenadorModel.orElse(null);
    }

    public List<CoordenadorModel> listar() {
        return repository.findAll();
    }

    public void deletar(String cpf) {
        repository.findByCpf(cpf).ifPresent(repository::delete);
    }

    public CoordenadorModel adicionarEstagiarios(String matriculaCoord, List<String> matriculasEstagiarios) {
        CoordenadorModel coordenador = repository.findByMatricula(matriculaCoord)
                .orElseThrow(() -> new NoSuchElementException("Coordenador não encontrado."));

        List<EstagiarioModel> estagiarios = matriculasEstagiarios.stream()
                .map(mat -> estagiarioRepository.findByMatricula(mat)
                        .orElseThrow(() -> new RuntimeException("Estagiário não encontrado: " + mat)))
                .toList();

        coordenador.setEstagiarios(estagiarios);
        return repository.save(coordenador);
    }
}