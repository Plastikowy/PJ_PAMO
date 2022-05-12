// Target.java
// Subclass of GameElement customized for the Target
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

class Target     // constructor
(view: CannonView?, color: Int, // the hit reward for this target
 private val hitReward: Int, x: Int, y: Int,
 width: Int, length: Int, velocityY: Float) : GameElement(view, color, CannonView.Companion.TARGET_SOUND_ID, x, y, width, length,
        velocityY) {
    // returns the hit reward for this Target
    fun getHitReward(): Int {
        return hitReward
    }
}