package br.com.yugiapp.service.impl;

import br.com.yugiapp.model.Produto;
import br.com.yugiapp.repository.ProdutoRepository;
import br.com.yugiapp.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Page<Produto> getAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto getById(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }
}
