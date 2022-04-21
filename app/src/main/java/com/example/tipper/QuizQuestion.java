package com.example.tipper;

public class QuizQuestion {

    private String question;
    private String answer1;
    private String answer2;
    private int goodAnswerIndex;

    public int getGoodAnswerIndex() {
        return goodAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public QuizQuestion(String question, String answer1, String answer2, int goodAnswerIndex) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.goodAnswerIndex = goodAnswerIndex;
    }
}
