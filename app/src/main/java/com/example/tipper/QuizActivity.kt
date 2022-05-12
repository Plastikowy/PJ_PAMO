package com.example.tipper

import org.junit.runner.RunWith
import com.example.tipper.CannonView
import com.example.tipper.Cannonball
import com.example.tipper.GameElement
import com.example.tipper.CannonView.CannonThread
import android.app.Activity
import com.example.tipper.Cannon
import com.example.tipper.Blocker
import android.media.SoundPool
import android.util.SparseIntArray
import com.example.tipper.R
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
import android.media.AudioManager
import android.view.*
import android.widget.Button

class QuizActivity : AppCompatActivity() {
    var controller: QuizController? = null
    var questionTextView: TextView? = null
    var answer1btn: Button? = null
    var answer2btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // call superclass onCreate
        setContentView(R.layout.quiz_page) // inflate the GUI
        questionTextView = findViewById<View?>(R.id.questionTextView) as TextView
        answer1btn = findViewById<View?>(R.id.answer1button) as Button
        answer2btn = findViewById<View?>(R.id.answer2button) as Button
        controller = QuizController()
        SetUpQuestionAndAnswer()
        answer1btn!!.setOnClickListener(View.OnClickListener {
            controller!!.Answer(0)
            checkContinuityOfQuiz()
        })
        answer2btn!!.setOnClickListener(View.OnClickListener {
            controller!!.Answer(1)
            checkContinuityOfQuiz()
        })
    }

    private fun checkContinuityOfQuiz() {
        if (controller!!.isFinished()) {
            questionTextView!!.setText("Udzieliłeś " + controller!!.getGoodAnswers() + " poprawnych odpowiedzi z " + controller!!.getQuestionsAndAnswers()!!.size)
            answer1btn!!.setVisibility(View.INVISIBLE)
            answer2btn!!.setVisibility(View.INVISIBLE)
        } else {
            SetUpQuestionAndAnswer()
        }
    }

    private fun SetUpQuestionAndAnswer() {
        questionTextView!!.setText(controller!!.GetCurrentQuestion())
        answer1btn!!.setText(controller!!.GetCurrentAnswer1())
        answer2btn!!.setText(controller!!.GetCurrentAnswer2())
    }
}