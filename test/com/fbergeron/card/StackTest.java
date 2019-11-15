package com.fbergeron.card;

import org.junit.Test;

import java.awt.*;
import java.util.Vector;

import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void Stack_location() {
        Stack stack = new Stack();
        assertEquals(stack.getLocation(), new Point(0, 0));
    }

    @Test
    public void Stack_empty() {
        Stack stack = new Stack();
        assertEquals(stack.cardCount(), 0);
    }

    @Test
    public void firstFaceUp() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);
        ClassicCard card5 = new ClassicCard(Value.V_KING, Suit.DIAMOND);
        card4.turnFaceUp();

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);
        stack.push(card5);

        assertEquals(3, stack.firstFaceUp());
    }

    @Test
    public void top_empty() {
        Stack stack = new Stack();

        assertNull(stack.top());
    }

    @Test
    public void top_card() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);
        ClassicCard card5 = new ClassicCard(Value.V_KING, Suit.DIAMOND);

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);
        stack.push(card5);

        assertEquals(card5, stack.top());
    }

    @Test
    public void elementAt() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);
        ClassicCard card5 = new ClassicCard(Value.V_KING, Suit.DIAMOND);

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);
        stack.push(card5);

        assertEquals(card2, stack.elementAt(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void elementAt_empty() {
        Stack stack = new Stack();

        stack.elementAt(0);
    }

    @Test
    public void push() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);
        ClassicCard card5 = new ClassicCard(Value.V_KING, Suit.DIAMOND);

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);
        stack.push(card5);

        assertEquals(5, stack.cardCount());
    }

    @Test
    public void pop_count() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);
        ClassicCard card5 = new ClassicCard(Value.V_KING, Suit.DIAMOND);

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);
        stack.push(card5);

        int count = stack.cardCount();

        stack.pop(card5);

        assertEquals(count - 1, stack.cardCount());
    }

    @Test
    public void pop_card() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);
        ClassicCard card5 = new ClassicCard(Value.V_KING, Suit.DIAMOND);

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);
        stack.push(card5);

        Card popped = stack.pop();

        assertEquals(card5, popped);
    }

    @Test
    public void cardCount() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);
        ClassicCard card5 = new ClassicCard(Value.V_KING, Suit.DIAMOND);
        ClassicCard card6 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card7 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card8 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card9 = new ClassicCard(Value.V_9, Suit.HEART);
        ClassicCard card10 = new ClassicCard(Value.V_KING, Suit.DIAMOND);

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);
        stack.push(card5);
        stack.push(card6);
        stack.push(card7);
        stack.push(card8);
        stack.push(card9);
        stack.push(card10);

        assertEquals(10, stack.cardCount());
    }

    @Test
    public void contains() {
        Stack stack = new Stack();

        ClassicCard card = new ClassicCard(Value.V_JACK, Suit.HEART);
        card.setLocation(25, 30);

        assertTrue(stack.contains(new Point(26, 35)));
    }

    @Test
    public void contains_false() {
        Stack stack = new Stack();

        ClassicCard card = new ClassicCard(Value.V_JACK, Suit.HEART);
        card.setLocation(25, 30);

        assertFalse(stack.contains(new Point(500, 120)));
    }

    @Test
    public void isValid() {
        Stack stack = new Stack();
        assertTrue(stack.isValid(stack));
    }

    @Test
    public void getClickedCard() {
        Stack stack = new Stack();

        ClassicCard card = new ClassicCard(Value.V_JACK, Suit.HEART);
        card.setLocation(25, 30);
        stack.push(card);

        assertEquals(card, stack.getClickedCard(new Point(26, 31)));
    }

    @Test
    public void reverse() {
        Stack stack = new Stack();
        Vector<Card> cards = new Vector<>();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);
        cards.add(card4);
        cards.add(card3);
        cards.add(card2);
        cards.add(card1);

        stack.reverse();
        assertArrayEquals(cards.toArray(), stack.getCards().toArray());
    }

    /**
     * Testa se o comando é propagado para as cartas.
     */
    @Test
    public void setLocation() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);

        stack.setLocation(25, 30);
        Point point = new Point(25, 30);

        // compara o ponto com uma carta aleatória
        assertEquals(point, card3.getLocation());
    }

    @Test
    public void testToString() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);
        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);

        assertEquals("[X3SX, XJHX, X8CX, X9HX]", stack.toString());
    }

    @Test
    public void getCards() {
        Stack stack = new Stack();
        Vector<Card> cards = new Vector<>();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        ClassicCard card2 = new ClassicCard(Value.V_JACK, Suit.HEART);
        ClassicCard card3 = new ClassicCard(Value.V_8, Suit.CLUB);
        ClassicCard card4 = new ClassicCard(Value.V_9, Suit.HEART);

        stack.push(card1);
        stack.push(card2);
        stack.push(card3);
        stack.push(card4);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        assertArrayEquals(cards.toArray(), stack.getCards().toArray());
    }

    @Test
    public void getLocation() {
        Stack stack = new Stack();

        stack.setLocation(25, 30);
        Point point = new Point(25, 30);

        // compara o ponto com uma carta aleatória
        assertEquals(point, stack.getLocation());
    }

    @Test
    public void isEmpty_false() {
        Stack stack = new Stack();

        ClassicCard card1 = new ClassicCard(Value.V_3, Suit.SPADE);
        stack.push(card1);

        assertFalse(stack.isEmpty());
    }

    @Test
    public void isEmpty_true() {
        Stack stack = new Stack();

        assertTrue(stack.isEmpty());
    }
}