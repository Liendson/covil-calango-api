package br.com.yugiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private String nome;

    private String contato;

    private String email;

    private String password;

    private String token;

    private List<String> perfis;
}
