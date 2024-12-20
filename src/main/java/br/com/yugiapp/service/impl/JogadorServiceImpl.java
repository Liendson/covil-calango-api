package br.com.yugiapp.service.impl;

import br.com.yugiapp.model.Jogador;
import br.com.yugiapp.repository.JogadorRepository;
import br.com.yugiapp.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorServiceImpl implements JogadorService {

    private final JogadorRepository jogadorRepository;

    @Override
    public Page<Jogador> getAll(Pageable pageable) {
        return jogadorRepository.findAll(pageable);
    }

    @Override
    public List<Jogador> getAll() {
        return jogadorRepository.findAll();
    }

    @Override
    public Jogador getById(Long id) {
        return jogadorRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        jogadorRepository.deleteById(id);
    }

    @Override
    public Jogador save(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }
}
