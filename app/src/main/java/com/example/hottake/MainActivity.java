package com.example.hottake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private CardDao cardDao;



    private TextView mainWindowText;
    private TextView mainWindowNumber;
    private TextView badNumber;
    private TextView goodNumber;
    private TextView skipNumber;
    private TextView forReworkNumber;

    public int iterator;

    //Create a cards for database
    Card first = new Card( 1, "La cultura del \"self-care\" è spesso solo consumismo travestito", 0, 0, 0, 1 );
    Card second = new Card( 2, "Il bowling è meglio del calcio", 0, 0, 1, 0 );
    Card third = new Card( 3, "L'acqua ha un sapore diverso in ogni posto", 0, 0, 1, 1 );
    Card fourth = new Card( 4, "Bisogna mettere prima i cereali, non il latte", 0, 1, 0, 0 );
    Card fifth = new Card( 5, "Indossare i calzini con i sandali va benissimo", 0, 1, 0, 1 );
    Card sixth = new Card( 6, "Gli atleti professionisti sono pessimi esempi", 0, 1, 1, 0 );
    Card seventh = new Card( 7, "Il privilegio della bellezza è reale", 0, 1, 1, 1 );
    Card eighth = new Card( 8, "*Friends* non è stato uno show bello", 1, 0, 0, 0 );
    Card ninth = new Card( 9, "I musical sono belli", 1, 0, 0, 1 );
    Card tenth = new Card( 10, "L'arredamento minimalista fa sembrare le case dei modellini di IKEA", 1, 0, 1, 0 );

    Card[] cards = {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth};
    Deck deck = new Deck( cards );


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Create database
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "cards_database")
                .allowMainThreadQueries() // Only for test
                .build();

        cardDao = db.cardDao();


        //Card card1 = new Card();
        //cardDao.insert(third);

        //Add element to database
        //ERROR with empty object. With any of deck cards works.
        try {
            Card card1 = new Card();
            cardDao.insert(third);
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: maybe empty object", Toast.LENGTH_LONG).show();
        }

        //Link fields with variables
        mainWindowText = findViewById( R.id.mainWindowText );
        mainWindowNumber = findViewById( R.id.mainWindowNumber );

        badNumber = findViewById( R.id.badNumber );
        goodNumber = findViewById( R.id.goodNumber );
        skipNumber = findViewById( R.id.skipNumber );
        forReworkNumber = findViewById( R.id.forReworkNumber );

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
        Toast.makeText(this, deck.toString(), Toast.LENGTH_LONG).show();

    }

    public void printData(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deck Data");

        ScrollView scrollView = new ScrollView(this);
        TextView textView = new TextView(this);
        textView.setText(deck.toString());
        textView.setPadding(50, 50, 50, 50);
        scrollView.addView(textView);

        builder.setView(scrollView);
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}

//forReworkNumber.setText(String.valueOf(Integer.parseInt(forReworkNumber.getText().toString())+1));
