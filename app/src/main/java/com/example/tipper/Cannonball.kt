// Cannonball.java
// Represents the Cannonball that the Cannon fires
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

class Cannonball     // constructor
(view: CannonView?, color: Int, soundId: Int, x: Int,
 y: Int, radius: Int, private var velocityX: Float, velocityY: Float) : GameElement(view, color, soundId, x, y,
        2 * radius, 2 * radius, velocityY) {
    private var onScreen = true

    // get Cannonball's radius
    private fun getRadius(): Int {
        return (shape!!.right - shape!!.left) / 2
    }

    // test whether Cannonball collides with the given GameElement
    fun collidesWith(element: GameElement?): Boolean {
        return Rect.intersects(shape!!, element!!.shape!!) && velocityX > 0
    }

    // returns true if this Cannonball is on the screen
    fun isOnScreen(): Boolean {
        return onScreen
    }

    // reverses the Cannonball's horizontal velocity
    fun reverseVelocityX() {
        velocityX *= -1f
    }

    // updates the Cannonball's position
    override fun update(interval: Double) {
        super.update(interval) // updates Cannonball's vertical position

        // update horizontal position
        shape!!.offset((velocityX * interval).toInt(), 0)

        // if Cannonball goes off the screen
        if (shape!!.top < 0 || shape!!.left < 0 || shape!!.bottom > view!!.screenHeight || shape!!.right > view!!.screenWidth) onScreen = false // set it to be removed
    }

    // draws the Cannonball on the given canvas
    override fun draw(canvas: Canvas?) {
        paint?.let {
            canvas!!.drawCircle((shape!!.left + getRadius()).toFloat(), (
                shape!!.top + getRadius()).toFloat(), getRadius().toFloat(), it)
        }
    }
}