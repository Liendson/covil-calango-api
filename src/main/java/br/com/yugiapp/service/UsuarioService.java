package br.com.yugiapp.service;

import br.com.yugiapp.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {

    Page<Usuario> getAll(Pageable pageable);

    List<Usuario> getAll();

    Usuario getById(Long id);

    void delete(Long id);

    Usuario save(Usuario usuario);
}
