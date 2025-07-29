package Desafio.Crud.demo.controller;
import Desafio.Crud.demo.dto.CoordenadorDTO;
import Desafio.Crud.demo.model.CoordenadorModel;
import Desafio.Crud.demo.service.CoordenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/coordenadores")
@RequiredArgsConstructor
public class CoordenadorController {

    private final CoordenadorService coordenadorService;

    @PostMapping
    public ResponseEntity<CoordenadorDTO> criar(@RequestBody CoordenadorDTO dto) {
        CoordenadorDTO criado = coordenadorService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<CoordenadorDTO>> listar() {
        List<CoordenadorDTO> lista = coordenadorService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CoordenadorDTO> buscar(@PathVariable String cpf) {
        CoordenadorDTO dto = coordenadorService.buscar(cpf);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<CoordenadorDTO> atualizar(@PathVariable String cpf, @RequestBody CoordenadorDTO dto) {
        CoordenadorDTO atualizado = coordenadorService.atualizar(cpf, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        coordenadorService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{matricula}/estagiarios")
    public ResponseEntity<CoordenadorDTO> adicionarEstagiarios(
            @PathVariable String matricula,
            @RequestBody List<String> matriculasEstagiarios) {
        CoordenadorDTO atualizado = coordenadorService.adicionarEstagiarios(matricula, matriculasEstagiarios);
        return ResponseEntity.ok(atualizado);
    }
}

