package br.com.yugiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO implements Serializable {

    private String id;
    private String email;
    private String usuario;
    private String senha;
    private String provider;

}
