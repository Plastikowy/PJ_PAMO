package com.example.tipper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    QuizController controller;
    TextView questionTextView;
    Button answer1btn;
    Button answer2btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.quiz_page); // inflate the GUI

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        answer1btn = (Button) findViewById(R.id.answer1button);
        answer2btn = (Button) findViewById(R.id.answer2button);

        controller = new QuizController();

        SetUpQuestionAndAnswer();

        answer1btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                controller.Answer(0);
                checkContinuityOfQuiz();
            }
        });

        answer2btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                controller.Answer(1);
                checkContinuityOfQuiz();
            }
        });
    }

    private void checkContinuityOfQuiz() {
        if(controller.isFinished()){
            questionTextView.setText("Udzieliłeś " + controller.getGoodAnswers() + " poprawnych odpowiedzi z " + controller.getQuestionsAndAnswers().size());
            answer1btn.setVisibility(View.INVISIBLE);
            answer2btn.setVisibility(View.INVISIBLE);
        }
        else{
            SetUpQuestionAndAnswer();
        }
    }

    private void SetUpQuestionAndAnswer(){
        questionTextView.setText(controller.GetCurrentQuestion());
        answer1btn.setText(controller.GetCurrentAnswer1());
        answer2btn.setText(controller.GetCurrentAnswer2());
    }

}
