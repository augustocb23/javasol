package com.fbergeron.solitaire;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FrameCongratulationsTest {

    @Test
    public void setVisible_false() {
        Solitaire solitaire = new Solitaire(false);
        solitaire.setLocale(Locale.ENGLISH);

        FrameCongratulations frameCongratulations = new FrameCongratulations();
        frameCongratulations.setVisible(false);

        assertFalse(frameCongratulations.isVisible());
    }

    @Test
    public void setVisible_true() {
        Solitaire solitaire = new Solitaire(false);
        solitaire.setLocale(Locale.ENGLISH);

        FrameCongratulations frameCongratulations = new FrameCongratulations();
        frameCongratulations.setVisible(true);

        assertTrue(frameCongratulations.isVisible());
    }
}