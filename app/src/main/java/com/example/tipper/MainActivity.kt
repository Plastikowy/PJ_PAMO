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

// for bill amount input
// for changing the tip percentage
// SeekBar listener
// for displaying text
// for currency formatting
class MainActivity : AppCompatActivity() {
    private var _btnBMICalculator: Button? = null
    private var _btnCaloriesCounter: Button? = null
    private var _btnQuiz: Button? = null
    private var _btnCannonGame: Button? = null

    // called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // call superclass onCreate
        setContentView(R.layout.activity_main) // inflate the GUI
        _btnBMICalculator = findViewById<View?>(R.id.btnBMICalc) as Button
        _btnCaloriesCounter = findViewById<View?>(R.id.btnCaloriesCounter) as Button
        _btnQuiz = findViewById<View?>(R.id.btnQuiz) as Button
        _btnCannonGame = findViewById<View?>(R.id.btnCannonGame) as Button
        _btnBMICalculator!!.setOnClickListener(View.OnClickListener { GoToBMICalculator() })
        _btnCaloriesCounter!!.setOnClickListener(View.OnClickListener { GoToCaloriesCounter() })
        _btnQuiz!!.setOnClickListener(View.OnClickListener { GoToQuiz() })
        _btnCannonGame!!.setOnClickListener(View.OnClickListener { GoToCannonGame() })
    }

    private fun GoToBMICalculator() {
        val intent = Intent(this, BMICalculator::class.java)
        startActivity(intent)
    }

    private fun GoToCaloriesCounter() {
        val intent = Intent(this, CaloriesCounter::class.java)
        startActivity(intent)
    }

    private fun GoToQuiz() {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }

    private fun GoToCannonGame() {
        val intent = Intent(this, CannonActivity::class.java)
        startActivity(intent)
    }
}