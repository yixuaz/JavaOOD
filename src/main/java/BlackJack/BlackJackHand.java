package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackHand extends Hand {

    @Override
    public int score() {
        List<Integer> scores = possibleScores();
        int maxUnder21 = Integer.MIN_VALUE;
        int minOver21 = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score > 21 && score < minOver21)
                minOver21 = score;
            else if(score <= 21 && score > maxUnder21)
                maxUnder21 = score;
        }
        return maxUnder21 == Integer.MIN_VALUE ? minOver21 : maxUnder21;
    }

    private List<Integer> possibleScores() {
        List<Integer> scores = new ArrayList<>();
        for (Card card : cards) {
            updateScores(card, scores);
        }
        return scores;
    }

    private void updateScores(Card card, List<Integer> scores) {
        final int[] toAdd = getScores(card);
        if(scores.isEmpty()) {
            for (int score : toAdd)
                scores.add(score);
        } else {
            int len = scores.size();
            for (int i = 0; i < len; i++) {
                int oldScore = scores.get(i);
                scores.set(i, oldScore + toAdd[0]);
                for (int j = 1; j < toAdd.length; j++)
                    scores.add(oldScore + toAdd[j]);
            }

        }
    }

    private int[] getScores(Card card) {
        if (card.getFaceValue() > 1)
            return new int[]{Math.min(card.getFaceValue(), 10)};
        return new int[]{1, 11};
    }

    public boolean busted() {
        return score() > 21;
    }

    public boolean isBlackJack() {
        if(cards.size() != 2) return false;
        Card fir = cards.get(0);
        Card sec = cards.get(1);
        return (isAce(fir) && isFaceCard(sec)) ||
                (isAce(sec) && isFaceCard(fir));
    }

    private boolean isFaceCard(Card sec) {
        return sec.getFaceValue() >= 11 && sec.getFaceValue() <= 13;
    }

    private boolean isAce(Card fir) {
        return fir.getFaceValue() == 1;
    }
}
