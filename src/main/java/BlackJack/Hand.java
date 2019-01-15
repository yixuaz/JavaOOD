package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Hand {
    final List<Card> cards = new ArrayList<>();

    public abstract int score();

    public void addCards(Card[] c) {
        Collections.addAll(cards, c);
    }

    public int size() {
        return cards.size();
    }

    public void print() {
        for (Card c : cards)
            System.out.print(c.getFaceValue() +",");
        System.out.println();
    }
}
