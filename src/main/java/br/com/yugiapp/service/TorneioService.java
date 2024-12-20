package br.com.yugiapp.service;

import br.com.yugiapp.model.Torneio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TorneioService {

    Page<Torneio> getAll(Pageable pageable);

    List<Torneio> getAll();

    Torneio getById(Long id);

    void delete(Long id);

    Torneio save(Torneio torneio);

}
