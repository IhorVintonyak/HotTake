package com.example.hottake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mainWindowText;
    private TextView badNumber;
    private TextView goodNumber;
    private TextView skipNumber;
    private TextView forReworkNumber;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainWindowText = findViewById(R.id.mainWindowText);

        badNumber = findViewById( R.id.badNumber );
        goodNumber = findViewById( R.id.goodNumber );
        skipNumber = findViewById( R.id.skipNumber );
        forReworkNumber = findViewById( R.id.forReworkNumber );

//        TextView[] outputNumbers = {badNumber, goodNumber, skipNumber, forReworkNumber};

        mainWindowText.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                increaseOutputNumber(goodNumber);
            }

            @Override
            public void onSwipeLeft() {
                increaseOutputNumber(badNumber);
            }

            @Override
            public void onSwipeTop() {
                increaseOutputNumber(skipNumber);
            }

            @Override
            public void onSwipeBottom() {
                increaseOutputNumber(forReworkNumber);
            }
        });
    }

    public void increaseOutputNumber(TextView number) {
        int currentNumber = Integer.parseInt(number.getText().toString());
        currentNumber++;
        number.setText(String.valueOf(currentNumber));
    }

}