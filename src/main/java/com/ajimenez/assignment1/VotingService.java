package com.ajimenez.assignment1;

import java.util.HashMap;
import java.util.Set;

// A vote service provides the ability to submit answers and print results
interface IVoteService {
    void submitAnswer(String studentId, Set<Integer> answer);
    Question getCurrentQuestion();
    void printResults();
}

class VotingService implements IVoteService {
    private Question currentQuestion;
    private HashMap<String, Set<Integer>> answers = new HashMap<String, Set<Integer>>();

    // Must be initiated with the question
    VotingService(Question question) {
        this.currentQuestion = question;
    }

    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    // Allow students to submit answers. Stored in hashmap to prevent repeats
    public void submitAnswer(String studentId, Set<Integer> answer) {
        answers.put(studentId, answer);
    }

    // Output the number of each choice selected
    public void printResults() {
        int[] results = new int[this.currentQuestion.getChoices().length];
        for (Set<Integer> answer : answers.values()) {
            for (Integer i : answer) {
                results[i]++;
            }
        }

        System.out.println("Voting results:");
        for (int i = 0; i < results.length; i++) {
            System.out.println((char)('A' + i) + ": " + results[i]);
        }
    }
}
