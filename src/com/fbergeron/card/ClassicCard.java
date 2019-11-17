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

import com.fbergeron.util.Util;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.ImageObserver;
import java.util.Hashtable;

/**
 * A classic game card.
 *
 * @author Frederic Bergeron
 * @author <A HREF="http://javasol.sourceforge.net">http://javasol.sourceforge.net</A>
 * @version Version 1.0
 */
public class ClassicCard extends Card {

    private static final String STRING_HIDDEN = "X";

    private static final int BORDER_ARC = 20;
    private static final Color CARD_COLOR = Color.blue;
    static private Hashtable<String, Image> images = new Hashtable<>();
    static private MediaTracker tracker = new MediaTracker(new Button());

    //Preloading of the card images.
    static {
        for (int i = 0; i < Suit.suits.length; i++) {
            for (int j = 0; j < Value.values.length; j++) {
                StringBuilder imgFilename = new StringBuilder(Suit.suits[i].toString());
                imgFilename.append("/").append(Value.values[j].toString());
                String imgName = imgFilename.toString();
                imgFilename.append(".png");
                Image img = Util.getImageResourceFile(imgFilename.toString(), ClassicCard.class);
                tracker.addImage(img, 0);
                if (img != null)
                    images.put(imgName, img);
            }
        }
        String imgName = "Legal";
        Image img = Util.getImageResourceFile(imgName + ".png", ClassicCard.class);
        tracker.addImage(img, 0);
        if (img != null)
            images.put(imgName, img);
        try {
            tracker.waitForID(0);
        } catch (InterruptedException ignored) {
        }
    }

    private Suit suit;
    private Value value;
    private String imgName;
    private boolean legal;
    private ImageObserver imgObserver;
    private Image img;

    /**
     * Creates a card from other one.
     *
     * @param card Card to be copied.
     */
    public ClassicCard(ClassicCard card) {
        super();
        this.img = card.img;
        this.imgName = card.imgName;
        this.imgObserver = card.imgObserver;
        this.suit = card.suit;
        this.value = card.value;
        this.legal = card.legal;

        this.setLocation(card.getLocation());
        this.setSize(card.getSize());
        if (card.isFaceDown())
            this.turnFaceDown();
        else
            this.turnFaceUp();
    }

    public ClassicCard(Value value, Suit suit) {
        super();
        this.suit = suit;
        this.value = value;
        this.imgName = suit.toString() + "/" + value.toString();
        this.legal = false;
        turnFaceDown();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ClassicCard))
            return false;
        if (obj == this)
            return true;

        ClassicCard classicCard = (ClassicCard) obj;
        return isFaceDown() == classicCard.isFaceDown() &&
                suit == classicCard.suit &&
                value == classicCard.value;
    }

    public String toString() {
        StringBuilder strBufTemp = new StringBuilder();
        if (isFaceDown())
            strBufTemp.append(STRING_HIDDEN);
        strBufTemp.append(value.toString());
        strBufTemp.append(suit.toString());
        if (isFaceDown())
            strBufTemp.append(STRING_HIDDEN);
        return strBufTemp.toString();
    }

    public void paint(Graphics g, boolean hint) {
        Point location = getLocation();

        //Background
        RoundRectangle2D border = new RoundRectangle2D.Double(
                location.x, location.y, getSize().width - 1, getSize().height - 1, BORDER_ARC, BORDER_ARC);

        g.setClip(border); // Don't draw outside the lines

        if (isFaceDown()) {
            g.setColor(CARD_COLOR);
            g.fillRect(location.x, location.y, getSize().width - 1, getSize().height - 1);
        } else {
            g.setColor(Color.white);
            g.fillRect(location.x, location.y, getSize().width - 1, getSize().height - 1);

            Image img = images.get(imgName);
            if (img != null && imgObserver != null)
                g.drawImage(img, location.x + 3, location.y + 3, imgObserver);
            if (hint) {
                if (this.legal) {
                    img = images.get("Legal");
                    if (img != null && imgObserver != null)
                        g.drawImage(img, location.x + 3, location.y + 3, imgObserver);
                }
            }
        }

        g.setClip(null); // OK, you can draw anywhere again

        // Frame
        g.setColor(Color.black);
        g.drawRoundRect(location.x, location.y, getSize().width - 1, getSize().height - 1, BORDER_ARC, BORDER_ARC);
    }

    /**
     * @return Color of the card.
     * May be either <CODE>Color.red</CODE> or <CODE>Color.black</CODE>.
     */
    public Color getColor() {
        return (suit == Suit.SPADE || suit == Suit.CLUB) ? Color.black : Color.red;
    }

    /**
     * @return Suit of the card.  May be one of these values
     * <CODE>HEART</CODE>, <CODE>SPADE</CODE>, <CODE>DIAMOND</CODE> or <CODE>CLUB</CODE>,
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @return Value of the card. May be from 1 to 13.
     */
    public Value getValue() {
        return value;
    }

    void setImageObserver(ImageObserver imgObserver) {
        this.imgObserver = imgObserver;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

}
