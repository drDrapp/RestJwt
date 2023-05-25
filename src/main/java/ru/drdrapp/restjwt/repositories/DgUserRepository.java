package ru.drdrapp.restjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.drdrapp.restjwt.models.DgUser;

import java.util.Optional;

@Repository
public interface DgUserRepository extends JpaRepository<DgUser, Long> {
    Optional<DgUser> findByLogin(String login);
}