package ru.itis.zadachnik.models;

import java.util.List;

public class Student {

    private Long id;

    private String name;

    private String surname;

    private Group group;

    private List<Solution> solutions;

    private Teacher teacher;
}
