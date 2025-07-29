package Desafio.Crud.demo.controller;
import Desafio.Crud.demo.dto.EstagiarioDTO;
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
    public ResponseEntity<EstagiarioDTO> criar(@RequestBody EstagiarioDTO dto) {
        EstagiarioDTO criado = estagiarioService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<EstagiarioDTO>> listar() {
        List<EstagiarioDTO> lista = estagiarioService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<EstagiarioDTO> buscar(@PathVariable String cpf) {
        EstagiarioDTO dto = estagiarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<EstagiarioDTO> atualizar(@PathVariable String cpf, @RequestBody EstagiarioDTO dto) {
        EstagiarioDTO atualizado = estagiarioService.atualizar(cpf, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/deletar/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        estagiarioService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }
}
