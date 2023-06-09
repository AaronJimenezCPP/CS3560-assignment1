package com.ajimenez.assignment1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SimulationDriver 
{
    public static void main( String[] args ) throws Exception
    {
        Student[] students = generateStudents(50);

        Question q = generateSingleChoiceQuestion();
        IVoteService voteService = new VotingService(q);

        for (Student student : students) {
            student.vote(voteService);
        }
        
        q.print();
        System.out.println();
        voteService.printResults();
        System.out.println();

        q = generateMultipleChoiceQuestion();
        voteService = new VotingService(q);

        for (Student student : students) {
            student.vote(voteService);
        }
        
        q.print();
        System.out.println();
        voteService.printResults();
    }

    // Generate n unique students
    private static Student[] generateStudents(int n) throws Exception {
        Student[] students = new Student[n];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student("Student " + (i + 1));
        }

        return students;
    }

    // Generate simple addition question
    private static SingleChoiceQuestion generateSingleChoiceQuestion() {
        Random r = new Random();
        int n1 = r.nextInt(100);
        int n2 = r.nextInt(100);

        String[] choices = new String[4];
        for (int i = 0; i < choices.length; i++) {
            int rn = r.nextInt(200);
            if (rn == n1 + n2) rn++;
            choices[i] = "" + rn;
        }

        int correctChoice = r.nextInt(choices.length);
        choices[correctChoice] = "" + (n1 + n2); 

        return new SingleChoiceQuestion("What is " + n1 + " + " + n2 + "?", choices, correctChoice);
    }

    // Generate simple "greater than" question
    private static MultipleChoiceQuestion generateMultipleChoiceQuestion() {
        Random r = new Random();
        int n = r.nextInt(100);

        String[] choices = new String[5];
        Set<Integer> answer = new HashSet<Integer>();
        for (int i = 0; i < choices.length - 1; i++) {
            int rn = n + r.nextInt(101) - 50;
            choices[i] = "" + rn;
            if (rn > n) answer.add(i); 
        }

        choices[4] = "None of the above";
        if (answer.size() == 0) answer.add(4);

        return new MultipleChoiceQuestion("Select all numbers greater than " + n, choices, answer);
    }
}
