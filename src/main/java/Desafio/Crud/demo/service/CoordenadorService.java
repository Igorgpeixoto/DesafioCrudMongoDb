package Desafio.Crud.demo.service;

import Desafio.Crud.demo.dto.CoordenadorDTO;
import Desafio.Crud.demo.mapper.CoordenadorMapper;
import Desafio.Crud.demo.model.CoordenadorModel;
import Desafio.Crud.demo.model.EstagiarioModel;
import Desafio.Crud.demo.repository.CoordenadorRepository;
import Desafio.Crud.demo.repository.EstagiarioRepository;
import Desafio.Crud.demo.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class CoordenadorService {

    private final CoordenadorRepository repository;
    private final PessoaRepository pessoaRepository;
    private final EstagiarioRepository estagiarioRepository;
    private final CoordenadorMapper mapper;  // Mapper para converter entre DTO e Model

    public CoordenadorDTO criar(CoordenadorDTO dto) {
        CoordenadorModel model = mapper.toEntity(dto);

        if (repository.findByMatricula(model.getMatricula()).isPresent()) {
            throw new IllegalArgumentException("Matrícula já existe.");
        }
        if (pessoaRepository.findByCpf(model.getCpf()).isEmpty()) {
            throw new IllegalArgumentException("Pessoa não cadastrada.");
        }

        CoordenadorModel salvo = repository.save(model);
        return mapper.toDTO(salvo);
    }

    public CoordenadorDTO atualizar(String cpf, CoordenadorDTO dtoAtualizado) {
        CoordenadorModel existente = buscarEntity(cpf);

        existente.setNomeCompleto(dtoAtualizado.getNomeCompleto());
        existente.setDataNascimento(dtoAtualizado.getDataNascimento());
        existente.setSetor(dtoAtualizado.getSetor());

        CoordenadorModel salvo = repository.save(existente);
        return mapper.toDTO(salvo);
    }

    public CoordenadorDTO buscar(String cpf) {
        CoordenadorModel model = buscarEntity(cpf);
        if (model == null) {
            return null;
        }
        return mapper.toDTO(model);
    }

    public List<CoordenadorDTO> listar() {
        List<CoordenadorModel> modelos = repository.findAll();
        return modelos.stream()
                .map(mapper::toDTO)
                .toList();
    }

    public void deletar(String cpf) {
        repository.findByCpf(cpf).ifPresent(repository::delete);
    }

    public CoordenadorDTO adicionarEstagiarios(String matriculaCoord, List<String> matriculasEstagiarios) {
        CoordenadorModel coordenador = repository.findByMatricula(matriculaCoord)
                .orElseThrow(() -> new NoSuchElementException("Coordenador não encontrado."));

        List<EstagiarioModel> estagiarios = matriculasEstagiarios.stream()
                .map(mat -> estagiarioRepository.findByMatricula(mat)
                        .orElseThrow(() -> new RuntimeException("Estagiário não encontrado: " + mat)))
                .toList();

        coordenador.setEstagiarios(estagiarios);

        CoordenadorModel salvo = repository.save(coordenador);
        return mapper.toDTO(salvo);
    }

    // Método privado para buscar entidade (Model) sem expor para fora
    private CoordenadorModel buscarEntity(String cpf) {
        return repository.findByCpf(cpf).orElse(null);
    }
}
