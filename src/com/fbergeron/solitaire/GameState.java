/*
 * Copyright (C) 2011  Frédéric Bergeron (fbergeron@users.sourceforge.net)
 *                     and other contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.fbergeron.solitaire;

import com.fbergeron.card.ClassicCard;
import com.fbergeron.card.ClassicDeck;
import com.fbergeron.card.Stack;
import com.fbergeron.card.Value;

import java.util.ArrayList;

public class GameState {

    private GameInfo gameInfo;
    private ClassicDeck deck;
    private Stack revealedCards;
    private SolitaireStack[] solStack;
    private SequentialStack[] seqStack;

    private GameState() {
    }

    /*
     Save the state of the solitaire game
     do NOT make a deep copy
     this is used in the case of legal move generation
    */
    GameState(GameInfo gameInfo, ClassicDeck deck, Stack revealedCards, SolitaireStack[] solStack,
              SequentialStack[] seqStack) {
        this.gameInfo = gameInfo;
        this.deck = deck;
        this.revealedCards = revealedCards;
        this.solStack = solStack;
        this.seqStack = seqStack;
    }

    /*
     Save the state of the solitaire game - make deep copies of objects
     This is important otherwise the saved objects will get corrupted as the
     game plays out i.e. turned face up etc.
     This is used to undo moves
    */
    static GameState copyGameState(
            GameInfo gameInfo,
            ClassicDeck deck,
            Stack revealedCards,
            SolitaireStack[] solStack,
            SequentialStack[] seqStack) {
        GameState dest = new GameState();

        dest.gameInfo = new GameInfo(gameInfo.getType(), gameInfo.getSeed());
        dest.deck = new ClassicDeck();
        for (int i = 0; i < deck.cardCount(); i++) {
            ClassicCard currentCard = ((ClassicCard) deck.elementAt(i));
            ClassicCard c = new ClassicCard(currentCard);
            dest.deck.push(c);
        }
        dest.revealedCards = new Stack();
        for (int i = 0; i < revealedCards.cardCount(); i++) {
            ClassicCard currentCard = ((ClassicCard) revealedCards.elementAt(i));
            ClassicCard c = new ClassicCard(currentCard);
            dest.revealedCards.push(c);
        }

        dest.solStack = new SolitaireStack[Solitaire.SOL_STACK_CNT];
        for (int i = 0; i < Solitaire.SOL_STACK_CNT; i++) {
            dest.solStack[i] = new SolitaireStack();
            for (int j = 0; j < solStack[i].cardCount(); j++) {
                ClassicCard currentCard = ((ClassicCard) solStack[i].elementAt(j));
                ClassicCard c = new ClassicCard(currentCard);
                dest.solStack[i].push(c);
            }
        }
        dest.seqStack = new SequentialStack[Solitaire.SEQ_STACK_CNT];
        for (int i = 0; i < Solitaire.SEQ_STACK_CNT; i++) {
            dest.seqStack[i] = new SequentialStack();
            for (int j = 0; j < seqStack[i].cardCount(); j++) {
                ClassicCard currentCard = ((ClassicCard) seqStack[i].elementAt(j));
                ClassicCard c = new ClassicCard(currentCard);
                dest.seqStack[i].push(c);
            }
        }
        return dest;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GameState))
            return (false);

        GameState gs = (GameState) obj;
        if (!this.gameInfo.equals(gs.gameInfo))
            return (false);
        if (this.deck.getCards().size() != gs.deck.getCards().size() ||
                this.revealedCards.getCards().size() != gs.revealedCards.getCards().size()) {
            return false;
        }
        for (int i = 0; i < Solitaire.SOL_STACK_CNT; i++) {
            if (this.solStack[i].cardCount() != gs.solStack[i].cardCount()) {
                return false;
            }
        }
        for (int i = 0; i < Solitaire.SEQ_STACK_CNT; i++) {
            if (this.seqStack[i].cardCount() != gs.seqStack[i].cardCount()) {
                return false;
            }
        }

        return true;
    }

    // For this game state return a list of game states
    // after execution of all legal moves from this game state.
    ArrayList<GameState> legalMoves() {
        ArrayList<GameState> legalGs = new ArrayList<>();
        ClassicCard c;
        int j;

        // Set all cards to not legal
        for (int i = 0; i < Solitaire.SOL_STACK_CNT; i++) {
            for (int k = 0; k < this.solStack[i].cardCount(); k++) {
                ClassicCard cc = (ClassicCard) (this.solStack[i].elementAt(k));
                cc.setLegal(false);
            }
        }
        for (int i = 0; i < Solitaire.SEQ_STACK_CNT; i++) {
            for (int k = 0; k < this.seqStack[i].cardCount(); k++) {
                ClassicCard cc = (ClassicCard) (this.seqStack[i].elementAt(k));
                cc.setLegal(false);
            }
        }
        for (int i = 0; i < this.revealedCards.cardCount(); i++) {
            ClassicCard cc = (ClassicCard) (this.revealedCards.elementAt(i));
            cc.setLegal(false);
        }
        for (int i = 0; i < this.deck.cardCount(); i++) {
            ClassicCard cc = (ClassicCard) (this.deck.elementAt(i));
            cc.setLegal(false);
        }

        // Check Solitaire Stacks Legal Moves
        for (int i = 0; i < Solitaire.SOL_STACK_CNT; i++) {
            j = this.solStack[i].firstFaceUp();
            // Find 1st turned over card in stack
            if (j != -1) {
                // Can this card be moved to another solitaire stack slot?
                c = ((ClassicCard) this.solStack[i].elementAt(j));
                legalSolToSol(c, i);
                // Can this card be moved to a sequential stack?
                legalSolToSeq(c, i);

                if (j + 1 != this.solStack[i].cardCount()) {
                    // If 1st turned over not equal top card then try legal moves on top card
                    j = this.solStack[i].cardCount() - 1;
                    c = ((ClassicCard) this.solStack[i].elementAt(j));
                    // Can this card be moved to a sequential stack?
                    legalSolToSeq(c, i);
                }
            }
        }

        c = (ClassicCard) this.revealedCards.top();
        // Check Revealed cards legal move to sequential stacks
        legalRevToSeq(c);
        // Check Revealed cards legal moves to solitaire stacks
        legalRevToSol(c);

        return legalGs;
    }

    // Check if card can be moved from one sol stack to another
    private void legalSolToSol(ClassicCard c, int i) {
        if (c.getValue() == Value.V_ACE) {
            // Never move an ace between sol stacks only to a sequential stack
            return;
        }
        Stack dst;
        Stack src = this.solStack[i];
        Stack currStack = src.pop(c);
        currStack.reverse();
        currStack.reverse();
        for (int j2 = 0; j2 < Solitaire.SOL_STACK_CNT; j2++) {
            if (j2 != i) {
                dst = this.solStack[j2];
                if (dst != null && dst.isValid(currStack)) {
                    // Only consider the move if it turns over a card or empties a stack
                    if (src.isEmpty() | (!src.isEmpty() && src.top().isFaceDown())) {
                        // Don't consider if source stack after move is empty but destination stack before move was
                        // empty
                        // i.e. moving a king stack to an empty stack.
                        if (!(src.isEmpty() && dst.isEmpty())) {
                            //                  if (!src.isEmpty() && !dst.isEmpty()){
                            // Move is legal so apply move and store result gamestate in legal games states
                            ClassicCard cOut = ((ClassicCard) currStack.elementAt(currStack.cardCount() - 1));
                            cOut.setLegal(true);
                        }
                    }
                }
            }
        }
        // put cards back on src stack
        for (; !currStack.isEmpty(); )
            src.push(currStack.pop());
    }

    // Check if card can be moved to a sequential stack
    private void legalSolToSeq(ClassicCard c, int i) {
        Stack src = this.solStack[i];
        Stack currStack = src.pop(c);
        if (currStack.cardCount() > 2) {
            // Can't move more than one card to sequential stack
            for (; !currStack.isEmpty(); )
                src.push(currStack.pop());
            return;
        }
        for (int j2 = 0; j2 < Solitaire.SEQ_STACK_CNT; j2++) {
            Stack dst = this.seqStack[j2];
            if (dst != null && dst.isValid(currStack)) {
                // Move is legal so apply move and store result gamestate in legal games states
                ClassicCard cOut = ((ClassicCard) currStack.elementAt(currStack.cardCount() - 1));

                cOut.setLegal(true);
                for (; !currStack.isEmpty(); )
                    src.push(currStack.pop());
                // if can go on one sequential stack no need to check the others
                return;
            }
        }
        // put cards back on src stack
        for (; !currStack.isEmpty(); )
            src.push(currStack.pop());
    }

    // Check if revealed card can be place on one of the solitaire stacks
    private void legalRevToSol(ClassicCard c) {
        if (this.revealedCards.isEmpty())
            return;
        Stack src = this.revealedCards;
        Stack currStack = src.pop(c);
        for (int j2 = 0; j2 < Solitaire.SOL_STACK_CNT; j2++) {
            Stack dst = this.solStack[j2];
            if (dst != null && dst.isValid(currStack)) {

                // Move is legal so apply move and store result gamestate in legal games states
                ClassicCard cOut = ((ClassicCard) currStack.elementAt(0));
                cOut.setLegal(true);
            }
        }
        // put cards back on src stack
        for (; !currStack.isEmpty(); )
            src.push(currStack.pop());
    }

    // Check if revealed card can be place on one of the sequential stacks
    private void legalRevToSeq(ClassicCard c) {
        Stack curr;
        Stack currStack;
        Stack src;
        Stack dst;
        if (this.revealedCards.isEmpty())
            return;
        src = this.revealedCards;
        currStack = src.pop(c);
        currStack.reverse();
        curr = currStack;
        curr.reverse();
        if (curr.cardCount() > 2) {
            // Can't move more than one card to sequential stack
            for (; !curr.isEmpty(); )
                src.push(curr.pop());
            return;
        }
        for (int j2 = 0; j2 < Solitaire.SEQ_STACK_CNT; j2++) {

            dst = this.seqStack[j2];
            if (dst != null && dst.isValid(curr)) {
                // Only consider the move if it turns over a card or empties a stack
                // Move is legal so apply move and store result gamestate in legal games states
                ClassicCard cOut = ((ClassicCard) curr.elementAt(0));
                cOut.setLegal(true);
                for (; !curr.isEmpty(); )
                    src.push(curr.pop());
                // if can go on one sequential stack no need to check the others
                return;

            }

        }
        // put cards back on src stack
        for (; !curr.isEmpty(); )
            src.push(curr.pop());
    }

    // Restore the game state to the previous state
    // In the case of undo this would be before the last move
    void restoreGameState(GameInfo gameInfo, ClassicDeck deck, Stack revealedCards, SolitaireStack[] solStack,
                          SequentialStack[] seqStack) {
        gameInfo.setType(this.gameInfo.getType());
        gameInfo.setSeed(this.gameInfo.getSeed());
        for (; !revealedCards.isEmpty(); ) {
            revealedCards.pop();
        }
        for (int i = 0; i < this.revealedCards.cardCount(); i++) {
            ClassicCard currentCard = ((ClassicCard) this.revealedCards.elementAt(i));
            ClassicCard c = new ClassicCard(currentCard);
            revealedCards.push(c);

        }
        for (; !deck.isEmpty(); ) {
            deck.pop();
        }
        for (int i = 0; i < this.deck.cardCount(); i++) {
            ClassicCard currentCard = ((ClassicCard) this.deck.elementAt(i));
            ClassicCard c = new ClassicCard(currentCard);
            deck.push(c);
        }
        for (int i = 0; i < Solitaire.SEQ_STACK_CNT; i++) {
            for (; !seqStack[i].isEmpty(); ) {
                seqStack[i].pop();
            }
            for (int j = 0; j < this.seqStack[i].cardCount(); j++) {
                ClassicCard currentCard = ((ClassicCard) this.seqStack[i].elementAt(j));
                ClassicCard c = new ClassicCard(currentCard);
                seqStack[i].push(c);
            }
        }
        for (int i = 0; i < Solitaire.SOL_STACK_CNT; i++) {
            for (; !solStack[i].isEmpty(); ) {
                solStack[i].pop();
            }

            for (int j = 0; j < this.solStack[i].cardCount(); j++) {
                ClassicCard currentCard = ((ClassicCard) this.solStack[i].elementAt(j));
                ClassicCard c = new ClassicCard(currentCard);
                solStack[i].push(c);
            }
        }
    }

    public String toString() {
        return (gameInfo.toString());
    }
}
