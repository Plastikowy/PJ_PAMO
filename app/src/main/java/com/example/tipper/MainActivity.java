package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing the tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView; // for displaying text

import java.text.NumberFormat; // for currency formatting

public class MainActivity extends AppCompatActivity {

    private Button _btnBMICalculator;
    private Button _btnCaloriesCounter;
    private Button _btnQuiz;
    private Button _btnCannonGame;
    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        _btnBMICalculator = (Button) findViewById(R.id.btnBMICalc);
        _btnCaloriesCounter = (Button) findViewById(R.id.btnCaloriesCounter);
        _btnQuiz = (Button) findViewById(R.id.btnQuiz);
        _btnCannonGame = (Button) findViewById(R.id.btnCannonGame);

        _btnBMICalculator.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                GoToBMICalculator();
            }
        });

        _btnCaloriesCounter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                GoToCaloriesCounter();
            }
        });

        _btnQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                GoToQuiz();
            }
        });
        _btnCannonGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                GoToCannonGame();
            }
        });
    }

    private void GoToBMICalculator(){
        Intent intent = new Intent(this, BMICalculator.class);
        startActivity(intent);
    }

    private void GoToCaloriesCounter(){
        Intent intent = new Intent(this, CaloriesCounter.class);
        startActivity(intent);
    }

    private void GoToQuiz(){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    private void GoToCannonGame(){
        Intent intent = new Intent(this, CannonActivity.class);
        startActivity(intent);
    }
}



/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
