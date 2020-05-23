package ru.itis.zadachnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.repositories.ProblemsRepository;

import java.util.List;

@Service
public class ProblemService {
    @Autowired
    private ProblemsRepository problemsRepository;

    public List<Problem> getAllProblems(){
        return problemsRepository.findAll();
    }
}
