package ru.itis.zadachnik.enums;

public enum ProgrammingLanguage {
    JAVA, C, PYTHON, JS, HTML, CSS;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
