package br.com.yugiapp.controller;

import br.com.yugiapp.model.Liga;
import br.com.yugiapp.service.LigaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liga")
@RequiredArgsConstructor
public class LigaController {

    private final LigaService ligaService;

    @PostMapping
    public void salvar(@RequestBody Liga liga) {
        ligaService.save(liga);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        ligaService.delete(id);
    }

    @GetMapping
    public List<Liga> getAll() {
        return ligaService.getAll();
    }

    @GetMapping(path = "{id}")
    public Liga getById(@PathVariable Long id) {
        return ligaService.getById(id);
    }

    @GetMapping(path = "/vigente")
    public Liga getVigente() {
        return ligaService.getVigente().orElse(null);
    }
}
