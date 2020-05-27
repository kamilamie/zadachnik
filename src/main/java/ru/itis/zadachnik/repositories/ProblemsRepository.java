package ru.itis.zadachnik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zadachnik.models.Problem;

import java.util.Optional;

@Repository
public interface ProblemsRepository extends JpaRepository<Problem, Long> {
    Optional<Problem> findProblemById(Long id);
}
