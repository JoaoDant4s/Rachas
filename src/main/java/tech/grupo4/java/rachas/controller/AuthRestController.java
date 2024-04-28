package tech.grupo4.java.rachas.controller;

import lombok.RequiredArgsConstructor;
import tech.grupo4.java.rachas.model.Jogador;
import tech.grupo4.java.rachas.model.dto.LoginDto;
import tech.grupo4.java.rachas.service.JwtService;
import tech.grupo4.java.rachas.service.JogadorService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/username")
@RequiredArgsConstructor
public class AuthRestController {

    private final JogadorService jogadorService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping
    public String username(@RequestBody LoginDto request) {
        Jogador jogador = jogadorService.getByUsernameEntity(request.username());
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authenticationManager.authenticate(authentication);
        return jwtService.createToken(jogador);
    }

}
