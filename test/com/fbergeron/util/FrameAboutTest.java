package com.fbergeron.util;

import org.junit.Test;

import java.awt.*;
import java.util.Locale;
import java.util.MissingResourceException;

import static org.junit.Assert.*;

public class FrameAboutTest {

    @Test
    public void getInsets() {
        FrameAbout frameAbout = new FrameAbout();

        Insets insets = frameAbout.getInsets();
        assertTrue(insets.top == 41 &&
                insets.left == 18 &&
                insets.bottom == 18 &&
                insets.right == 18);
    }

    @Test
    public void setLocale_english() {
        FrameAbout frameAbout = new FrameAbout();
        frameAbout.setLocale(Locale.ENGLISH);

        assertEquals(Locale.ENGLISH, frameAbout.getLocale());
    }

    @Test
    public void setLocale_french() {
        FrameAbout frameAbout = new FrameAbout();
        frameAbout.setLocale(Locale.FRENCH);

        assertEquals(Locale.FRENCH, frameAbout.getLocale());
    }

    @Test(expected = MissingResourceException.class)
    public void testSetLocale_germanNotFound() {
        FrameAbout frameAbout = new FrameAbout();
        frameAbout.setLocale(Locale.GERMAN);
    }

    @Test
    public void testSetLocale_false() {
        FrameAbout frameAbout = new FrameAbout();
        frameAbout.setLocale(Locale.ENGLISH);

        assertNotEquals(Locale.FRENCH, frameAbout.getLocale());
    }

    @Test
    public void setVisible_false() {
        FrameAbout frameAbout = new FrameAbout();
        frameAbout.setVisible(true);

        assertTrue(frameAbout.isVisible());
    }

    @Test
    public void setVisible_true() {
        FrameAbout frameAbout = new FrameAbout();
        frameAbout.setVisible(false);

        assertFalse(frameAbout.isVisible());
    }
}