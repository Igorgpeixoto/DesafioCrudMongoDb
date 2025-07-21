package Desafio.Crud.demo.service;

import Desafio.Crud.demo.model.PessoaModel;
import Desafio.Crud.demo.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    //Cria Pessoa
    public PessoaModel criar(PessoaModel pessoa) {
        if (pessoaRepository.findByCpf(pessoa.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        return pessoaRepository.save(pessoa);
    }

    //Atualiza Pessoa
    public PessoaModel atualizar(String cpf, PessoaModel nova) {
        PessoaModel existente = buscarPorCpf(cpf);
        existente.setNomeCompleto(nova.getNomeCompleto());
        existente.setDataNascimento(nova.getDataNascimento());
        return pessoaRepository.save(existente);

    }

    //Deleta Pessoa
    public void deletar(String cpf) {
        PessoaModel pessoa = buscarPorCpf(cpf);
        pessoaRepository.delete(pessoa);
    }

    //Busca Pessoa por CPF
    public PessoaModel buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada."));
    }

    //Lista todas as pessoas
    public List<PessoaModel> listar() {
        return pessoaRepository.findAll();
    }
}