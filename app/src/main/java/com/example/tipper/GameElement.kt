// GameElement.java
// Represents a rectangle-bounded game element
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
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
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

open class GameElement(// the view that contains this GameElement
        protected var view: CannonView?, color: Int, soundId: Int, x: Int,
        y: Int, width: Int, length: Int, velocityY: Float) {
    protected var paint: Paint? = Paint() // Paint to draw this GameElement
    var shape // the GameElement's rectangular bounds
            : Rect?
    private var velocityY // the vertical velocity of this GameElement
            : Float
    private val soundId // the sound associated with this GameElement
            : Int

    // update GameElement position and check for wall collisions
    open fun update(interval: Double) {
        // update vertical position
        shape!!.offset(0, (velocityY * interval).toInt())

        // if this GameElement collides with the wall, reverse direction
        if (shape!!.top < 0 && velocityY < 0 ||
                shape!!.bottom > view!!.getScreenHeight() && velocityY > 0) velocityY *= -1f // reverse this GameElement's velocity
    }

    // draws this GameElement on the given Canvas
    open fun draw(canvas: Canvas?) {
        canvas!!.drawRect(shape!!, paint!!)
    }

    // plays the sound that corresponds to this type of GameElement
    fun playSound() {
        view!!.playSound(soundId)
    }

    // public constructor
    init {
        paint!!.setColor(color)
        shape = Rect(x, y, x + width, y + length) // set bounds
        this.soundId = soundId
        this.velocityY = velocityY
    }
}