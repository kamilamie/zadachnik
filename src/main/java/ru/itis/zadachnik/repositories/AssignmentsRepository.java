package ru.itis.zadachnik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.zadachnik.models.Assignment;

import java.util.List;

@Repository
public interface AssignmentsRepository extends JpaRepository <Assignment, Long> {

    List<Assignment> findAllByStudent_id(Long id);
}
