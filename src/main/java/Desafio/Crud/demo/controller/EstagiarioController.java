package Desafio.Crud.demo.controller;
import Desafio.Crud.demo.model.EstagiarioModel;
import Desafio.Crud.demo.service.EstagiarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estagiarios")
@RequiredArgsConstructor

public class EstagiarioController {

    private final EstagiarioService estagiarioService;

    @PostMapping
    public ResponseEntity<EstagiarioModel> criar(@RequestBody EstagiarioModel e) {
        return ResponseEntity.ok(estagiarioService.criar(e));
    }

    @GetMapping
    public ResponseEntity<List<EstagiarioModel>> listar() {
        return ResponseEntity.ok(estagiarioService.listar());
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<EstagiarioModel> buscar(@PathVariable String cpf) {
        return ResponseEntity.ok(estagiarioService.buscarPorCpf(cpf));
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<EstagiarioModel> atualizar(@PathVariable String cpf, @RequestBody EstagiarioModel e) {
        return ResponseEntity.ok(estagiarioService.atualizar(cpf, e));
    }

    @DeleteMapping("/deletar/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        estagiarioService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }
}