package ru.drdrapp.restjwt.services.interfaces;

import ru.drdrapp.restjwt.models.DgUser;
import ru.drdrapp.restjwt.services.DgUserDetailsImpl;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(DgUser dgUser);

    boolean isTokenValid(String token, DgUserDetailsImpl dgUserDetails);
}
