package Desafio.Crud.demo.controller;
import Desafio.Crud.demo.model.CoordenadorModel;
import Desafio.Crud.demo.service.CoordenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/coordenadores")
@RequiredArgsConstructor
public class CoordenadorController {

    private final CoordenadorService coordenadorService;

    @PostMapping
    public ResponseEntity<CoordenadorModel> criar(@RequestBody CoordenadorModel c) {
        return ResponseEntity.ok(coordenadorService.criar(c));
    }

    @GetMapping
    public ResponseEntity<List<CoordenadorModel>> listar() {
        return ResponseEntity.ok(coordenadorService.listar());
    }

    @GetMapping("/{cpf}")
        public CoordenadorModel buscar(@PathVariable String cpf) {
        return coordenadorService.buscar(cpf);
        }

        @PutMapping("/{cpf}")
    public ResponseEntity<CoordenadorModel> atualizar(@PathVariable String cpf, @RequestBody CoordenadorModel c) {
        return ResponseEntity.ok(coordenadorService.atualizar(cpf, c));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        coordenadorService.deletar(cpf);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{matricula}/estagiarios")
    public ResponseEntity<CoordenadorModel> adicionarEstagiarios(@PathVariable String matricula, @RequestBody List<String> matriculasEstagiarios) {
        CoordenadorModel atualizado = coordenadorService.adicionarEstagiarios(matricula, matriculasEstagiarios);
        return ResponseEntity.ok(atualizado);
    }
}
