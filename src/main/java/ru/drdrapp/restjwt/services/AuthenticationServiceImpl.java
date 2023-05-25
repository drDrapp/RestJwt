package ru.drdrapp.restjwt.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.drdrapp.restjwt.dto.request.SignInRequest;
import ru.drdrapp.restjwt.dto.request.SignUpRequest;
import ru.drdrapp.restjwt.dto.response.JwtAuthenticationResponse;
import ru.drdrapp.restjwt.models.DgUser;
import ru.drdrapp.restjwt.models.Role;
import ru.drdrapp.restjwt.models.State;
import ru.drdrapp.restjwt.repositories.DgUserRepository;
import ru.drdrapp.restjwt.services.interfaces.AuthenticationService;
import ru.drdrapp.restjwt.services.interfaces.JwtService;

import java.util.Collections;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final DgUserRepository dgUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(DgUserRepository dgUserRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.dgUserRepository = dgUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        String hashPassword = passwordEncoder.encode(request.getPassword());
        var dgUser = DgUser.builder()
                .login(request.getLogin())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .hashPassword(hashPassword)
                .roles(Collections.singleton(Role.USER))
                .state(State.ACTIVE)
                .build();
        dgUserRepository.save(dgUser);
        var jwt = jwtService.generateToken(dgUser);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        var dgUser = dgUserRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Invalid authentication."));
        var jwt = jwtService.generateToken(dgUser);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
