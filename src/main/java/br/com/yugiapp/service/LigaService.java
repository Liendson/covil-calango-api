package br.com.yugiapp.service;

import br.com.yugiapp.model.Liga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LigaService {

    Page<Liga> getAll(Pageable pageable);

    List<Liga> getAll();

    Liga getById(Long id);

    Optional<Liga> getVigente();

    void delete(Long id);

    Liga save(Liga liga);
}
