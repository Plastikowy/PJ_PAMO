package com.example.tipper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizController {

    private List<QuizQuestion> questionsAndAnswers = new ArrayList<>();
    private List<String> hardcodedQuestionsThatShouldBeTakenFromResources = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int goodAnswers = 0;
    private boolean isFinished = false;

    public List<QuizQuestion> getQuestionsAndAnswers() {
        return questionsAndAnswers;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getGoodAnswers() {
        return goodAnswers;
    }

    public QuizController() {
        GenerateQuestions();
        ShuffleQuestions();
    }

    public String GetCurrentQuestion(){
        return questionsAndAnswers.get(currentQuestionIndex).getQuestion();
    }

    public String GetCurrentAnswer1(){
        return questionsAndAnswers.get(currentQuestionIndex).getAnswer1();
    }

    public String GetCurrentAnswer2(){
        return questionsAndAnswers.get(currentQuestionIndex).getAnswer2();
    }

    public void Answer(int answerIndex){
        if(questionsAndAnswers.get(currentQuestionIndex).getGoodAnswerIndex() == answerIndex)
        {
            goodAnswers++;
        }

        currentQuestionIndex++;
        if(currentQuestionIndex >= questionsAndAnswers.size()){
            FinishQuiz();
        }
    }

    private void FinishQuiz() {
        isFinished = true;
    }

    private void GenerateQuestions() {
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Jedzenie fastfoodów i przetworzonego jedzenia jest zdrowe.;Prawda;Fałsz;1");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Zjedzenie jednego dużego posiłku jest zdrowsze niż kilku mniejszych w ciągu dnia.;Prawda;Fałsz;0");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Ile powinno się pić litrów wody dziennie?;Około 2l;Wcale, lepsze są napoje z dużą zawartością cukru;0");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Który z wymienionych produktów zawiera najwięcej witaminy C na 100g produktu?;Chrzan;Czarna porzeczka;1");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Stosując dietę ubogą w tłuszcze narażamy się na niedobory witamin. Jakich?;A,D,E,K;C#,C++,C,R;0");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Czy jedzenie kurzych jaj jest zdrowe?;Tak;Tak, ale tylko w odpowiednich ilościach;1");

        for (int i=0;i<hardcodedQuestionsThatShouldBeTakenFromResources.size(); i++)
        {
            String question = hardcodedQuestionsThatShouldBeTakenFromResources.get(i).split(";")[0];
            String answer1 = hardcodedQuestionsThatShouldBeTakenFromResources.get(i).split(";")[1];
            String answer2 = hardcodedQuestionsThatShouldBeTakenFromResources.get(i).split(";")[2];
            int goodAnswerIndex = Integer.parseInt(hardcodedQuestionsThatShouldBeTakenFromResources.get(i).split(";")[3]);
            QuizQuestion quizQuestion = new QuizQuestion(question, answer1, answer2, goodAnswerIndex);
            questionsAndAnswers.add(quizQuestion);
        }
    }

    private void ShuffleQuestions(){
        Collections.shuffle(questionsAndAnswers);
    }


}
