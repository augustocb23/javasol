package com.fbergeron.solitaire;

import com.fbergeron.card.ClassicCard;
import com.fbergeron.card.Stack;
import com.fbergeron.card.Suit;
import com.fbergeron.card.Value;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SequentialStackTest {

    @Test
    public void isValidEmpty() {
        Stack sequentialStack = new SequentialStack();

        Stack stack = new Stack();
        ClassicCard card = new ClassicCard(Value.V_ACE, Suit.SPADE);
        stack.push(card);

        assertTrue(sequentialStack.isValid(stack));
    }

    @Test
    public void isValidNotEmpty() {
        Stack sequentialStack = new SequentialStack();
        ClassicCard card1 = new ClassicCard(Value.V_ACE, Suit.SPADE);
        sequentialStack.push(card1);

        Stack stack = new Stack();
        ClassicCard card2 = new ClassicCard(Value.V_2, Suit.SPADE);
        stack.push(card2);

        assertTrue(sequentialStack.isValid(stack));
    }

    @Test
    public void isValidFalse() {
        Stack sequentialStack = new SequentialStack();
        ClassicCard card1 = new ClassicCard(Value.V_ACE, Suit.SPADE);
        sequentialStack.push(card1);

        Stack stack = new Stack();
        ClassicCard card2 = new ClassicCard(Value.V_2, Suit.HEART);
        stack.push(card2);

        assertFalse(sequentialStack.isValid(stack));
    }
}