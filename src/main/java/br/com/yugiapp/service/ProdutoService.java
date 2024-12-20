package br.com.yugiapp.service;

import br.com.yugiapp.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoService {

    Page<Produto> getAll(Pageable pageable);

    List<Produto> getAll();

    Produto getById(Long id);

    void delete(Long id);

    Produto save(Produto produto);
}
