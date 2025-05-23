package br.com.yugiapp.service;

import br.com.yugiapp.config.security.JwtTokenService;
import br.com.yugiapp.dto.UsuarioConvidadoResponseDTO;
import br.com.yugiapp.model.Solicitacao;
import br.com.yugiapp.model.Usuario;
import br.com.yugiapp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtTokenService jwtTokenService;

    public Page<Usuario> getAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public Optional<Usuario> getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // TODO: Remover para criar autenticação
    public UsuarioConvidadoResponseDTO criarUsuarioConvidado(String nome) {
        return UsuarioConvidadoResponseDTO.builder().nome(nome).token(jwtTokenService.generateToken(nome)).build();
    }

    public Usuario criarNovoUsuario(Solicitacao solicitacao) {
        // TODO: ADICIONAR PERFIL PADRÃO
        return usuarioRepository.save(Usuario.builder().nome(solicitacao.getNome()).email(solicitacao.getEmail()).build());
    }

}
