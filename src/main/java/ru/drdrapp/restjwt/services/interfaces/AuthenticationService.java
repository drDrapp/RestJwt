package ru.drdrapp.restjwt.services.interfaces;


import ru.drdrapp.restjwt.dto.request.SignInRequest;
import ru.drdrapp.restjwt.dto.request.SignUpRequest;
import ru.drdrapp.restjwt.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
}
