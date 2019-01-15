package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static final Random random = new Random();
    private final List<Card> cards = new ArrayList<>();
    private int deltaIndex = 0;

    public Deck() {
        for (int i = 0; i <= 13; i++) {
            for (Suit suit : Suit.values())
                cards.add(new Card(suit, i));
        }
    }

    public void shuffle() {
        for (int i = 0; i < cards.size() - 1; i++) {
            int j = random.nextInt(cards.size() - i) + i;
            Card c1 = cards.get(i);
            Card c2 = cards.get(j);
            cards.set(j, c1);
            cards.set(i, c2);
        }
    }

    private int remainingCards() {
        return cards.size() - deltaIndex;
    }

    public Card[] dealHand(int number) {
        if (remainingCards() < number) {
            return null;
        }
        Card[] cards = new Card[number];
        for (int i = 0; i < number; i++) {
            cards[i] = dealCard();
        }
        return cards;

    }

    public Card dealCard() {
        return remainingCards() == 0 ? null : cards.get(deltaIndex++);
    }
}
