package com.module.entry;

public class Question {
    private String question;
    private String answer;
    private int points;

    public Question(String question, String answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getPoints() {
        return points;
    }

    public int getPointsForAnswer(String answer){
        if (this.answer.equals(answer))
            return points;
        else
            return 0;
    }
}
