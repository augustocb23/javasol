package com.fbergeron.card;

import org.junit.Test;

public class DeckTest {
    @Test
    public void shuffle() {
        Deck deck = new ClassicDeck();

        deck.shuffle(25);
        deck.cardCount();
    }
}