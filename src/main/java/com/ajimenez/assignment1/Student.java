package com.ajimenez.assignment1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Student {
    private String studentId = "N/A";
    private static Set<String> studentIds = new HashSet<String>();

    Student(String studentId) throws Exception {
        setStudentId(studentId);
    }

    String getStudentId() {
        return studentId;
    }

    private void setStudentId(String studentId) throws Exception {
        if (studentIds.contains(studentId)) {
            throw new Exception("Student with ID \"" + studentId + "\" already exists");
        }
        
        studentIds.add(studentId);
        this.studentId = studentId;
    }

    public void vote(IVoteService votingService) {
        Random r = new Random();
        if (r.nextDouble() < 0.75) {
            Set<Integer> myAnswer = votingService.getCurrentQuestion().getAnswers();
            votingService.submitAnswer(studentId, myAnswer);
        }
        else {
            Set<Integer> myAnswer = votingService.getCurrentQuestion().randomAnswer();
            votingService.submitAnswer(studentId, myAnswer);
        }
    }
}
