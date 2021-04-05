package kg.geektech.android3lesson1_2.domain;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game<Content> {

    private List<Card<Content>> cards = new ArrayList<>();
    private boolean isGameOver = false;


    public Game(List<Content> contents) {
        for (int i = 0; i < contents.size(); i++) {
            cards.add(new Card<>(i * 2, false, false, contents.get(i)));
            cards.add(new Card<>((i * 2) + 1, false, false, contents.get(i)));
        }
        Collections.shuffle(cards);
    }

    public void choose(Card<Content> card) {
        card.setFaceUp(!card.isFaceUp());
        if (card.isFaceUp()) {
            checkPair(card);
        }
        isGameFinished();

    }

    private void checkPair(Card<Content> card) {

        for (Card<Content> secondCard : cards) {
            Log.e("TAG", "for each is running: ");

            if (secondCard.isFaceUp()
                    && secondCard.getId() != card.getId()
                    && secondCard.equals(card)) {

                card.setMatch(true);
                secondCard.setMatch(true);

                removeCard();
                Log.e("TAG", "MATCH!");
                return;

            } else if (
                    secondCard.isFaceUp()
                            && secondCard.getId() != card.getId()
                            && secondCard.getContent() != card.getContent()) {

                android.os.Handler handler = new Handler();
                handler.postDelayed(() -> {

                    card.setFaceUp(false);
                    secondCard.setFaceUp(false);
                    Log.e("TAG", "we are in else if");

                }, 300);
            }
        }
    }

    private void removeCard() {
        Log.e("TAG", "removeCard: method is working  ");

        List<Card<Content>> resultCards = new ArrayList<>(cards);
        for (Card<Content> c : cards) {
            if (c.isMatch()) {
                resultCards.remove(c);
            }
        }
        cards.clear();
        cards.addAll(resultCards);
    }


    public List<Card<Content>> getCards() {
        return cards;
    }


    private void isGameFinished() {
        if (cards.isEmpty()) {
            setGameOver(true);
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
