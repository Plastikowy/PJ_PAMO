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

class CaloriesCounter : AppCompatActivity() {
    private var height: EditText? = null
    private var age: EditText? = null
    private var mass: EditText? = null
    private var submitButton: Button? = null
    private var caloriesScore: TextView? = null
    private var recipesBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // call superclass onCreate
        setContentView(R.layout.calories_page) // inflate the GUI
        height = findViewById<View?>(R.id._caloriesEditTextHeightCalorie) as EditText
        age = findViewById<View?>(R.id._caloriesEditTextAge) as EditText
        mass = findViewById<View?>(R.id._caloriesEditTextMass) as EditText
        submitButton = findViewById<View?>(R.id._caloriesSubmitBtn) as Button
        recipesBtn = findViewById<View?>(R.id._caloriesRecipesBtn) as Button
        caloriesScore = findViewById<View?>(R.id._caloriesCaloriesScore) as TextView
        recipesBtn!!.setVisibility(View.INVISIBLE)
        submitButton!!.setOnClickListener(View.OnClickListener {
            val m_age = age!!.getText().toString().replace(',', '.').toFloat()
            val m_height = height!!.getText().toString().replace(',', '.').toFloat()
            val m_mass = mass!!.getText().toString().replace(',', '.').toFloat()

            //opcja tylko dla mezczyzn
            val score = (66.47 + 13.7 * m_mass + 5 * m_height - 6.76 * m_age)
            caloriesScore!!.setText("You need at least $score calories to eat daily")
            recipesBtn!!.setVisibility(View.VISIBLE)
        })
        recipesBtn!!.setOnClickListener(View.OnClickListener { GoToRecipes() })
    }

    private fun GoToRecipes() {
        val intent = Intent(this, RecipesActivity::class.java)
        startActivity(intent)
    }
}