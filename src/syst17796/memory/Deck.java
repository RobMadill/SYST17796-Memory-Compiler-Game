package syst17796.memory;

import syst17796.memory.PlayingCard.Suit;
import syst17796.memory.PlayingCard.Value;
import java.util.ArrayList;

public class Deck extends GroupOfCards {

    public Deck() {
        super(new ArrayList<Card>());
        for (Suit s : Suit.values()) {
            for (Value v : Value.values()) {
                PlayingCard card = new PlayingCard(v, s);
                super.addCard(card);
            }
        }
    }
}