package br.com.yugiapp.service;

import br.com.yugiapp.model.Produto;
import br.com.yugiapp.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Page<Produto> getAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public Produto getById(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }
}
