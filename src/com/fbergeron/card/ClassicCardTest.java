package com.fbergeron.card;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ClassicCardTest {

    /**
     * Testa o construtor que recebe um valor e um naipe.
     */
    @Test
    public void testConstructorValueSuit() {
        Value value = Value.V_KING;
        Suit suit = Suit.CLUB;
        ClassicCard classicCard = new ClassicCard(value, suit);
        boolean result = classicCard.getValue() == value && classicCard.getSuit() == suit;

        assertTrue(result);
    }

    /**
     * Testa o construtor que recebe outra carta.
     */
    @Test
    public void testConstructorCard() {
        Value value = Value.V_KING;
        Suit suit = Suit.CLUB;
        ClassicCard template = new ClassicCard(value, suit);
        Point point = new Point(25, 30);
        template.setLocation(25, 30);

        ClassicCard classicCard = new ClassicCard(template);
        boolean result = classicCard.getValue() == value &&
                classicCard.getSuit() == suit &&
                classicCard.getLocation().equals(point);

        assertTrue(result);
    }

    /**
     * Testa o construtor passando uma carta nula.
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void testConstructorCard_null() {
        ClassicCard template = null;
        ClassicCard classicCard = new ClassicCard(template);

        assertEquals(template, classicCard);
    }

    @Test
    public void testEquals() {
        ClassicCard template = new ClassicCard(Value.V_1, Suit.SPADE);
        ClassicCard classicCard = new ClassicCard(Value.V_1, Suit.SPADE);

        assertEquals(template, classicCard);
    }

    @Test
    public void testNotEquals() {
        ClassicCard template = new ClassicCard(Value.V_1, Suit.SPADE);
        ClassicCard classicCard = new ClassicCard(Value.V_1, Suit.HEART);

        assertNotEquals(template, classicCard);
    }

    @Test
    public void testToString() {
        ClassicCard classicCard = new ClassicCard(Value.V_JACK, Suit.CLUB);
        String string = "XJCX";

        String result = classicCard.toString();

        assertEquals(string, result);
    }

    @Test
    public void getColor_black() {
        ClassicCard classicCard = new ClassicCard(Value.V_1, Suit.SPADE);
        assertEquals(Color.black, classicCard.getColor());
    }

    @Test
    public void getColor_red() {
        ClassicCard classicCard = new ClassicCard(Value.V_1, Suit.HEART);
        assertEquals(Color.red, classicCard.getColor());
    }

    @Test
    public void getSuit() {
        ClassicCard classicCard = new ClassicCard(Value.V_1, Suit.HEART);
        assertEquals(Suit.HEART, classicCard.getSuit());
    }

    @Test
    public void getValue() {
        ClassicCard classicCard = new ClassicCard(Value.V_1, Suit.HEART);
        assertEquals(Value.V_1, classicCard.getValue());
    }
}
