package br.com.yugiapp.service.impl;

import br.com.yugiapp.model.Liga;
import br.com.yugiapp.repository.LigaRepository;
import br.com.yugiapp.service.LigaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LigaServiceImpl implements LigaService {

    private final LigaRepository ligaRepository;

    @Override
    public Page<Liga> getAll(Pageable pageable) {
        return ligaRepository.findAll(pageable);
    }

    @Override
    public List<Liga> getAll() {
        return ligaRepository.findAll();
    }

    @Override
    public Liga getById(Long id) {
        return ligaRepository.findById(id).orElseThrow();
    }

    @Override
    public Optional<Liga> getVigente() {
        return ligaRepository.findVigente();
    }

    @Override
    public void delete(Long id) {
        ligaRepository.deleteById(id);
    }

    @Override
    public Liga save(Liga liga) {
        return ligaRepository.save(liga);
    }
}
