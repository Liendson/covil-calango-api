package br.com.yugiapp.service;

import br.com.yugiapp.model.Parametro;
import br.com.yugiapp.repository.ParametroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParametroService {

    private final ParametroRepository parametroRepository;

    public Optional<Parametro> findByNome(String nome) {
        return parametroRepository.findByNome(nome);
    }

}
