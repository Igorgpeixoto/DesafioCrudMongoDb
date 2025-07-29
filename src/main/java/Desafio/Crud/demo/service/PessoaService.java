package Desafio.Crud.demo.service;

import Desafio.Crud.demo.dto.PessoaDTO;
import Desafio.Crud.demo.mapper.PessoaMapper;
import Desafio.Crud.demo.model.PessoaModel;
import Desafio.Crud.demo.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper mapper;  // Mapper para converter DTO <-> Model

    // Cria Pessoa
    public PessoaDTO criar(PessoaDTO dto) {
        PessoaModel model = mapper.toEntity(dto);


        if (pessoaRepository.findByCpf(model.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        PessoaModel salvo = pessoaRepository.save(model);
        return mapper.toDTO(salvo);
    }

    // Atualiza Pessoa
    public PessoaDTO atualizar(String cpf, PessoaDTO dtoAtualizado) {
        PessoaModel existente = buscarEntityPorCpf(cpf);

        existente.setNomeCompleto(dtoAtualizado.getNomeCompleto());
        existente.setDataNascimento(dtoAtualizado.getDataNascimento());

        PessoaModel salvo = pessoaRepository.save(existente);
        return mapper.toDTO(salvo);
    }

    // Deleta Pessoa
    public void deletar(String cpf) {
        PessoaModel aDeletar = buscarEntityPorCpf(cpf);
        pessoaRepository.delete(aDeletar);
    }

    // Busca Pessoa por CPF (retorna DTO)
    public PessoaDTO buscarPorCpf(String cpf) {
        PessoaModel model = buscarEntityPorCpf(cpf);
        return mapper.toDTO(model);
    }

    // Lista todas as pessoas (retorna lista DTO)
    public List<PessoaDTO> listar() {
        List<PessoaModel> modelos = pessoaRepository.findAll();
        return modelos.stream()
                .map(mapper::toDTO)
                .toList();
    }

    // Método privado para buscar a entidade PessoaModel por CPF
    private PessoaModel buscarEntityPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada."));
    }
}
