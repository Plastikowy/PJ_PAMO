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
import java.text.NumberFormat

// for EditText event handling
// EditText listener
// for bill amount input
// for changing the tip percentage
// SeekBar listener
// for displaying text
// for currency formatting
class BMICalculator : AppCompatActivity() {
    private var heightTextView // shows formatted bill amount
            : EditText? = null
    private var weightTextView // shows tip percentage
            : EditText? = null
    private var maleCheckbox: CheckBox? = null
    private var BMIResultTextView: TextView? = null
    private var submitButton: Button? = null

    // called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // call superclass onCreate
        setContentView(R.layout.bmi_calculator) // inflate the GUI

        // get references to programmatically manipulated TextViews
        heightTextView = findViewById<View?>(R.id.heightEditText) as EditText
        weightTextView = findViewById<View?>(R.id.weightEditText) as EditText
        maleCheckbox = findViewById<View?>(R.id.isMaleCheckBox) as CheckBox
        BMIResultTextView = findViewById<View?>(R.id.BMITextView) as TextView
        submitButton = findViewById<View?>(R.id.submitButton) as Button
        heightTextView!!.setHint("Input height (ex. 1,89)")
        weightTextView!!.setHint("Input weight (ex. 67,9)")
        submitButton!!.setOnClickListener(View.OnClickListener {
            val weight = weightTextView!!.getText().toString().replace(',', '.').toFloat()
            val height = heightTextView!!.getText().toString().replace(',', '.').toFloat()
            val isMale = maleCheckbox!!.isChecked()
            val bmi = weight / Math.pow(height.toDouble(), 2.0)
            if (isMale) {
                if (bmi > 19.5 && bmi < 30) BMIResultTextView!!.setText("Your BMI equals = $bmi which means it's ok!") else BMIResultTextView!!.setText("Your BMI equals = $bmi which means there's something wrong!")
            } else {
                if (bmi > 18 && bmi < 30) BMIResultTextView!!.setText("Your BMI equals = $bmi which means it's ok!") else BMIResultTextView!!.setText("Your BMI equals = $bmi which means there's something wrong!")
            }
        })
    }

    companion object {
        // currency and percent formatter objects
        private val numberFormat = NumberFormat.getNumberInstance()
    }
}