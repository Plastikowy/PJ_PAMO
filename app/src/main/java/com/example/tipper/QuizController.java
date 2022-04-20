package com.example.tipper;

import java.util.ArrayList;
import java.util.List;

public class QuizController {

    public List<QuizQuestion> questionsAndAnswers = new ArrayList<>();
    private List<String> hardcodedQuestionsThatShouldBeTakenFromResources = new ArrayList<>();
    private int currentQuestionIndex = 0;

    public QuizController() {
        GenerateQuestions();
    }

    public String GetCurrentQuestion(){
        return questionsAndAnswers.get(currentQuestionIndex).getQuestion();
    }

    private void GenerateQuestions() {
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Jedzenie fastfoodów i przetworzonego jedzenia jest zdrowe.;Prawda;Fałsz");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Zjedzenie jednego dużego posiłku jest zdrowsze niż kilku mniejszych w ciągu dnia.;Prawda;Fałsz");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Ile powinno się pić litrów wody dziennie?;Około 2l;Wcale, lepsze są napoje z dużą zawartością cukru");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Który z wymienionych produktów zawiera najwięcej witaminy C na 100g produktu?;Chrzan;Czarna porzeczka");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Stosując dietę ubogą w tłuszcze narażamy się na niedobory witamin. Jakich?;A,D,E,K;C#,C++,C,R");
        hardcodedQuestionsThatShouldBeTakenFromResources.add("Czy jedzenie kurzych jaj jest zdrowe?;Tak;Tak, ale tylko w odpowiednich ilościach");

        for (int i=0;i<hardcodedQuestionsThatShouldBeTakenFromResources.size() - 1; i++)
        {
            String question = hardcodedQuestionsThatShouldBeTakenFromResources.get(i).split(";")[0];
            String answer1 = hardcodedQuestionsThatShouldBeTakenFromResources.get(i).split(";")[1];
            String answer2 = hardcodedQuestionsThatShouldBeTakenFromResources.get(i).split(";")[2];
            QuizQuestion quizQuestion = new QuizQuestion(question, answer1, answer2);
            questionsAndAnswers.add(quizQuestion);
        }
    }
}
