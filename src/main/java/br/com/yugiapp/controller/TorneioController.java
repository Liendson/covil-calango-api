package br.com.yugiapp.controller;

import br.com.yugiapp.dto.TorneioFilterRequestDTO;
import br.com.yugiapp.model.Torneio;
import br.com.yugiapp.service.TorneioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torneio")
@RequiredArgsConstructor
public class TorneioController {

    private final TorneioService torneioService;

    @PostMapping
    public void salvar(@RequestBody Torneio liga) {
        torneioService.save(liga);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        torneioService.delete(id);
    }

    @GetMapping
    public List<Torneio> getAll(TorneioFilterRequestDTO filterDTO) {
        return torneioService.getAllByFilters(filterDTO);
    }

    @GetMapping(path = "{id}")
    public Torneio getById(@PathVariable Long id) {
        return torneioService.getById(id);
    }
}
