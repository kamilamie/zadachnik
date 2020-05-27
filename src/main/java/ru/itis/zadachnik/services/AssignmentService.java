package ru.itis.zadachnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.enums.Role;
import ru.itis.zadachnik.forms.NewAssignmentForm;
import ru.itis.zadachnik.models.Assignment;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.models.User;
import ru.itis.zadachnik.repositories.AssignmentsRepository;
import ru.itis.zadachnik.repositories.ProblemsRepository;
import ru.itis.zadachnik.repositories.UsersRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @Autowired
    private ProblemsRepository problemsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<Assignment> getAllAssignmentsByUser(User user) {
        if (user.getRole().equals(Role.STUDENT)) {
            return assignmentsRepository.findAllByStudent_id(user.getId());
        } else {
            return assignmentsRepository.findAllByTeacher_id(user.getId());
        }
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentsRepository.findById(id).orElse(null);
    }

    public void addNewAssignment(NewAssignmentForm form, User teacher) {
        List<User> students = new ArrayList<>();

        if (!form.getGroup().isEmpty()) {
            List<User> studentsFromGroup = usersRepository.findAllByGroup_id(Long.parseLong(form.getGroup()));
            students.addAll(studentsFromGroup);
        }
        if (!form.getStudent().isEmpty()) {
            students.add(usersRepository.findOneById(Long.parseLong(form.getStudent())).get());
        }
        for (User student : students) {
            Assignment assignment = Assignment.builder()
                    .problems(getProblemsFromString(form.getProblemsIds()))
                    .student(student)
                    .teacher(teacher)
                    .title(form.getTitle())
                    .creationDate(new Date())
                    .attempts(0)
                    .deadline(parseStringDate(form.getDeadline()))
                    .build();
            assignmentsRepository.save(assignment);
        }
    }

    public void addSolution(Long ass_id, String solution) {
        Assignment assignment = assignmentsRepository.getOne(ass_id);
        assignment.setSolution(solution);
        assignment.setAttempts(assignment.getAttempts() + 1);
        assignment.setCompletion(new Date());
        assignmentsRepository.save(assignment);
    }

    private Date parseStringDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private List<Problem> getProblemsFromString(String ids) {
        List<Problem> problemList = new ArrayList<>();
        String[] problemsIds = ids.split(",");
        for (String id : problemsIds) {
            Problem problem = problemsRepository.findProblemById(Long.parseLong(id)).get();
            problemList.add(problem);
        }
        return problemList;
    }
}
