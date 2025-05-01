package br.com.yugiapp.controller;

import br.com.yugiapp.dto.LoginRequestDTO;
import br.com.yugiapp.model.Usuario;
import br.com.yugiapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        return authService.login(loginRequestDTO);
    }
}
