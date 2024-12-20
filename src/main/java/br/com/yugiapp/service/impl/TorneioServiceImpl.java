package br.com.yugiapp.service.impl;

import br.com.yugiapp.model.Torneio;
import br.com.yugiapp.repository.TorneioRepository;
import br.com.yugiapp.service.TorneioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TorneioServiceImpl implements TorneioService {

    private final TorneioRepository torneioRepository;

    @Override
    public Page<Torneio> getAll(Pageable pageable) {
        return torneioRepository.findAll(pageable);
    }

    @Override
    public List<Torneio> getAll() {
        return torneioRepository.findAll();
    }

    @Override
    public Torneio getById(Long id) {
        return torneioRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        torneioRepository.deleteById(id);
    }

    @Override
    public Torneio save(Torneio torneio) {
        return torneioRepository.save(torneio);
    }
}
