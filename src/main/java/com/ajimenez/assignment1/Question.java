package com.ajimenez.assignment1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

abstract class Question {
    protected String question = "N/A";
    protected String[] choices = {"Choices not configured"};
    private Set<Integer> answers = new HashSet<Integer>();

    Question(String question, String[] choices, Set<Integer> answers) {
        setQuestion(question);
        setChoices(choices);
        setAnswers(answers);
    }

    Question(String question, String[] choices, int answer) {
        setQuestion(question);
        setChoices(choices);
        setAnswer(answer);
    }

    Set<Integer> getAnswers() {
        return answers;
    }

    private void setAnswers(Set<Integer> answers) {
        this.answers.clear();
        this.answers.addAll(answers);
    }

    Set<Integer> randomAnswer() {
        Random r = new Random();
        Set<Integer> thisAnswer = new HashSet<>();
        for (int i = 0; i < choices.length; i++) {
            if (r.nextDouble() < 0.5) {
                thisAnswer.add(i);
            }
        }

        return thisAnswer;
    }

    private void setAnswer(Integer answer) {
        this.answers.clear();
        this.answers.add(answer);
    }

    String[] getChoices() {
        return choices;
    }

    private void setChoices(String[] choices) {
        this.choices = choices;
    }

    String getQuestion() {
        return question;
    }

    private void setQuestion(String question) {
        this.question = question;
    }

    void print() {
        System.out.println(this.question);
        for (int i = 0; i < this.choices.length; i++) {
            System.out.println((char)('A' + i) + ": " + this.choices[i]);
        }
    }
}

class MultipleChoiceQuestion extends Question {
    MultipleChoiceQuestion(String question, String[] choices, Set<Integer> answers) {
        super(question, choices, answers);
    }

    MultipleChoiceQuestion(String question, String[] choices, int answer) {
        super(question, choices, answer);
    }

    void print() {
        System.out.println(this.question);
        System.out.println("Choose all answers that apply:");
        for (int i = 0; i < this.choices.length; i++) {
            System.out.println((char)('A' + i) + ": " + this.choices[i]);
        }
    }
}

class SingleChoiceQuestion extends Question {
    SingleChoiceQuestion(String question, String[] choices, Set<Integer> answers) throws Exception {
        super(question, choices, answers);
        if (answers.size() > 1) {
            throw new Exception("SingleChoiceQuestion may only have 1 answer");
        }     
    }

    SingleChoiceQuestion(String question, String[] choices, int answer) {
        super(question, choices, answer);
    }

    Set<Integer> randomAnswer() {
        Random r = new Random();
        Set<Integer> thisAnswer = new HashSet<>();
        thisAnswer.add(r.nextInt(this.choices.length));
        return thisAnswer;
    }

    void print() {
        System.out.println(this.question);
        System.out.println("Choose 1 answer:");
        for (int i = 0; i < this.choices.length; i++) {
            System.out.println((char)('A' + i) + ": " + this.choices[i]);
        }
    }
}
