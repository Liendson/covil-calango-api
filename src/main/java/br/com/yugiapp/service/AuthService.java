package br.com.yugiapp.service;

import br.com.yugiapp.dto.LoginRequestDTO;
import br.com.yugiapp.enums.TipoProviderAuthEnum;
import br.com.yugiapp.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioService usuarioService;

    public Usuario login(LoginRequestDTO loginRequestDTO) throws Exception {
        if (TipoProviderAuthEnum.GOOGLE.getValue().equals(loginRequestDTO.getProvider())) {
            // validar token recebido
            // avaliar criação do usuário caso seja pelo Aplicativo
            return usuarioService.getByEmail(loginRequestDTO.getEmail());
        }
        if (TipoProviderAuthEnum.CREDENTIALS.getValue().equals(loginRequestDTO.getProvider())) {
            // validar email e senha criptografados
            Usuario usuario = usuarioService.getByEmail(loginRequestDTO.getEmail());
            if (Objects.nonNull(usuario) && usuario.getPassword().equals(loginRequestDTO.getSenha())) {
                return usuario;
            }
            throw new Exception();
        }
        throw new Exception();
    }
}
