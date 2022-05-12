package com.example.tipper

import org.junit.runner.RunWith
import com.example.tipper.CannonView
import com.example.tipper.Cannonball
import com.example.tipper.GameElement
import android.view.SurfaceView
import android.view.SurfaceHolder
import com.example.tipper.CannonView.CannonThread
import android.app.Activity
import com.example.tipper.Cannon
import com.example.tipper.Blocker
import android.media.SoundPool
import android.util.SparseIntArray
import com.example.tipper.R
import android.view.MotionEvent
import android.os.Build
import android.media.AudioAttributes
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.tipper.BMICalculator
import com.example.tipper.CaloriesCounter
import com.example.tipper.QuizActivity
import com.example.tipper.CannonActivity
import com.example.tipper.QuizController
import android.widget.TextView
import android.widget.EditText
import android.widget.CheckBox
import com.example.tipper.QuizQuestion
import com.example.tipper.RecipesActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.media.AudioManager
import java.util.*

class QuizController {
    private val questionsAndAnswers: MutableList<QuizQuestion?>? = ArrayList()
    private val hardcodedQuestionsThatShouldBeTakenFromResources: MutableList<String?>? = ArrayList()
    private var currentQuestionIndex = 0
    private var goodAnswers = 0
    private var isFinished = false
    fun getQuestionsAndAnswers(): MutableList<QuizQuestion?>? {
        return questionsAndAnswers
    }

    fun isFinished(): Boolean {
        return isFinished
    }

    fun getGoodAnswers(): Int {
        return goodAnswers
    }

    fun GetCurrentQuestion(): String? {
        return questionsAndAnswers!!.get(currentQuestionIndex)!!.getQuestion()
    }

    fun GetCurrentAnswer1(): String? {
        return questionsAndAnswers!!.get(currentQuestionIndex)!!.getAnswer1()
    }

    fun GetCurrentAnswer2(): String? {
        return questionsAndAnswers!!.get(currentQuestionIndex)!!.getAnswer2()
    }

    fun Answer(answerIndex: Int) {
        if (questionsAndAnswers!!.get(currentQuestionIndex)!!.getGoodAnswerIndex() == answerIndex) {
            goodAnswers++
        }
        currentQuestionIndex++
        if (currentQuestionIndex >= questionsAndAnswers.size) {
            FinishQuiz()
        }
    }

    private fun FinishQuiz() {
        isFinished = true
    }

    private fun GenerateQuestions() {
        hardcodedQuestionsThatShouldBeTakenFromResources!!.add("Jedzenie fastfoodów i przetworzonego jedzenia jest zdrowe.;Prawda;Fałsz;1")
        hardcodedQuestionsThatShouldBeTakenFromResources!!.add("Zjedzenie jednego dużego posiłku jest zdrowsze niż kilku mniejszych w ciągu dnia.;Prawda;Fałsz;0")
        hardcodedQuestionsThatShouldBeTakenFromResources!!.add("Ile powinno się pić litrów wody dziennie?;Około 2l;Wcale, lepsze są napoje z dużą zawartością cukru;0")
        hardcodedQuestionsThatShouldBeTakenFromResources!!.add("Który z wymienionych produktów zawiera najwięcej witaminy C na 100g produktu?;Chrzan;Czarna porzeczka;1")
        hardcodedQuestionsThatShouldBeTakenFromResources!!.add("Stosując dietę ubogą w tłuszcze narażamy się na niedobory witamin. Jakich?;A,D,E,K;C#,C++,C,R;0")
        hardcodedQuestionsThatShouldBeTakenFromResources!!.add("Czy jedzenie kurzych jaj jest zdrowe?;Tak;Tak, ale tylko w odpowiednich ilościach;1")
        for (i in hardcodedQuestionsThatShouldBeTakenFromResources!!.indices) {
            val question = hardcodedQuestionsThatShouldBeTakenFromResources.get(i)!!.split(";").toTypedArray()[0]
            val answer1 = hardcodedQuestionsThatShouldBeTakenFromResources.get(i)!!.split(";").toTypedArray()[1]
            val answer2 = hardcodedQuestionsThatShouldBeTakenFromResources.get(i)!!.split(";").toTypedArray()[2]
            val goodAnswerIndex = hardcodedQuestionsThatShouldBeTakenFromResources.get(i)!!.split(";").toTypedArray()[3].toInt()
            val quizQuestion = QuizQuestion(question, answer1, answer2, goodAnswerIndex)
            questionsAndAnswers!!.add(quizQuestion)
        }
    }

    private fun ShuffleQuestions() {
        Collections.shuffle(questionsAndAnswers)
    }

    init {
        GenerateQuestions()
        ShuffleQuestions()
    }
}