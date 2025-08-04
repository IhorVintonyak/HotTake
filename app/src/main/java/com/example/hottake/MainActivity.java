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
    public int maxLength;

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




        Card prima = new Card(1, "Pizza non è buona", 5, 1, 4, 2);
        Card seconda = new Card(2, "Pure fa schifo", 2, 4, 2, 6);
        Card terza = new Card(3, "Non si può fire salve tra i amici", 0, 10, 1, 1);

        Card[] cards = {prima, seconda, terza};
        iterator = 0;
        nextCard(cards, iterator);



        mainWindowText.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                increaseOutputNumber(goodNumber);
                iterator++;
                visualizeCard(cards[iterator]);
            }

            @Override
            public void onSwipeLeft() {
                increaseOutputNumber(badNumber);
                iterator++;
                visualizeCard(cards[iterator]);
            }

            @Override
            public void onSwipeTop() {
                increaseOutputNumber(skipNumber);
                iterator++;
                visualizeCard(cards[iterator]);
            }

            @Override
            public void onSwipeBottom() {
                increaseOutputNumber(forReworkNumber);
                iterator++;
                visualizeCard(cards[iterator]);
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