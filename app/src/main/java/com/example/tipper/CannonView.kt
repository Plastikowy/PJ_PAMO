// CannonView.java
// Displays and controls the Cannon Game
package com.example.tipper

import org.junit.runner.RunWith
import com.example.tipper.CannonView
import com.example.tipper.Cannonball
import com.example.tipper.GameElement
import android.view.SurfaceView
import android.view.SurfaceHolder
import com.example.tipper.CannonView.CannonThread
import android.app.Activity
import android.content.Context
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
import android.util.AttributeSet
import android.util.Log
import java.util.*

class CannonView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs), SurfaceHolder.Callback {
    private var cannonThread // controls the game loop
            : CannonThread? = null
    private val activity // to display Game Over dialog in GUI thread
            : Activity?
    private val dialogIsDisplayed = false

    // game objects
    private var cannon: Cannon? = null
    private var blocker: Blocker? = null
    private var targets: ArrayList<Target?>? = null

    // dimension variables
    var screenWidth = 0
    var screenHeight = 0

    // variables for the game loop and tracking statistics
    private var gameOver // is the game over?
            = false
    private var timeLeft // time remaining in seconds
            = 0.0
    private var shotsFired // shots the user has fired
            = 0
    private var totalElapsedTime // elapsed seconds
            = 0.0
    private var soundPool // plays sound effects
            : SoundPool?
    private val soundMap // maps IDs to SoundPool
            : SparseIntArray?

    // Paint variables used when drawing each item on the screen
    private val textPaint // Paint used to draw text
            : Paint?
    private val backgroundPaint // Paint used to clear the drawing area
            : Paint?

    // called when the size of the SurfaceView changes,
    // such as when it's first added to the View hierarchy
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w // store CannonView's width
        screenHeight = h // store CannonView's height

        // configure text properties
        textPaint!!.setTextSize((TEXT_SIZE_PERCENT * screenHeight).toFloat())
        textPaint!!.setAntiAlias(true) // smoothes the text
    }

    // get width of the game screen
    @JvmName("getScreenWidth1")
    fun getScreenWidth(): Int {
        return screenWidth
    }

    // get height of the game screen
    @JvmName("getScreenHeight1")
    fun getScreenHeight(): Int {
        return screenHeight
    }

    // plays a sound with the given soundId in soundMap
    fun playSound(soundId: Int) {
        soundPool!!.play(soundMap!!.get(soundId), 1f, 1f, 1, 0, 1f)
    }

    // reset all the screen elements and start a new game
    fun newGame() {
        // construct a new Cannon
        cannon = Cannon(this,
                (CANNON_BASE_RADIUS_PERCENT * screenHeight).toInt(),
                (CANNON_BARREL_LENGTH_PERCENT * screenWidth).toInt(),
                (CANNON_BARREL_WIDTH_PERCENT * screenHeight).toInt())
        val random = Random() // for determining random velocities
        targets = ArrayList() // construct a new Target list

        // initialize targetX for the first Target from the left
        var targetX = (TARGET_FIRST_X_PERCENT * screenWidth).toInt()

        // calculate Y coordinate of Targets
        val targetY = ((0.5 - TARGET_LENGTH_PERCENT / 2) *
                screenHeight).toInt()

        // add TARGET_PIECES Targets to the Target list
        var n = 0
        while (n < TARGET_PIECES) {


            // determine a random velocity between min and max values
            // for Target n
            var velocity = screenHeight * (random.nextDouble() *
                    (TARGET_MAX_SPEED_PERCENT - TARGET_MIN_SPEED_PERCENT) +
                    TARGET_MIN_SPEED_PERCENT)

            // alternate Target colors between dark and light
            val color = if (n % 2 == 0) resources.getColor(R.color.dark,
                    context.theme) else resources.getColor(R.color.light,
                    context.theme)
            velocity *= -1.0 // reverse the initial velocity for next Target

            // create and add a new Target to the Target list
            targets!!.add(Target(this, color, HIT_REWARD, targetX, targetY,
                    (TARGET_WIDTH_PERCENT * screenWidth).toInt(),
                    (TARGET_LENGTH_PERCENT * screenHeight).toInt(),
                    velocity.toFloat()))

            // increase the x coordinate to position the next Target more
            // to the right
            targetX += ((TARGET_WIDTH_PERCENT + TARGET_SPACING_PERCENT) *
                    screenWidth).toInt()
            n++
        }

        // create a new Blocker
        blocker = Blocker(this, Color.BLACK, MISS_PENALTY,
                (BLOCKER_X_PERCENT * screenWidth).toInt(),
                ((0.5 - BLOCKER_LENGTH_PERCENT / 2) * screenHeight).toInt(),
                (BLOCKER_WIDTH_PERCENT * screenWidth).toInt(),
                (BLOCKER_LENGTH_PERCENT * screenHeight).toInt(),
                (BLOCKER_SPEED_PERCENT * screenHeight).toFloat())
        timeLeft = 120.0 // start the countdown at 10 seconds
        shotsFired = 0 // set the initial number of shots fired
        totalElapsedTime = 0.0 // set the time elapsed to zero
        if (gameOver) { // start a new game after the last game ended
            gameOver = false // the game is not over
            cannonThread = CannonThread(holder) // create thread
            cannonThread!!.start() // start the game loop thread
        }
        hideSystemBars()
    }

    // called repeatedly by the CannonThread to update game elements
    private fun updatePositions(elapsedTimeMS: Double) {
        val interval = elapsedTimeMS / 1000.0 // convert to seconds

        // update cannonball's position if it is on the screen
        if (cannon!!.getCannonball() != null) cannon!!.getCannonball()!!.update(interval)
        blocker!!.update(interval) // update the blocker's position
        for (target in targets!!) target!!.update(interval) // update the target's position
        timeLeft -= interval // subtract from time left

        // if the timer reached zero
        if (timeLeft <= 0) {
            timeLeft = 0.0
            gameOver = true // the game is over
            cannonThread!!.setRunning(false) // terminate thread
            //showGameOverDialog(R.string.lose); // show the losing dialog
        }

        // if all pieces have been hit
        if (targets!!.isEmpty()) {
            cannonThread!!.setRunning(false) // terminate thread
            //showGameOverDialog(R.string.win); // show winning dialog
            gameOver = true
        }
    }

    // aligns the barrel and fires a Cannonball if a Cannonball is not
    // already on the screen
    fun alignAndFireCannonball(event: MotionEvent?) {
        // get the location of the touch in this view
        val touchPoint = Point(event!!.getX().toInt(),
                event!!.getY().toInt())

        // compute the touch's distance from center of the screen
        // on the y-axis
        val centerMinusY = (screenHeight / 2 - touchPoint.y).toDouble()
        var angle = 0.0 // initialize angle to 0

        // calculate the angle the barrel makes with the horizontal
        angle = Math.atan2(touchPoint.x.toDouble(), centerMinusY)

        // point the barrel at the point where the screen was touched
        cannon!!.align(angle)

        // fire Cannonball if there is not already a Cannonball on screen
        if (cannon!!.getCannonball() == null ||
                !cannon!!.getCannonball()!!.isOnScreen()) {
            cannon!!.fireCannonball()
            ++shotsFired
        }
    }

    // draws the game to the given Canvas
    fun drawGameElements(canvas: Canvas?) {
        // clear the background
        if (backgroundPaint != null) {
            canvas!!.drawRect(0f, 0f, canvas.getWidth().toFloat(), canvas.getHeight().toFloat(),
                    backgroundPaint)
        }

        // display time remaining
        if (textPaint != null) {
            canvas!!.drawText(resources.getString(
                    R.string.time_remaining_format, timeLeft), 50f, 100f, textPaint)
        }
        cannon!!.draw(canvas) // draw the cannon

        // draw the GameElements
        if (cannon!!.getCannonball() != null &&
                cannon!!.getCannonball()!!.isOnScreen()) cannon!!.getCannonball()!!.draw(canvas)
        blocker!!.draw(canvas) // draw the blocker

        // draw all of the Targets
        for (target in targets!!) target!!.draw(canvas)
    }

    // checks if the ball collides with the Blocker or any of the Targets
    // and handles the collisions
    fun testForCollisions() {
        // remove any of the targets that the Cannonball
        // collides with
        if (cannon!!.getCannonball() != null &&
                cannon!!.getCannonball()!!.isOnScreen()) {
            var n = 0
            while (n < targets!!.size) {
                if (cannon!!.getCannonball()!!.collidesWith(targets!!.get(n))) {
                    targets!!.get(n)!!.playSound() // play Target hit sound

                    // add hit rewards time to remaining time
                    timeLeft += targets!!.get(n)!!.getHitReward().toDouble()
                    cannon!!.removeCannonball() // remove Cannonball from game
                    targets!!.removeAt(n) // remove the Target that was hit
                    --n // ensures that we don't skip testing new target n
                    break
                }
                n++
            }
        } else { // remove the Cannonball if it should not beon the screen
            cannon!!.removeCannonball()
        }

        // check if ball collides with blocker
        if (cannon!!.getCannonball() != null &&
                cannon!!.getCannonball()!!.collidesWith(blocker)) {
            blocker!!.playSound() // play Blocker hit sound

            // reverse ball direction
            cannon!!.getCannonball()!!.reverseVelocityX()

            // deduct blocker's miss penalty from remaining time
            timeLeft -= blocker!!.getMissPenalty().toDouble()
        }
    }

    // stops the game: called by CannonGameFragment's onPause method
    fun stopGame() {
        if (cannonThread != null) cannonThread!!.setRunning(false) // tell thread to terminate
    }

    // release resources: called by CannonGame's onDestroy method
    fun releaseResources() {
        soundPool!!.release() // release all resources used by the SoundPool
        soundPool = null
    }

    // called when surface changes size
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    // called when surface is first created
    override fun surfaceCreated(holder: SurfaceHolder) {
        if (!dialogIsDisplayed) {
            newGame() // set up and start a new game
            cannonThread = CannonThread(holder) // create thread
            cannonThread!!.setRunning(true) // start game running
            cannonThread!!.start() // start the game loop thread
        }
    }

    // called when the surface is destroyed
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // ensure that thread terminates properly
        var retry = true
        cannonThread!!.setRunning(false) // terminate cannonThread
        while (retry) {
            try {
                cannonThread!!.join() // wait for cannonThread to finish
                retry = false
            } catch (e: InterruptedException) {
                Log.e(TAG, "Thread interrupted", e)
            }
        }
    }

    // called when the user touches the screen in this activity
    override fun onTouchEvent(e: MotionEvent?): Boolean {
        // get int representing the type of action which caused this event
        val action = e!!.getAction()

        // the user touched the screen or dragged along the screen
        if (action == MotionEvent.ACTION_DOWN ||
                action == MotionEvent.ACTION_MOVE) {
            // fire the cannonball toward the touch point
            alignAndFireCannonball(e)
        }
        return true
    }

    // Thread subclass to control the game loop
    inner class CannonThread(  // for manipulating canvas
            private val surfaceHolder: SurfaceHolder?) : Thread() {
        private var threadIsRunning = true // running by default

        // changes running state
        fun setRunning(running: Boolean) {
            threadIsRunning = running
        }

        // controls the game loop
        override fun run() {
            var canvas: Canvas? = null // used for drawing
            var previousFrameTime = System.currentTimeMillis()
            while (threadIsRunning) {
                try {
                    // get Canvas for exclusive drawing from this thread
                    canvas = surfaceHolder!!.lockCanvas(null)

                    // lock the surfaceHolder for drawing
                    synchronized(surfaceHolder) {
                        val currentTime = System.currentTimeMillis()
                        val elapsedTimeMS = (currentTime - previousFrameTime).toDouble()
                        totalElapsedTime += elapsedTimeMS / 1000.0
                        updatePositions(elapsedTimeMS) // update game state
                        testForCollisions() // test for GameElement collisions
                        drawGameElements(canvas) // draw using the canvas
                        previousFrameTime = currentTime // update previous time
                    }
                } finally {
                    // display canvas's contents on the CannonView
                    // and enable other threads to use the Canvas
                    if (canvas != null) surfaceHolder!!.unlockCanvasAndPost(canvas)
                }
            }
        }

        // initializes the surface holder
        init {
            name = "CannonThread"
        }
    }

    // hide system bars and app bar
    private fun hideSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or
                SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                SYSTEM_UI_FLAG_FULLSCREEN or
                SYSTEM_UI_FLAG_IMMERSIVE
    }

    // show system bars and app bar
    private fun showSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or
                SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    companion object {
        private val TAG: String? = "CannonView" // for logging errors

        // constants for game play
        const val MISS_PENALTY = 60 // seconds deducted on a miss
        const val HIT_REWARD = 3 // seconds added on a hit

        // constants for the Cannon
        const val CANNON_BASE_RADIUS_PERCENT = 3.0 / 40
        const val CANNON_BARREL_WIDTH_PERCENT = 3.0 / 40
        const val CANNON_BARREL_LENGTH_PERCENT = 1.0 / 10

        // constants for the Cannonball
        const val CANNONBALL_RADIUS_PERCENT = 3.0 / 80
        const val CANNONBALL_SPEED_PERCENT = 3.0 / 2

        // constants for the Targets
        const val TARGET_WIDTH_PERCENT = 1.0 / 40
        const val TARGET_LENGTH_PERCENT = 3.0 / 20
        const val TARGET_FIRST_X_PERCENT = 3.0 / 5
        const val TARGET_SPACING_PERCENT = 1.0 / 60
        const val TARGET_PIECES = 9.0
        const val TARGET_MIN_SPEED_PERCENT = 2.0 / 4
        const val TARGET_MAX_SPEED_PERCENT = 4.0 / 4

        // constants for the Blocker
        const val BLOCKER_WIDTH_PERCENT = 1.0 / 40
        const val BLOCKER_LENGTH_PERCENT = 1.0 / 4
        const val BLOCKER_X_PERCENT = 1.0 / 2
        const val BLOCKER_SPEED_PERCENT = 1.0 / 4

        // text size 1/18 of screen width
        const val TEXT_SIZE_PERCENT = 1.0 / 36

        // constants and variables for managing sounds
        const val TARGET_SOUND_ID = 0
        const val CANNON_SOUND_ID = 1
        const val BLOCKER_SOUND_ID = 2
    }

    // constructor
    init {
        activity = context as Activity? // store reference to MainActivity

        // register SurfaceHolder.Callback listener
        holder.addCallback(this)

        // configure audio attributes for game audio
        val attrBuilder = AudioAttributes.Builder()
        attrBuilder.setUsage(AudioAttributes.USAGE_GAME)

        // initialize SoundPool to play the app's three sound effects
        val builder = SoundPool.Builder()
        builder.setMaxStreams(1)
        builder.setAudioAttributes(attrBuilder.build())
        soundPool = builder.build()

        // create Map of sounds and pre-load sounds
        soundMap = SparseIntArray(3) // create new SparseIntArray
        soundMap.put(TARGET_SOUND_ID,
                soundPool!!.load(context, R.raw.target_hit, 1))
        soundMap.put(CANNON_SOUND_ID,
                soundPool!!.load(context, R.raw.cannon_fire, 1))
        soundMap.put(BLOCKER_SOUND_ID,
                soundPool!!.load(context, R.raw.blocker_hit, 1))
        textPaint = Paint()
        backgroundPaint = Paint()
        backgroundPaint.color = Color.WHITE
    }
}