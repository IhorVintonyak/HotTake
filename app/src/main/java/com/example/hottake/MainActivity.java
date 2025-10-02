package com.example.hottake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView mainWindowText;
    private TextView mainWindowNumber;
    private TextView badNumber;
    private TextView goodNumber;
    private TextView skipNumber;
    private TextView forReworkNumber;

    public int iterator;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Link fields with variables
        mainWindowText = findViewById( R.id.mainWindowText );
        mainWindowNumber = findViewById( R.id.mainWindowNumber );

        badNumber = findViewById( R.id.badNumber );
        goodNumber = findViewById( R.id.goodNumber );
        skipNumber = findViewById( R.id.skipNumber );
        forReworkNumber = findViewById( R.id.forReworkNumber );

//        try {
//            String[] separated = getData( badNumber ).split("/");
//            badNumber.setText( separated[0] );
//            goodNumber.setText( separated[1] );
//            skipNumber.setText( separated[2] );
//            forReworkNumber.setText( separated[3] );
//        } catch (IOException e) {
//            throw new RuntimeException( e );
//        }
        //        Toast.makeText(this, separated[0], Toast.LENGTH_SHORT).show();
        //        TextView[] outputNumbers = {badNumber, goodNumber, skipNumber, forReworkNumber};


        //Create a cards for database
        Card prima = new Card( 1, "La cultura del \"self-care\" è spesso solo consumismo travestito", 0, 0, 0, 1 );
        Card seconda = new Card( 2, "Il bowling è meglio del calcio", 0, 0, 1, 0 );
        Card terza = new Card( 3, "L'acqua ha un sapore diverso in ogni posto", 0, 0, 1, 1 );
        Card quarta = new Card( 4, "Bisogna mettere prima i cereali, non il latte", 0, 1, 0, 0 );
        Card quinta = new Card( 5, "Indossare i calzini con i sandali va benissimo", 0, 1, 0, 1 );
        Card sesta = new Card( 6, "Gli atleti professionisti sono pessimi esempi", 0, 1, 1, 0 );
        Card settima = new Card( 7, "Il privilegio della bellezza è reale", 0, 1, 1, 1 );
        Card ottava = new Card( 8, "*Friends* non è stato uno show bello", 1, 0, 0, 0 );
        Card nona = new Card( 9, "I musical sono belli", 1, 0, 0, 1 );
        Card decima = new Card( 10, "L'arredamento minimalista fa sembrare le case dei modellini di IKEA", 1, 0, 1, 0 );

        Card[] cards = {prima, seconda, terza, quarta, quinta, sesta, settima, ottava, nona, decima};
        //Start with first card
        Deck deck = new Deck( cards );

        runCard( deck.getCurrentCard() );


        // Swipe actions
        mainWindowText.setOnTouchListener( new OnSwipeTouchListener( this ) {
            @Override
            public void onSwipeRight() {
                deck.getCurrentCard().incrementVoteGood();
                if (deck.incrementIsPossible()) {
                    deck.incrementIterator();
                } else {
                    deck.toFirstCard();
                }
                runCard(deck.getCurrentCard() );
            }

            @Override
            public void onSwipeLeft() {
                deck.getCurrentCard().incrementVoteBad();
                if (deck.incrementIsPossible()) {
                    deck.incrementIterator();
                } else {
                    deck.toFirstCard();
                }
                runCard(deck.getCurrentCard() );
            }

            @Override
            public void onSwipeTop() {
                deck.getCurrentCard().incrementVoteSkip();
                if (deck.incrementIsPossible()) {
                    deck.incrementIterator();
                } else {
                    deck.toFirstCard();
                }
                runCard(deck.getCurrentCard() );
            }

            @Override
            public void onSwipeBottom() {
                deck.getCurrentCard().incrementVoteForRework();
                if (deck.incrementIsPossible()) {
                    deck.incrementIterator();
                } else {
                    deck.toFirstCard();
                }
                runCard(deck.getCurrentCard() );
            }
        } );
    }

    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }
    public void runCard(Card card) {
        //Set value of object to fields on display
        mainWindowText.setText( card.getText() );
        mainWindowNumber.setText( String.valueOf( card.getNumber() ) );
        badNumber.setText( String.valueOf( card.getNumberVoteBad() ) );
        goodNumber.setText( String.valueOf( card.getNumberVoteGood() ) );
        skipNumber.setText( String.valueOf( card.getNumberVoteSkip() ) );
        forReworkNumber.setText( String.valueOf( card.getNumberVoteForRework() ) );
    }



    public void saveData(View v) {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }

}
//forReworkNumber.setText(String.valueOf(Integer.parseInt(forReworkNumber.getText().toString())+1));
