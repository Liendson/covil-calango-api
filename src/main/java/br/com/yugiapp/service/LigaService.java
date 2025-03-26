package br.com.yugiapp.service;

import br.com.yugiapp.model.Liga;
import br.com.yugiapp.repository.LigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LigaService {

    private final LigaRepository ligaRepository;

    public Page<Liga> getAll(Pageable pageable) {
        return ligaRepository.findAll(pageable);
    }

    public List<Liga> getAll() {
        return ligaRepository.findAll();
    }

    public Liga getById(Long id) {
        return ligaRepository.findById(id).orElseThrow();
    }

    public Optional<Liga> getVigente() {
        return ligaRepository.findVigente();
    }

    public void delete(Long id) {
        ligaRepository.deleteById(id);
    }

    public Liga save(Liga liga) {
        return ligaRepository.save(liga);
    }
}
