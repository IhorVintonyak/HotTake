package com.example.hottake;

import androidx.annotation.NonNull;

public class Deck {
    private Card[] cards;

    private int iterator;

    public Deck(Card[] inputCards){
        cards = inputCards;
        iterator = 0;
    }

    public Card[] getCards() {
        return cards;
    }

    public boolean incrementIsPossible(){
        if(iterator >= cards.length-1){
            return false;
        }else{
            return true;
        }
    }

    public void incrementIterator(){
        iterator++;
    }

    public Card getCurrentCard(){
        return cards[iterator];
    }

    public void toFirstCard(){
        iterator = 0;
    }

//    public String saveData(){
//        return "dsada";
//    }


    @NonNull
    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString() + "\n");
        }
        String result = sb.toString();
        return result;
    }

}
