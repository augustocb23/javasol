package com.fbergeron.solitaire;

import com.fbergeron.card.ClassicCard;
import com.fbergeron.card.Stack;
import com.fbergeron.card.Suit;
import com.fbergeron.card.Value;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolitaireStackTest {

    @Test
    public void isValidEmpty() {
        Stack solitaireStack = new SolitaireStack();

        Stack stack = new Stack();
        ClassicCard card = new ClassicCard(Value.V_KING, Suit.SPADE);
        stack.push(card);

        assertTrue(solitaireStack.isValid(stack));
    }

    @Test
    public void isValidNotEmpty() {
        Stack solitaireStack = new SolitaireStack();
        ClassicCard card1 = new ClassicCard(Value.V_KING, Suit.SPADE);
        solitaireStack.push(card1);

        Stack stack = new Stack();
        ClassicCard card2 = new ClassicCard(Value.V_QUEEN, Suit.HEART);
        stack.push(card2);

        assertTrue(solitaireStack.isValid(stack));
    }

    @Test
    public void isValidFalse() {
        Stack solitaireStack = new SolitaireStack();
        ClassicCard card1 = new ClassicCard(Value.V_KING, Suit.SPADE);
        solitaireStack.push(card1);

        Stack stack = new Stack();
        ClassicCard card2 = new ClassicCard(Value.V_2, Suit.HEART);
        stack.push(card2);

        assertFalse(solitaireStack.isValid(stack));
    }
}