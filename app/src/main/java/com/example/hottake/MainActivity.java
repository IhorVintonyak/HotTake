package com.example.hottake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;

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

        try {
            String[] separated = getData( badNumber ).split("/");
//            Toast.makeText(this, separated[0], Toast.LENGTH_SHORT).show();
        badNumber.setText( separated[0] );
        goodNumber.setText( separated[1] );
        skipNumber.setText( separated[2] );
        forReworkNumber.setText( separated[3] );
        } catch (IOException e) {
            throw new RuntimeException( e );
        }

//        TextView[] outputNumbers = {badNumber, goodNumber, skipNumber, forReworkNumber};




        Card prima = new Card(1, "1. La cultura del \"self-care\" è spesso solo consumismo travestito", 0, 0, 0, 1);
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
                if(nextCard(cards,iterator)){
                    increaseOutputNumber(goodNumber);
                    iterator++;
                };
            }

            @Override
            public void onSwipeLeft() {
                if(nextCard(cards,iterator)){
                    increaseOutputNumber(badNumber);
                    iterator++;
                };
            }

            @Override
            public void onSwipeTop() {
                if(nextCard(cards,iterator)){
                    increaseOutputNumber(skipNumber);
                    iterator++;
                };
            }

            @Override
            public void onSwipeBottom() {
                if(nextCard(cards,iterator)){
                    increaseOutputNumber(forReworkNumber);
                    iterator++;
                };
            }
        });
    }

    public boolean nextCard(Card[] cards, int iterator){
        if(cards.length <= iterator){
            Toast.makeText(this, "Non ci sono più carte", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            visualizeCard(cards[iterator]);
            return true;
        }
    }

    public void visualizeCard(Card card) {
        mainWindowText.setText(card.getText());
        mainWindowNumber.setText(String.valueOf(card.getNumber()));
    }

    public void increaseOutputNumber(TextView number) {
        int currentNumber = Integer.parseInt(number.getText().toString());
        currentNumber++;
        number.setText(String.valueOf(currentNumber));
    }


//    public void updateData(View view) {}


    public void saveData(View view) throws IOException {
        FileOutputStream fileOutput = openFileOutput("data.txt", MODE_PRIVATE);
        fileOutput.write((badNumber.getText().toString()+"/"+goodNumber.getText().toString()+"/"+skipNumber.getText().toString()+"/"+forReworkNumber.getText().toString()).getBytes());
        fileOutput.close();
        Toast.makeText(this, "Salvato", Toast.LENGTH_SHORT).show();
        badNumber.setText("0");
        goodNumber.setText("0");
        skipNumber.setText("0");
        forReworkNumber.setText("0");
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