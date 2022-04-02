package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {

    private TextView ingredients;
    private TextView preparing;
    private Button nextBtn;
    private int currentRecipeIndex = 0;
    private List<String> ingredientsList = new ArrayList<String>();
    private List<String> preparingList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_page);

        FullFillRecipeLists();

        ingredients = (TextView) findViewById(R.id._recipeRecipeIngredients);
        preparing = (TextView) findViewById(R.id._recipeRecipePreparing);
        nextBtn = (Button) findViewById(R.id._recipeNext);

        ingredients.setText(ingredientsList.get(currentRecipeIndex));
        preparing.setText(preparingList.get(currentRecipeIndex));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRecipeIndex++;

                if(currentRecipeIndex >= ingredientsList.size()){
                    currentRecipeIndex = 0;
                }

                ingredients.setText(ingredientsList.get(currentRecipeIndex));
                preparing.setText(preparingList.get(currentRecipeIndex));
            }
        });
    }

    private void FullFillRecipeLists() {
        ingredientsList.add("600 g schabu bez kości\n" +
                "sól i pieprz\n" +
                "do namoczenia: mleko\n" +
                "do obtoczenia: 2 łyżki mąki, 2 jajka, 5 łyżek bułki tartej\n" +
                "do smażenia: 6 łyżek masła klarowanego lub smalcu\n");
        preparingList.add(
                "\n" +
                "Ostrym nożem odciąć białą otoczkę z żyłki po zewnętrznej części mięsa. Pokroić na 4 plastry. Położyć na desce i dokładnie roztłuc na cieniutkie filety (mogą wyjść duże, wielkości pół talerza). Do rozbicia mięsa najlepiej użyć specjalnego tłuczka z metalowym obiciem z wytłoczoną krateczką.\n" +
                "Filety namoczyć w mleku z dodatkiem soli i pieprzu (ewentualnie z dodatkiem kilku plastrów cebuli) przez ok. 2 godziny lub dłużej jeśli mamy czas (można też zostawić do namoczenia na noc).\n" +
                "Wyjąć filety z mleka i osuszyć je papierowymi ręcznikami. Doprawić solą (niezbyt dużo, bo zalewa z mleka była już solona) i pieprzem, obtoczyć w mące, następnie w roztrzepanym jajku, a na koniec w bułce tartej.\n" +
                "Na patelni rozgrzać klarowane masło (ok. 1/2 cm warstwa) lub smalec. Smażyć partiami po 2 kotlety, na większym ogniu, po 2 minuty z każdej strony. Następnie zmniejszyć ogień i smażyć jeszcze po ok. 3 minuty z każdej strony. Przetrzeć patelnię papierowym ręcznikiem i powtórzyć z kolejną partią, na świeżym tłuszczu.\n" +
                "Usmażone schabowe odsączyć z tłuszczu na papierowym ręczniku i podawać z ziemniakami i kapustą lub mizerią.\n" +
                "\n");

        ingredientsList.add("\n" +
                "500 g łopatki wieprzowej\n" +
                "pół czerstwej kajzerki\n" +
                "1 średnie lub małe jajko\n" +
                "1 średnia cebula - 180 g\n" +
                "1 ząbek czosnku\n" +
                "2 łyżki bułki tartej\n" +
                "przyprawy: płaska łyżka majeranku, płaska łyżeczka soli, pół płaskiej łyżeczki pieprzu\n" +
                "do smażenia: 2 łyżki bułki tartej, 3 łyżki smalcu gęsiego\n");

        preparingList.add(
                "\n" +
                "Bułkę zalać mlekiem lub wodą, odstawić do namoczenia na około 10 - 15 minut. \n" +
                "Do większej miski włożyć zmielone mięso, startą na drobnej tarce cebulę, jajko, sól i pieprz oraz odciśniętą bułkę, wszystko dobrze wymieszać.\n" +
                "W trakcie wyrabiania mięsa należy dodawać po troszku zimnej wody i wyrabiać tak długo aż mięso wchłonie wodę i nie będzie przywierać do dłoni. Im dłużej wyrabiamy, tym lepsze kotlety. Masa mięsna może wydawać się dość luźna, ale dzięki temu kotlety będą delikatniejsze, mniej zbite i twarde.\n" +
                "Uformować podłużne kotlety, obtoczyć w bułce tartej i kłaść na dobrze rozgrzany olej na patelni. Po obsmażeniu z dwóch stron przełożyć kotlety do garnka lub naczynia żaroodpornego (bez przykrycia) i wstawić do rozgrzanego do 150 stopni C piekarnika, na około 15 minut. Unikniemy długiego smażenia, a kotlety będą w środku idealnie miękkie.\n" +
                "\n");
    }
}