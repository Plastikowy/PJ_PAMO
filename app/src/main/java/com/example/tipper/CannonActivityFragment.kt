// MainActivityFragment.java
// MainActivityFragment creates and manages a CannonView
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
import androidx.fragment.app.Fragment

class CannonActivityFragment : Fragment() {
    private var cannonView // custom view to display the game
            : CannonView? = null

    // called when Fragment's view needs to be created
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // inflate the fragment_main.xml layout
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)

        // get a reference to the CannonView
        cannonView = view.findViewById<View?>(R.id.cannonView) as CannonView
        return view
    }

    // set up volume control once Activity is created
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // allow volume buttons to set game volume
        activity!!.setVolumeControlStream(AudioManager.STREAM_MUSIC)
    }

    // when MainActivity is paused, terminate the game
    override fun onPause() {
        super.onPause()
        cannonView!!.stopGame() // terminates the game
    }

    // when MainActivity is paused, MainActivityFragment releases resources
    override fun onDestroy() {
        super.onDestroy()
        cannonView!!.releaseResources()
    }
}