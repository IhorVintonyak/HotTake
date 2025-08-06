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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainWindowText = findViewById(R.id.mainWindowText);
        mainWindowNumber = findViewById(R.id.mainWindowNumber);

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




        Card prima = new Card(1, "La cultura del \"self-care\" è spesso solo consumismo travestito", 0, 0, 0, 1);
        Card seconda = new Card(2, "Il bowling è meglio del calcio", 0, 0, 1, 0);
        Card terza = new Card(3, "L'acqua ha un sapore diverso in ogni posto", 0, 0, 1, 1);
        Card quarta = new Card(4, "Bisogna mettere prima i cereali, non il latte", 0, 1, 0, 0);
        Card quinta = new Card(5, "Indossare i calzini con i sandali va benissimo", 0, 1, 0, 1);
        Card sesta = new Card(6, "Gli atleti professionisti sono pessimi esempi", 0, 1, 1, 0);
        Card settima = new Card(7, "Il privilegio della bellezza è reale", 0, 1, 1, 1);
        Card ottava = new Card(8, "*Friends* non è stato uno show bello", 1, 0, 0, 0);
        Card nona = new Card(9, "I musical sono belli", 1, 0, 0, 1);
        Card decima = new Card(10, "L'arredamento minimalista fa sembrare le case dei modellini di IKEA", 1, 0, 1, 0);

        Card[] cards = {prima, seconda, terza, quarta, quinta, sesta, settima, ottava, nona, decima};
        iterator = 0;
        nextCard(cards, iterator);


        mainWindowText.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                cards[iterator].incrementVoteGood();
                iterator++;
                nextCard(cards,iterator);
            }

            @Override
            public void onSwipeLeft() {
                cards[iterator].incrementVoteBad();
                iterator++;
                nextCard(cards,iterator);
            }

            @Override
            public void onSwipeTop() {
                cards[iterator].incrementVoteSkip();
                iterator++;
                nextCard(cards,iterator);
            }

            @Override
            public void onSwipeBottom() {
                forReworkNumber.setText(String.valueOf(Integer.parseInt(forReworkNumber.getText().toString())+1));
                // Save the card
                cards[iterator].incrementVoteForRework();
                iterator++;
                nextCard(cards,iterator);
            }
        });
    }

    public void nextCard(Card[] cards, int iterator){
        if(cards.length <= iterator){
            Toast.makeText(this, "Non ci sono più carte", Toast.LENGTH_SHORT).show();
        }else{
            visualizeCard(cards[iterator]);
        }
    }



    public void visualizeCard(Card card) {
        mainWindowText.setText(card.getText());
        mainWindowNumber.setText(String.valueOf(card.getNumber()));
        badNumber.setText(String.valueOf(card.getNumberVoteBad()));
        goodNumber.setText(String.valueOf(card.getNumberVoteGood()));
        skipNumber.setText(String.valueOf(card.getNumberVoteSkip()));
        forReworkNumber.setText(String.valueOf(card.getNumberVoteForRework()));
    }


//    public void updateData(View view) {}


    public void saveCard(Card card) {
        card.setNumberVoteBad( Integer.parseInt( badNumber.getText().toString()));
        card.setNumberVoteGood( Integer.parseInt( goodNumber.getText().toString()));
        card.setNumberVoteSkip( Integer.parseInt( skipNumber.getText().toString()));
        card.setNumberVoteForRework( Integer.parseInt( forReworkNumber.getText().toString()));
    }

    public void createDatabaseTxt(Card[] inputCards) throws IOException {
        // Crea il file txt con i dati per ogetti di tipo Card

        FileOutputStream fileOutput = openFileOutput("cardsData.txt", MODE_PRIVATE);
        for(int i=0; i<inputCards.length; i++) {
            fileOutput.write(inputCards[i].toString().getBytes());
            fileOutput.close();
        }
        Toast.makeText(this, "Salvato", Toast.LENGTH_SHORT).show();
    }

    public String getDataFromTxt() throws IOException {
        FileInputStream fileInput = openFileInput("cardsData.txt");
        InputStreamReader reader = new InputStreamReader(fileInput);
        BufferedReader bReader = new BufferedReader(reader);

        String lines = "";
        StringBuilder sBuffer = new StringBuilder();
        while ((lines = bReader.readLine()) != null){
            sBuffer.append(lines).append("\n");
        }

        Toast.makeText(this, sBuffer.toString(), Toast.LENGTH_SHORT).show();
        return sBuffer.toString();
    }



    public void saveData(View view) throws IOException {
        //Adesso azzera l'iteratore
        iterator=0;
//        createDatabaseTxt(cards);


//        FileOutputStream fileOutput = openFileOutput("data.txt", MODE_PRIVATE);
//        fileOutput.write((badNumber.getText().toString()+"/"+goodNumber.getText().toString()+"/"+skipNumber.getText().toString()+"/"+forReworkNumber.getText().toString()).getBytes());
//        fileOutput.close();
//        Toast.makeText(this, "Salvato", Toast.LENGTH_SHORT).show();
//        badNumber.setText("0");
//        goodNumber.setText("0");
//        skipNumber.setText("0");
//        forReworkNumber.setText("0");
    }

    public String getData(View view) throws IOException {
        FileInputStream fileInput = openFileInput("data.txt");
        InputStreamReader reader = new InputStreamReader(fileInput);
        BufferedReader bReader = new BufferedReader(reader);

        String lines = "";
        StringBuilder sBuffer = new StringBuilder();
        while ((lines = bReader.readLine()) != null){
            sBuffer.append(lines).append("\n");
        }

        Toast.makeText(this, sBuffer.toString(), Toast.LENGTH_SHORT).show();
        return sBuffer.toString();
    }
}