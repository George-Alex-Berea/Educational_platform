package org.example;

import java.util.HashMap;
import java.util.Map;

public class Student implements Comparable<Student> {
    private String name;
    private String group;
    private Map<Exam, Double> exams;

    public Student(String name, String group) {
        this.name = name;
        this.group = group;
        this.exams = new HashMap<>();
    }

    public void addExam(Exam exam, double value) {
        exams.put(exam, value);
    }

    @Override
    public int compareTo(Student student) {
        return this.name.compareTo(student.name);
    }

    public double getScore(Exam exam) {
        return exams.get(exam);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Map<Exam, Double> getExams() {
        return exams;
    }

    public void setExams(Map<Exam, Double> exams) {
        this.exams = exams;
    }
}
