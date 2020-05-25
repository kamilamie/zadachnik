package ru.itis.zadachnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.models.Assignment;
import ru.itis.zadachnik.repositories.AssignmentsRepository;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    public List<Assignment> getAllAssignmentsByUserId(Long userId){
        return assignmentsRepository.findAllByStudent_id(userId);
    }
}
