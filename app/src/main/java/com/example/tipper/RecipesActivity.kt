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
import java.util.ArrayList

class RecipesActivity : AppCompatActivity() {
    private var ingredients: TextView? = null
    private var preparing: TextView? = null
    private var nextBtn: Button? = null
    private var currentRecipeIndex = 0
    private val ingredientsList: MutableList<String?>? = ArrayList()
    private val preparingList: MutableList<String?>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_page)
        FullFillRecipeLists()
        ingredients = findViewById<View?>(R.id._recipeRecipeIngredients) as TextView
        preparing = findViewById<View?>(R.id._recipeRecipePreparing) as TextView
        nextBtn = findViewById<View?>(R.id._recipeNext) as Button
        ingredients!!.setText(ingredientsList!!.get(currentRecipeIndex))
        preparing!!.setText(preparingList!!.get(currentRecipeIndex))
        nextBtn!!.setOnClickListener(View.OnClickListener {
            currentRecipeIndex++
            if (currentRecipeIndex >= ingredientsList.size) {
                currentRecipeIndex = 0
            }
            ingredients!!.setText(ingredientsList.get(currentRecipeIndex))
            preparing!!.setText(preparingList.get(currentRecipeIndex))
        })
    }

    private fun FullFillRecipeLists() {
        ingredientsList!!.add("""
    600 g schabu bez kości
    sól i pieprz
    do namoczenia: mleko
    do obtoczenia: 2 łyżki mąki, 2 jajka, 5 łyżek bułki tartej
    do smażenia: 6 łyżek masła klarowanego lub smalcu
    
    """.trimIndent())
        preparingList!!.add(
                """
                    
                    Ostrym nożem odciąć białą otoczkę z żyłki po zewnętrznej części mięsa. Pokroić na 4 plastry. Położyć na desce i dokładnie roztłuc na cieniutkie filety (mogą wyjść duże, wielkości pół talerza). Do rozbicia mięsa najlepiej użyć specjalnego tłuczka z metalowym obiciem z wytłoczoną krateczką.
                    Filety namoczyć w mleku z dodatkiem soli i pieprzu (ewentualnie z dodatkiem kilku plastrów cebuli) przez ok. 2 godziny lub dłużej jeśli mamy czas (można też zostawić do namoczenia na noc).
                    Wyjąć filety z mleka i osuszyć je papierowymi ręcznikami. Doprawić solą (niezbyt dużo, bo zalewa z mleka była już solona) i pieprzem, obtoczyć w mące, następnie w roztrzepanym jajku, a na koniec w bułce tartej.
                    Na patelni rozgrzać klarowane masło (ok. 1/2 cm warstwa) lub smalec. Smażyć partiami po 2 kotlety, na większym ogniu, po 2 minuty z każdej strony. Następnie zmniejszyć ogień i smażyć jeszcze po ok. 3 minuty z każdej strony. Przetrzeć patelnię papierowym ręcznikiem i powtórzyć z kolejną partią, na świeżym tłuszczu.
                    Usmażone schabowe odsączyć z tłuszczu na papierowym ręczniku i podawać z ziemniakami i kapustą lub mizerią.
                    
                    
                    """.trimIndent())
        ingredientsList.add("""
    
    500 g łopatki wieprzowej
    pół czerstwej kajzerki
    1 średnie lub małe jajko
    1 średnia cebula - 180 g
    1 ząbek czosnku
    2 łyżki bułki tartej
    przyprawy: płaska łyżka majeranku, płaska łyżeczka soli, pół płaskiej łyżeczki pieprzu
    do smażenia: 2 łyżki bułki tartej, 3 łyżki smalcu gęsiego
    
    """.trimIndent())
        preparingList.add(
                """
                    
                    Bułkę zalać mlekiem lub wodą, odstawić do namoczenia na około 10 - 15 minut. 
                    Do większej miski włożyć zmielone mięso, startą na drobnej tarce cebulę, jajko, sól i pieprz oraz odciśniętą bułkę, wszystko dobrze wymieszać.
                    W trakcie wyrabiania mięsa należy dodawać po troszku zimnej wody i wyrabiać tak długo aż mięso wchłonie wodę i nie będzie przywierać do dłoni. Im dłużej wyrabiamy, tym lepsze kotlety. Masa mięsna może wydawać się dość luźna, ale dzięki temu kotlety będą delikatniejsze, mniej zbite i twarde.
                    Uformować podłużne kotlety, obtoczyć w bułce tartej i kłaść na dobrze rozgrzany olej na patelni. Po obsmażeniu z dwóch stron przełożyć kotlety do garnka lub naczynia żaroodpornego (bez przykrycia) i wstawić do rozgrzanego do 150 stopni C piekarnika, na około 15 minut. Unikniemy długiego smażenia, a kotlety będą w środku idealnie miękkie.
                    
                    
                    """.trimIndent())
    }
}