package br.com.yugiapp.controller;

import br.com.yugiapp.model.Produto;
import br.com.yugiapp.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public void salvar(@RequestBody Produto liga) {
        produtoService.save(liga);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        produtoService.delete(id);
    }

    @GetMapping
    public List<Produto> getAll() {
        return produtoService.getAll();
    }

    @GetMapping(path = "{id}")
    public Produto getById(@PathVariable Long id) {
        return produtoService.getById(id);
    }
}
