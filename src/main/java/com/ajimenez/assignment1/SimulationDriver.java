package com.ajimenez.assignment1;

import java.util.Random;

public class SimulationDriver 
{
    public static void main( String[] args ) throws Exception
    {
        Student[] students = new Student[50];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student("Student " + (i + 1));
        }

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

        Question q1 = new SingleChoiceQuestion("What is " + n1 + " + " + n2 + "?", choices, correctChoice);
        IVoteService voteService = new VotingService(q1);

        for (Student student : students) {
            student.vote(voteService);
        }
        
        q1.print();
        System.out.println();
        voteService.printResults();
    }
}
