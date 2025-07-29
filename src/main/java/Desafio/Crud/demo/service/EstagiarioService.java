package Desafio.Crud.demo.service;
import Desafio.Crud.demo.dto.EstagiarioDTO;
import Desafio.Crud.demo.mapper.EstagiarioMapper;
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
    private final EstagiarioMapper mapper;  // Mapper para converter DTO <-> Model

    public EstagiarioDTO criar(EstagiarioDTO dto) {
        EstagiarioModel model = mapper.toEntity(dto);

        pessoaRepository.findByCpf(model.getCpf())
                .orElseThrow(() -> new IllegalArgumentException("Pessoa com CPF não encontrada."));

        if (estagiarioRepository.findByMatricula(model.getMatricula()).isPresent()) {
            throw new IllegalArgumentException("Matrícula já em uso.");
        }

        EstagiarioModel salvo = estagiarioRepository.save(model);
        return mapper.toDTO(salvo);
    }

    public EstagiarioDTO atualizar(String cpf, EstagiarioDTO dtoAtualizado) {
        EstagiarioModel existente = buscarEntityPorCpf(cpf);

        existente.setNomeCompleto(dtoAtualizado.getNomeCompleto());
        existente.setDataNascimento(dtoAtualizado.getDataNascimento());
        existente.setDataEntrada(dtoAtualizado.getDataEntrada());
        existente.setSetor(dtoAtualizado.getSetor());

        EstagiarioModel salvo = estagiarioRepository.save(existente);
        return mapper.toDTO(salvo);
    }

    public void deletar(String cpf) {
        EstagiarioModel aDeletar = buscarEntityPorCpf(cpf);
        estagiarioRepository.delete(aDeletar);
    }

    public EstagiarioDTO buscarPorCpf(String cpf) {
        EstagiarioModel model = buscarEntityPorCpf(cpf);
        return mapper.toDTO(model);
    }

    public List<EstagiarioDTO> listar() {
        List<EstagiarioModel> modelos = estagiarioRepository.findAll();
        return modelos.stream()
                .map(mapper::toDTO)
                .toList();
    }

    private EstagiarioModel buscarEntityPorCpf(String cpf) {
        return estagiarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Estagiário não encontrado."));
    }
}
