package com.example.hottake;

public class Card {
    private int number;
    private String text;
    private int numberVoteBad;
    private int numberVoteGood;
    private int numberVoteSkip;
    private int numberVoteForRework;


    public Card(){
        number = 0;
        text = "";
        numberVoteBad = 0;
        numberVoteGood = 0;
        numberVoteSkip = 0;
        numberVoteForRework = 0;
    }

    public Card(int Number, String Text){
        number = Number;
        text=Text;
        numberVoteBad = 0;
        numberVoteGood = 0;
        numberVoteSkip = 0;
        numberVoteForRework = 0;
    }

    public Card(int Number, String Text, int NumberVoteBad, int NumberVoteGood, int NumberVoteSkip, int NumberVoteForRework){
        number = Number;
        text=Text;
        numberVoteBad = NumberVoteBad;
        numberVoteGood = NumberVoteGood;
        numberVoteSkip = NumberVoteSkip;
        numberVoteForRework = NumberVoteForRework;
    }

    public void voteBad() {
        numberVoteBad++;
    }
    public void voteGood() {
        numberVoteGood++;
    }
    public void voteSkip() {
        numberVoteSkip++;
    }
    public void voteForRework() {
        numberVoteForRework++;
    }




    public String categorizeCard(){
        if(numberVoteBad > numberVoteGood && numberVoteBad > numberVoteSkip && numberVoteBad > numberVoteForRework){
            return "Brutta";
        }
        if(numberVoteGood > numberVoteBad && numberVoteGood > numberVoteSkip && numberVoteGood > numberVoteForRework){
            return "Buona";
        }
        if(numberVoteSkip > numberVoteBad && numberVoteSkip > numberVoteGood && numberVoteSkip > numberVoteForRework){
            return "Non interessante";
        }
        if(numberVoteForRework > numberVoteBad && numberVoteForRework > numberVoteGood && numberVoteForRework > numberVoteSkip){
            return "Da rifare";
        }
        return "Senza giudizio";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getNumberVoteBad() {
        return numberVoteBad;
    }
    public void setNumberVoteBad(int numberVoteBad) {
        this.numberVoteBad = numberVoteBad;
    }
    public int getNumberVoteGood() {
        return numberVoteGood;
    }
    public void setNumberVoteGood(int numberVoteGood) {
        this.numberVoteGood = numberVoteGood;
    }
    public int getNumberVoteSkip() {
        return numberVoteSkip;
    }
    public void setNumberVoteSkip(int numberVoteSkip) {
        this.numberVoteSkip = numberVoteSkip;
    }
    public int getNumberVoteForRework() {
        return numberVoteForRework;
    }
    public void setNumberVoteForRework(int numberVoteForRework) {
        this.numberVoteForRework = numberVoteForRework;
    }
}
