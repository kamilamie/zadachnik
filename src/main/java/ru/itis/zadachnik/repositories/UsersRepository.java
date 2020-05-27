package ru.itis.zadachnik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zadachnik.enums.Role;
import ru.itis.zadachnik.models.Group;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLogin(String login);
    Optional<User> findOneById(Long id);
    List<User> findAllByRole(Role role);
    List<User> findAllByGroup_id (Long id);
}
