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
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }
}