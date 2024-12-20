package br.com.yugiapp.controller;

import br.com.yugiapp.model.Jogador;
import br.com.yugiapp.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogador")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    public void salvar(@RequestBody Jogador liga) {
        jogadorService.save(liga);
    }

    @PutMapping
    public void editar(@RequestBody Jogador liga) {
        jogadorService.save(liga);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        jogadorService.delete(id);
    }

    @GetMapping
    public List<Jogador> getAll() {
        return jogadorService.getAll();
    }

    @GetMapping(path = "{id}")
    public Jogador getById(@PathVariable Long id) {
        return jogadorService.getById(id);
    }
}
