package com.example.tipper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CaloriesCounter extends AppCompatActivity {

    private EditText height;
    private EditText age;
    private EditText mass;
    private Button submitButton;
    private TextView caloriesScore;
    private Button recipesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.calories_page); // inflate the GUI

        height = (EditText) findViewById(R.id._caloriesEditTextHeightCalorie);
        age = (EditText) findViewById(R.id._caloriesEditTextAge);
        mass = (EditText) findViewById(R.id._caloriesEditTextMass);
        submitButton = (Button) findViewById(R.id._caloriesSubmitBtn);
        recipesBtn = (Button) findViewById(R.id._caloriesRecipesBtn);
        caloriesScore = (TextView) findViewById(R.id._caloriesCaloriesScore);

        recipesBtn.setVisibility(View.INVISIBLE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float m_age = Float.parseFloat(age.getText().toString().replace(',','.'));
                float m_height = Float.parseFloat(height.getText().toString().replace(',','.'));
                float m_mass = Float.parseFloat(mass.getText().toString().replace(',','.'));

                //opcja tylko dla mezczyzn

                float score = (float) (66.47 + (13.7 * m_mass) + (5* m_height) - (6.76 * m_age));

                caloriesScore.setText("You need at least " + score + " calories to eat daily");

                recipesBtn.setVisibility(View.VISIBLE);
            }
        });

        recipesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToRecipes();
            }
        });
    }

    private void GoToRecipes(){
        Intent intent = new Intent(this, RecipesActivity.class);
        startActivity(intent);
    }
}
