package ru.itis.zadachnik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zadachnik.models.Problem;

@Repository
public interface GroupsRepository extends JpaRepository<Problem, Long> {
}
