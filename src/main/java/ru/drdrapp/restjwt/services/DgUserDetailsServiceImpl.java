package ru.drdrapp.restjwt.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.drdrapp.restjwt.repositories.DgUserRepository;

@Service
public class DgUserDetailsServiceImpl implements UserDetailsService {

    final DgUserRepository dgUserRepository;

    public DgUserDetailsServiceImpl(DgUserRepository dgUserRepository) {
        this.dgUserRepository = dgUserRepository;
    }

    @Override
    public DgUserDetailsImpl loadUserByUsername(String login) throws UsernameNotFoundException {
        return new DgUserDetailsImpl(dgUserRepository.findByLogin(login).orElseThrow(IllegalArgumentException::new));
    }

}