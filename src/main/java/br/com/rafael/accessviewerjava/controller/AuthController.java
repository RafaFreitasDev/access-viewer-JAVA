package br.com.rafael.accessviewerjava.controller;

import br.com.rafael.accessviewerjava.dto.AuthDto;
import br.com.rafael.accessviewerjava.dto.LoginResponseDto;
import br.com.rafael.accessviewerjava.infra.security.TokenService;
import br.com.rafael.accessviewerjava.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    //temos que falar pro spring security onde ele vai pegar isso daqui. Vamos criar no SecurityConfig
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthDto loginData) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        // Criar um objeto LoginResponseDto para retornar o token
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }
}
