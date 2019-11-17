package com.fbergeron.solitaire;

import com.fbergeron.card.*;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GameStateTest {

    @Test
    public void copyGameState() {
        GameInfo gameInfo = new GameInfo(GameInfo.WINNABLE_HARD, 256);
        ClassicDeck classicDeck = new ClassicDeck();
        SolitaireStack[] solitaireStacks = IntStream.range(0, Solitaire.SOL_STACK_CNT)
                .mapToObj(i -> new SolitaireStack()).toArray(SolitaireStack[]::new);
        SequentialStack[] sequentialStacks = IntStream.range(0, Solitaire.SEQ_STACK_CNT)
                .mapToObj(i -> new SequentialStack()).toArray(SequentialStack[]::new);
        Stack currentStack = new Stack();

        GameState gameState1 =
                new GameState(gameInfo, classicDeck, currentStack, solitaireStacks, sequentialStacks);
        GameState gameState2 =
                GameState.copyGameState(gameInfo, classicDeck, currentStack, solitaireStacks, sequentialStacks);

        assertEquals(gameState1, gameState2);
    }

    @Test
    public void testEqualsTrue() {
        GameInfo gameInfo1 = new GameInfo(GameInfo.WINNABLE_HARD, 128);
        ClassicDeck classicDeck1 = new ClassicDeck();
        SolitaireStack[] solitaireStacks1 = IntStream.range(0, Solitaire.SOL_STACK_CNT)
                .mapToObj(i -> new SolitaireStack()).toArray(SolitaireStack[]::new);
        SequentialStack[] sequentialStacks1 = IntStream.range(0, Solitaire.SEQ_STACK_CNT)
                .mapToObj(i -> new SequentialStack()).toArray(SequentialStack[]::new);
        Stack currentStack1 = new Stack();

        GameInfo gameInfo2 = new GameInfo(GameInfo.WINNABLE_HARD, 128);
        ClassicDeck classicDeck2 = new ClassicDeck();
        SolitaireStack[] solitaireStack2 = IntStream.range(0, Solitaire.SOL_STACK_CNT)
                .mapToObj(i -> new SolitaireStack()).toArray(SolitaireStack[]::new);
        SequentialStack[] sequentialStacks2 = IntStream.range(0, Solitaire.SEQ_STACK_CNT)
                .mapToObj(i -> new SequentialStack()).toArray(SequentialStack[]::new);
        Stack currentStack2 = new Stack();

        GameState gameState1 =
                new GameState(gameInfo1, classicDeck1, currentStack1, solitaireStacks1, sequentialStacks1);
        GameState gameState2 =
                new GameState(gameInfo2, classicDeck2, currentStack2, solitaireStack2, sequentialStacks2);

        assertEquals(gameState1, gameState2);
    }

    @Test
    public void testEqualsFalse() {
        GameInfo gameInfo1 = new GameInfo(GameInfo.WINNABLE_HARD, 128);
        ClassicDeck classicDeck1 = new ClassicDeck();
        SolitaireStack[] solitaireStacks1 = IntStream.range(0, Solitaire.SOL_STACK_CNT)
                .mapToObj(i -> new SolitaireStack()).toArray(SolitaireStack[]::new);
        SequentialStack[] sequentialStacks1 = IntStream.range(0, Solitaire.SEQ_STACK_CNT)
                .mapToObj(i -> new SequentialStack()).toArray(SequentialStack[]::new);
        Stack currentStack1 = new Stack();

        GameInfo gameInfo2 = new GameInfo(GameInfo.WINNABLE_EASY, 256);
        ClassicDeck classicDeck2 = new ClassicDeck();
        SolitaireStack[] solitaireStack2 = IntStream.range(0, Solitaire.SOL_STACK_CNT)
                .mapToObj(i -> new SolitaireStack()).toArray(SolitaireStack[]::new);
        SequentialStack[] sequentialStacks2 = IntStream.range(0, Solitaire.SEQ_STACK_CNT)
                .mapToObj(i -> new SequentialStack()).toArray(SequentialStack[]::new);
        Stack currentStack2 = new Stack();

        GameState gs = new GameState(gameInfo1, classicDeck1, currentStack1, solitaireStacks1, sequentialStacks1);
        GameState gs2 = new GameState(gameInfo2, classicDeck2, currentStack2, solitaireStack2, sequentialStacks2);

        assertNotEquals(gs, gs2);
    }

    @Test
    public void legalMoves() {
        GameInfo gameInfo = new GameInfo(GameInfo.RANDOM, 255);
        ClassicDeck deck = new ClassicDeck();
        SolitaireStack[] solitaireStacks = IntStream.range(0, Solitaire.SOL_STACK_CNT)
                .mapToObj(i -> new SolitaireStack()).toArray(SolitaireStack[]::new);
        SequentialStack[] sequentialStacks = IntStream.range(0, Solitaire.SEQ_STACK_CNT)
                .mapToObj(i -> new SequentialStack()).toArray(SequentialStack[]::new);
        Stack currentStack = new Stack();

        Card card01 = new ClassicCard(Value.V_2, Suit.SPADE);
        Card card02 = new ClassicCard(Value.V_QUEEN, Suit.DIAMOND);
        Card card03 = new ClassicCard(Value.V_2, Suit.DIAMOND);
        Card card04 = new ClassicCard(Value.V_2, Suit.CLUB);
        Card card05 = new ClassicCard(Value.V_2, Suit.HEART);
        Card card06 = new ClassicCard(Value.V_9, Suit.SPADE);
        Card card07 = new ClassicCard(Value.V_JACK, Suit.SPADE);
        Card card08 = new ClassicCard(Value.V_3, Suit.SPADE);
        Card card09 = new ClassicCard(Value.V_ACE, Suit.SPADE);
        Card card10 = new ClassicCard(Value.V_ACE, Suit.DIAMOND);
        Card card11 = new ClassicCard(Value.V_ACE, Suit.HEART);
        Card card12 = new ClassicCard(Value.V_ACE, Suit.CLUB);
        Card card13 = new ClassicCard(Value.V_5, Suit.SPADE);
        Card card14 = new ClassicCard(Value.V_4, Suit.HEART);
        Card card15 = new ClassicCard(Value.V_4, Suit.HEART);

        //configurar as cartas viradas para cima
        card01.turnFaceUp();
        card03.turnFaceUp();
        card04.turnFaceUp();
        card05.turnFaceUp();
        card06.turnFaceUp();
        card07.turnFaceUp();
        card08.turnFaceUp();
        card09.turnFaceUp();
        card10.turnFaceUp();
        card11.turnFaceUp();
        card12.turnFaceUp();
        card13.turnFaceUp();
        card14.turnFaceUp();
        card15.turnFaceUp();

        //adicionar as cartas nas pilhas
        solitaireStacks[0].push(card01);
        solitaireStacks[1].push(card02);
        solitaireStacks[2].push(card03);
        solitaireStacks[3].push(card04);
        solitaireStacks[4].push(card05);
        solitaireStacks[5].push(card06);
        solitaireStacks[6].push(card07);
        sequentialStacks[0].push(card09);
        sequentialStacks[1].push(card10);
        sequentialStacks[2].push(card11);
        sequentialStacks[3].push(card12);
        currentStack.push(card15);

        GameState gameState = new GameState(gameInfo, deck, currentStack, solitaireStacks, sequentialStacks);

        assertEquals(5, gameState.legalMoves().size());
    }

    @Test
    public void restoreGameState() {
    }
}