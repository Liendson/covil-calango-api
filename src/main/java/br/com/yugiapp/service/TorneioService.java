package br.com.yugiapp.service;

import br.com.yugiapp.dto.TorneioFilterRequestDTO;
import br.com.yugiapp.model.Torneio;
import br.com.yugiapp.repository.TorneioRepository;
import br.com.yugiapp.specs.TorneioSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TorneioService {

    private final TorneioRepository torneioRepository;

    private final TorneioSpecifications torneioSpecifications;

    public Page<Torneio> getAll(Pageable pageable) {
        return torneioRepository.findAll(pageable);
    }

    public List<Torneio> getAll() {
        return torneioRepository.findAll();
    }

    public List<Torneio> getAllByFilters(TorneioFilterRequestDTO filterDTO) {
        return torneioRepository.findAll(torneioSpecifications.getSpecs(filterDTO));
    }

    public Torneio getById(Long id) {
        return torneioRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        torneioRepository.deleteById(id);
    }

    public Torneio save(Torneio torneio) {
        return torneioRepository.save(torneio);
    }
}
