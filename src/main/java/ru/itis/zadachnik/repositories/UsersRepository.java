package ru.itis.zadachnik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.models.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLogin(String login);
    Optional<User> findOneById(Long id);
}
