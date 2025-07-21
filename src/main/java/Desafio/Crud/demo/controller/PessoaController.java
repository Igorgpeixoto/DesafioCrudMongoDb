package Desafio.Crud.demo.controller;
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
    public ResponseEntity<PessoaModel> criar(@RequestBody PessoaModel pessoa) {
        return ResponseEntity.ok(pessoaService.criar(pessoa));
    }

    @GetMapping
    public ResponseEntity<List<PessoaModel>> listar() {
        return ResponseEntity.ok(pessoaService.listar());
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<PessoaModel> buscar(@PathVariable String cpf) {
        return ResponseEntity.ok(pessoaService.buscarPorCpf(cpf));
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<PessoaModel> atualizar(@PathVariable String cpf, @RequestBody PessoaModel p) {
        return ResponseEntity.ok(pessoaService.atualizar(cpf, p));
    }

    @DeleteMapping("/deletar/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        pessoaService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }
}
