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
    600 g schabu bez ko??ci
    s??l i pieprz
    do namoczenia: mleko
    do obtoczenia: 2 ??y??ki m??ki, 2 jajka, 5 ??y??ek bu??ki tartej
    do sma??enia: 6 ??y??ek mas??a klarowanego lub smalcu
    
    """.trimIndent())
        preparingList!!.add(
                """
                    
                    Ostrym no??em odci???? bia???? otoczk?? z ??y??ki po zewn??trznej cz????ci mi??sa. Pokroi?? na 4 plastry. Po??o??y?? na desce i dok??adnie rozt??uc na cieniutkie filety (mog?? wyj???? du??e, wielko??ci p???? talerza). Do rozbicia mi??sa najlepiej u??y?? specjalnego t??uczka z metalowym obiciem z wyt??oczon?? krateczk??.
                    Filety namoczy?? w mleku z dodatkiem soli i pieprzu (ewentualnie z dodatkiem kilku plastr??w cebuli) przez ok. 2 godziny lub d??u??ej je??li mamy czas (mo??na te?? zostawi?? do namoczenia na noc).
                    Wyj???? filety z mleka i osuszy?? je papierowymi r??cznikami. Doprawi?? sol?? (niezbyt du??o, bo zalewa z mleka by??a ju?? solona) i pieprzem, obtoczy?? w m??ce, nast??pnie w roztrzepanym jajku, a na koniec w bu??ce tartej.
                    Na patelni rozgrza?? klarowane mas??o (ok. 1/2 cm warstwa) lub smalec. Sma??y?? partiami po 2 kotlety, na wi??kszym ogniu, po 2 minuty z ka??dej strony. Nast??pnie zmniejszy?? ogie?? i sma??y?? jeszcze po ok. 3 minuty z ka??dej strony. Przetrze?? patelni?? papierowym r??cznikiem i powt??rzy?? z kolejn?? parti??, na ??wie??ym t??uszczu.
                    Usma??one schabowe ods??czy?? z t??uszczu na papierowym r??czniku i podawa?? z ziemniakami i kapust?? lub mizeri??.
                    
                    
                    """.trimIndent())
        ingredientsList.add("""
    
    500 g ??opatki wieprzowej
    p???? czerstwej kajzerki
    1 ??rednie lub ma??e jajko
    1 ??rednia cebula - 180 g
    1 z??bek czosnku
    2 ??y??ki bu??ki tartej
    przyprawy: p??aska ??y??ka majeranku, p??aska ??y??eczka soli, p???? p??askiej ??y??eczki pieprzu
    do sma??enia: 2 ??y??ki bu??ki tartej, 3 ??y??ki smalcu g??siego
    
    """.trimIndent())
        preparingList.add(
                """
                    
                    Bu??k?? zala?? mlekiem lub wod??, odstawi?? do namoczenia na oko??o 10 - 15 minut. 
                    Do wi??kszej miski w??o??y?? zmielone mi??so, start?? na drobnej tarce cebul??, jajko, s??l i pieprz oraz odci??ni??t?? bu??k??, wszystko dobrze wymiesza??.
                    W trakcie wyrabiania mi??sa nale??y dodawa?? po troszku zimnej wody i wyrabia?? tak d??ugo a?? mi??so wch??onie wod?? i nie b??dzie przywiera?? do d??oni. Im d??u??ej wyrabiamy, tym lepsze kotlety. Masa mi??sna mo??e wydawa?? si?? do???? lu??na, ale dzi??ki temu kotlety b??d?? delikatniejsze, mniej zbite i twarde.
                    Uformowa?? pod??u??ne kotlety, obtoczy?? w bu??ce tartej i k??a???? na dobrze rozgrzany olej na patelni. Po obsma??eniu z dw??ch stron prze??o??y?? kotlety do garnka lub naczynia ??aroodpornego (bez przykrycia) i wstawi?? do rozgrzanego do 150 stopni C piekarnika, na oko??o 15 minut. Unikniemy d??ugiego sma??enia, a kotlety b??d?? w ??rodku idealnie mi??kkie.
                    
                    
                    """.trimIndent())
    }
}