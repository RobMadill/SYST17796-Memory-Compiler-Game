package syst17796.memory;

public class PlayingCard extends Card {

    public enum Suit {

        DIAMONDS("Diamonds", "Red"), HEARTS("Hearts", "Red"),
        SPADES("Spades", "Black"), CLUBS("Clubs", "Black");
        private String suit;
        private String colour;

        Suit(String suit, String colour) {
            this.suit = suit;
            this.colour = colour;
        }

        public String getSuit() {
            return suit;
        }

        public String getColour() {
            return colour;
        }
    }

    public enum Value {

        ACE(1, "Ace"), TWO(2, "Two"), THREE(3, "Three"), FOUR(4, "Four"), FIVE(5, "Five"),
        SIX(6, "Six"), SEVEN(7, "Seven"), EIGHT(8, "Eight"), NINE(9, "Nine"), TEN(10, "Ten"),
        JACK(11, "Jack"), QUEEN(12, "Queen"), KING(13, "King");

        private int value;
        private String strValue;

        Value(int value, String strValue) {
            this.value = value;
            this.strValue = strValue;
        }

        public int getValue() {
            return value;
        }

        public String getStrValue() {
            return strValue;
        }
    }

    private Value value;
    private Suit suit;

    public PlayingCard(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value.getValue();
    }

    public String getStrValue() {
        return value.getStrValue();
    }

    public String getSuit() {
        return suit.getSuit();
    }
    public String getColourSuit() {
        return suit.getColour();
    }

    @Override
    public String toString() {
        return String.format("%s of %s  ", getStrValue(), getSuit());
    }
}