package br.com.yugiapp.service;

import br.com.yugiapp.dto.LoginRequestDTO;
import br.com.yugiapp.enums.TipoProviderAuthEnum;
import br.com.yugiapp.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioService usuarioService;

    public Usuario login(LoginRequestDTO loginRequestDTO) throws Exception {
        if (TipoProviderAuthEnum.GOOGLE.getValue().equals(loginRequestDTO.getProvider())) {
            // TODO: validar token recebido
            // TODO: avaliar criação do usuário caso seja pelo Aplicativo
            return usuarioService.getByEmail(loginRequestDTO.getEmail()).orElseThrow();
        }
        if (TipoProviderAuthEnum.CREDENTIALS.getValue().equals(loginRequestDTO.getProvider())) {
            // TODO: validar email e senha criptografados
            Optional<Usuario> usuario = usuarioService.getByEmail(loginRequestDTO.getEmail());
            if (usuario.isPresent() && usuario.get().getPassword().equals(loginRequestDTO.getSenha())) {
                return usuario.get();
            }
            throw new Exception();
        }
        throw new Exception();
    }
}
