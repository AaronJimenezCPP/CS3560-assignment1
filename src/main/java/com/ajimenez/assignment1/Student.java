package com.ajimenez.assignment1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Student {
    private String studentId = "N/A";
    private static Set<String> studentIds = new HashSet<String>();

    // Require student ID on initialization
    Student(String studentId) throws Exception {
        setStudentId(studentId);
    }

    String getStudentId() {
        return studentId;
    }

    // Throw exception if student ID is already taken
    private void setStudentId(String studentId) throws Exception {
        if (studentIds.contains(studentId)) {
            throw new Exception("Student with ID \"" + studentId + "\" already exists");
        }
        
        studentIds.add(studentId);
        this.studentId = studentId;
    }

    // Simulated voting 
    // 70% to get correct, else select random answer
    public void vote(IVoteService votingService) {
        Random r = new Random();
        if (r.nextDouble() < 0.7) {
            Set<Integer> myAnswer = votingService.getCurrentQuestion().getAnswers();
            votingService.submitAnswer(studentId, myAnswer);
        }
        else {
            Set<Integer> myAnswer = votingService.getCurrentQuestion().randomAnswer();
            votingService.submitAnswer(studentId, myAnswer);
        }
    }
}
