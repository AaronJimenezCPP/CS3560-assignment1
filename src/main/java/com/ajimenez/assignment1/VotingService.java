package com.ajimenez.assignment1;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Set;

interface IVoteService {
    void submitAnswer(String studentId, Set<Integer> answer);
    Question getCurrentQuestion();
    void printResults();
}

class VotingService implements IVoteService {
    private Question currentQuestion;
    private HashMap<String, Set<Integer>> answers = new HashMap<String, Set<Integer>>();

    VotingService(Question question) {
        this.currentQuestion = question;
    }

    public void submitAnswer(String studentId, Set<Integer> answer) {
        answers.put(studentId, answer);
    }

    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

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
