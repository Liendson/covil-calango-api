package br.com.yugiapp.controller;

import br.com.yugiapp.model.Usuario;
import br.com.yugiapp.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping
    public Usuario editar(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

}
