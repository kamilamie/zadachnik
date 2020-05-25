package ru.itis.zadachnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.enums.Role;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.models.User;
import ru.itis.zadachnik.repositories.ProblemsRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    @Autowired
    private ProblemsRepository problemsRepository;

    public List<Problem> getAllProblems(){
        return problemsRepository.findAll();
    }

    public Problem getProblemById(Long id){
        return problemsRepository.findById(id).orElse(null);
    }

    /*public void addSolution(String solutionText, Problem problem, User user) {
        Solution solution = Solution.builder()
                .text(solutionText)
                .author(user)
                .date(new Date())
                .problem(problem)
                .build();
        solutionsRepository.save(solution);
    }*/

}
