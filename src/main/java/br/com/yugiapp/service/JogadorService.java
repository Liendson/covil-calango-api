package br.com.yugiapp.service;

import br.com.yugiapp.model.Jogador;
import br.com.yugiapp.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public Page<Jogador> getAll(Pageable pageable) {
        return jogadorRepository.findAll(pageable);
    }

    public List<Jogador> getAll() {
        return jogadorRepository.findAll();
    }

    public Jogador getById(Long id) {
        return jogadorRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        jogadorRepository.deleteById(id);
    }

    public Jogador save(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }
}
