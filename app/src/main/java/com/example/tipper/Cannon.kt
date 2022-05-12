// Cannon.java
// Represents Cannon and fires the Cannonball
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
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
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

class Cannon(// view containing the Cannon
        private val view: CannonView?, // Cannon base's radius
        private val baseRadius: Int, // Cannon barrel's length
        private val barrelLength: Int,
        barrelWidth: Int) {
    private val barrelEnd: Point? = Point() // endpoint of Cannon's barrel
    private var barrelAngle // angle of the Cannon's barrel
            = 0.0
    private var cannonball // the Cannon's Cannonball
            : Cannonball? = null
    private val paint: Paint? = Paint() // Paint used to draw the cannon

    // aligns the Cannon's barrel to the given angle
    fun align(barrelAngle: Double) {
        this.barrelAngle = barrelAngle
        barrelEnd!!.x = (barrelLength * Math.sin(barrelAngle)).toInt()
        barrelEnd!!.y = (-barrelLength * Math.cos(barrelAngle)).toInt() +
                view!!.getScreenHeight() / 2
    }

    // creates and fires Cannonball in the direction Cannon points
    fun fireCannonball() {
        // calculate the Cannonball velocity's x component
        val velocityX = (CannonView.Companion.CANNONBALL_SPEED_PERCENT *
                view!!.getScreenWidth() * Math.sin(barrelAngle)).toInt()

        // calculate the Cannonball velocity's y component
        val velocityY = (CannonView.Companion.CANNONBALL_SPEED_PERCENT *
                view.getScreenWidth() * -Math.cos(barrelAngle)).toInt()

        // calculate the Cannonball's radius
        val radius = (view.getScreenHeight() *
                CannonView.Companion.CANNONBALL_RADIUS_PERCENT).toInt()

        // construct Cannonball and position it in the Cannon
        cannonball = Cannonball(view, Color.BLACK,
                CannonView.Companion.CANNON_SOUND_ID, -radius,
                view.getScreenHeight() / 2 - radius, radius, velocityX.toFloat(),
                velocityY.toFloat())
        cannonball!!.playSound() // play fire Cannonball sound
    }

    // draws the Cannon on the Canvas
    fun draw(canvas: Canvas?) {
        // draw cannon barrel
        if (paint != null) {
            canvas!!.drawLine(0f, (view!!.getScreenHeight() / 2).toFloat(), barrelEnd!!.x.toFloat(),
                    barrelEnd!!.y.toFloat(), paint)
        }

        // draw cannon base
        if (paint != null) {
            canvas!!.drawCircle(0f, (view!!.getScreenHeight() as Int / 2).toFloat(),
                    baseRadius.toFloat(), paint)
        }
    }

    // returns the Cannonball that this Cannon fired
    fun getCannonball(): Cannonball? {
        return cannonball
    }

    // removes the Cannonball from the game
    fun removeCannonball() {
        cannonball = null
    }

    // constructor
    init {
        paint!!.setStrokeWidth(barrelWidth.toFloat()) // set width of barrel
        paint!!.setColor(Color.BLACK) // Cannon's color is Black
        align(Math.PI / 2) // Cannon barrel facing straight right
    }
}