package ru.itis.zadachnik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.models.Solution;

@Repository
public interface SolutionsRepository extends JpaRepository<Solution, Long> {
}
