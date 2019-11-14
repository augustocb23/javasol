package com.fbergeron.card;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CardTest {

    /**
     * Testa o método <code>toString()</code>.
     */
    @Test
    public void testToString() {
        ClassicCard classicCard = new ClassicCard(Value.V_JACK, Suit.CLUB);
        String string = "XJCX";

        String result = classicCard.toString();
        assertEquals(string, result);
    }

    @Test
    public void turnFaceUp() {
        ClassicCard card = new ClassicCard(Value.V_JACK, Suit.CLUB);
        card.turnFaceUp();

        assertFalse(card.isFaceDown());
    }

    @Test
    public void turnFaceDown() {
        ClassicCard card = new ClassicCard(Value.V_JACK, Suit.CLUB);
        card.turnFaceDown();

        assertTrue(card.isFaceDown());
    }

    @Test
    public void contains_true() {
        ClassicCard card = new ClassicCard(Value.V_JACK, Suit.CLUB);
        card.setLocation(25, 32);

        assertTrue(card.contains(new Point(27, 34)));
    }

    @Test
    public void contains_false() {
        ClassicCard card = new ClassicCard(Value.V_JACK, Suit.CLUB);
        card.setLocation(25, 32);

        assertFalse(card.contains(new Point(270, 340)));
    }

    @Test
    public void getLocation() {
        ClassicCard card = new ClassicCard(Value.V_JACK, Suit.CLUB);
        card.setLocation(25, 32);

        assertEquals(card.getLocation(), new Point(25, 32));
    }

    @Test
    public void getSize() {
        ClassicCard card = new ClassicCard(Value.V_JACK, Suit.CLUB);
        card.setSize(new Dimension(100, 150));

        assertEquals(card.getSize(), new Dimension(100, 150));
    }
}
