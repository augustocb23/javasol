package com.fbergeron.solitaire;

import junit.extensions.PA;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class SolitaireTest {

    @Test
    public void setVisible_true() {
        Solitaire solitaire = new Solitaire(false);
        solitaire.setLocale(Locale.ENGLISH);

        solitaire.setVisible(true);

        assertTrue(solitaire.isVisible());
    }

    @Test
    public void setVisible_false() {
        Solitaire solitaire = new Solitaire(false);
        solitaire.setLocale(Locale.ENGLISH);

        solitaire.setVisible(false);

        assertFalse(solitaire.isVisible());
    }

    @Test
    public void setGameType() {
        Solitaire solitaire = new Solitaire(false);
        solitaire.setLocale(Locale.ENGLISH);

        PA.invokeMethod(solitaire, "setGameType(java.lang.String)", GameInfo.WINNABLE_HARD);
        assertEquals(GameInfo.WINNABLE_HARD, ((GameInfo) PA.getValue(solitaire, "gameInfo")).getType());
    }
}