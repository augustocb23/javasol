package com.fbergeron.solitaire;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FrameRulesTest {

    @Test
    public void constructor() {
        FrameRules frameRules = new FrameRules();

        assertNotNull(frameRules);
    }

    @Test
    public void setLocale() {
        FrameRules frameRules = new FrameRules();
        frameRules.setLocale(Locale.ENGLISH);

        assertEquals(Locale.ENGLISH, frameRules.getLocale());
    }
}