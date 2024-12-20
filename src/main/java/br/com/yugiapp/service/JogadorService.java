package br.com.yugiapp.service;

import br.com.yugiapp.model.Jogador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JogadorService {

    Page<Jogador> getAll(Pageable pageable);

    List<Jogador> getAll();

    Jogador getById(Long id);

    void delete(Long id);

    Jogador save(Jogador jogador);
}
