package tech.grupo4.java.rachas.controller;

import lombok.RequiredArgsConstructor;
import tech.grupo4.java.rachas.model.*;
import tech.grupo4.java.rachas.model.dto.LoginDto;
import tech.grupo4.java.rachas.service.JwtService;
import tech.grupo4.java.rachas.service.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthRestController {

    private final JogadorService jogadorService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping
    public String login(@RequestBody LoginDto request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        Jogador jogador = jogadorService.getByUsernameEntity(request.username());
        authenticationManager.authenticate(authentication);
        return jwtService.createToken(jogador);
    }

}