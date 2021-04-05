package kg.geektech.android3lesson1_2.domain;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
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
        checkPairs(card);
        isGameFinished();

    }

    private void isGameFinished() {
        if (cards.isEmpty()){
            setGameOver(true);
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private void setGameOver(boolean gameOver) {
        isGameOver= gameOver;
    }

    private void checkPairs(Card<Content> card) {

        for (Card<Content> secondCard : cards) {
            Log.d("TAG", "for each is running: ");

            if (card.isFaceUp() && secondCard.isFaceUp()
                    && card.getId() != secondCard.getId()
                    && card.equals(secondCard)) {

                card.setMatch(true);
                secondCard.setMatch(true);
                Log.d("TAG", "MATCH!");

                removeSameCards();

            } else if (card.isFaceUp() && secondCard.isFaceUp()
                    && card.getId() != secondCard.getId()
                    && !card.equals(secondCard)) {


                card.setFaceUp(false);
                secondCard.setFaceUp(false);
                Log.e("TAG", "cards are not same");
            }
        }
    }

    private void removeSameCards() {
        Log.e("TAG", "removeSameCards: method is working  ");

        List<Card<Content>> resultCards =new ArrayList<>(cards);
        for (Card<Content> c : cards) {
            if (c.isMatch()){
                resultCards.remove(c);
            }
        }
        cards.clear();
        cards.addAll(resultCards);

    }




    public List<Card<Content>> getCards() {
        return cards;
    }

}
