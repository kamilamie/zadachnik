package ru.itis.zadachnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.enums.*;
import ru.itis.zadachnik.forms.NewProblemForm;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.models.User;
import ru.itis.zadachnik.repositories.ProblemsRepository;

import java.util.Collections;
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

    public Long addNewProblem(NewProblemForm newProblemForm) {
        Problem newProblem = Problem.builder()
                .commLanguage(CommunicationLanguage.valueOf(newProblemForm.getCommLang()))
                .progrLanguage(ProgrammingLanguage.valueOf(newProblemForm.getProgrLang()))
                .source(Source.TEACHERS)
                .text(newProblemForm.getProblemText())
                .difficulty(Difficulty.valueOf(newProblemForm.getDifficulty()))
                .topics(Collections.singletonList(Topic.valueOf(newProblemForm.getTopics())))
                .build();
        problemsRepository.save(newProblem);
        return newProblem.getId();
    }

    public void editProblem(NewProblemForm newProblemForm, String id) {
        Problem problem = problemsRepository.getOne(Long.parseLong(id));
        if (!newProblemForm.getCommLang().isEmpty()){
            problem.setCommLanguage(CommunicationLanguage.valueOf(newProblemForm.getCommLang()));
        }
        if (!newProblemForm.getDifficulty().isEmpty()){
            problem.setDifficulty(Difficulty.valueOf(newProblemForm.getDifficulty()));
        }
        if (!newProblemForm.getProgrLang().isEmpty()){
            problem.setProgrLanguage(ProgrammingLanguage.valueOf(newProblemForm.getProgrLang()));
        }
        if (!newProblemForm.getProblemText().isEmpty()){
            problem.setText(newProblemForm.getProblemText());
        }
        if (!newProblemForm.getTopics().isEmpty()){
            problem.setTopics(Collections.singletonList(Topic.valueOf(newProblemForm.getTopics())));
        }
        problemsRepository.save(problem);
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
