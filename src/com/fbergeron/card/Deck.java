/*
 * Copyright (C) 2002-2011  Frédéric Bergeron (fbergeron@users.sourceforge.net)
 *                          and other contributors
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

package com.fbergeron.card;

import java.util.Random;
import java.util.Vector;

/**
 * A deck of cards.
 *
 * @author Frederic Bergeron
 * @author <A HREF="http://javasol.sourceforge.net">http://javasol.sourceforge.net</A>
 * @version Version 1.0
 */
public abstract class Deck extends Stack {
    /**
     * Shuffles the deck.
     */
    public void shuffle(int seed) {
        //We transfer the deck in a vector temporarily
        Vector<Card> v = new Vector<>();
        while (!isEmpty())
            v.addElement(pop());

        Random random = new Random();

        // set the seed to the chosen game type
        if (seed != -1)
            random.setSeed(seed);
        else {
            seed = random.nextInt(1000000);
            random.setSeed(seed);
        }

        //We push randomly selected cards on the empty deck
        while (!v.isEmpty()) {
            int randomCard = random.nextInt(v.size());
            Card c = v.elementAt(randomCard);
            push(c);
            v.removeElement(c);
        }
    }
}
