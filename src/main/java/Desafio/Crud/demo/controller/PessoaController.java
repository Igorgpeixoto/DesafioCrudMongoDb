package Desafio.Crud.demo.controller;
import Desafio.Crud.demo.dto.PessoaDTO;
import Desafio.Crud.demo.model.PessoaModel;
import Desafio.Crud.demo.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDTO> criar(@RequestBody PessoaDTO dto) {
        PessoaDTO criado = pessoaService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listar() {
        List<PessoaDTO> lista = pessoaService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<PessoaDTO> buscar(@PathVariable String cpf) {
        PessoaDTO dto = pessoaService.buscarPorCpf(cpf);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<PessoaDTO> atualizar(@PathVariable String cpf, @RequestBody PessoaDTO dto) {
        PessoaDTO atualizado = pessoaService.atualizar(cpf, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/deletar/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        pessoaService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }
}
