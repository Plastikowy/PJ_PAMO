package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing the tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView; // for displaying text

import java.text.NumberFormat; // for currency formatting

public class MainActivity extends AppCompatActivity {

    // currency and percent formatter objects
    private static final NumberFormat numberFormat =
            NumberFormat.getNumberInstance();

    private EditText heightTextView; // shows formatted bill amount
    private EditText weightTextView; // shows tip percentage
    private CheckBox maleCheckbox;
    private TextView BMIResultTextView;
    private Button submitButton;

    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        // get references to programmatically manipulated TextViews
        heightTextView = (EditText) findViewById(R.id.heightEditText);
        weightTextView = (EditText) findViewById(R.id.weightEditText);
        maleCheckbox = (CheckBox) findViewById(R.id.isMaleCheckBox);
        BMIResultTextView = (TextView) findViewById(R.id.BMITextView);
        submitButton = (Button) findViewById(R.id.submitButton);

        heightTextView.setHint("Input height (ex. 1,89)");
        weightTextView.setHint("Input weight (ex. 67,9)");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float weight = Float.parseFloat(weightTextView.getText().toString().replace(',','.'));
                float height = Float.parseFloat(heightTextView.getText().toString().replace(',','.'));

                boolean isMale = maleCheckbox.isChecked();

                double bmi = weight / (Math.pow(height, 2));

                if (isMale) {
                    if (bmi > 19.5 && bmi < 30)
                        BMIResultTextView.setText("Your BMI equals = " + bmi + " which means it's ok!");
                    else
                        BMIResultTextView.setText("Your BMI equals = " + bmi + " which means there's something wrong!");
                } else {
                    if (bmi > 18 && bmi < 30)
                        BMIResultTextView.setText("Your BMI equals = " + bmi + " which means it's ok!");
                    else
                        BMIResultTextView.setText("Your BMI equals = " + bmi + " which means there's something wrong!");
                }
            }
        });
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
