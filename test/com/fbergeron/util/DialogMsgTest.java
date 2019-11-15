package com.fbergeron.util;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class DialogMsgTest {

    @Test
    public void testTitleNull() {
        Frame parent = new Frame();
        DialogMsg dm = new DialogMsg(parent, null, true, "testTitleNull");

        assertNull(dm.getTitle());
    }

    @Test
    public void testTitleNotNull() {
        Frame parent = new Frame();
        DialogMsg dm = new DialogMsg(parent, "", true, "testTitleNotNull");

        assertNotNull(dm.getTitle());
    }

    @Test
    public void testModalFalse() {
        Frame parent = new Frame();
        DialogMsg dm = new DialogMsg(parent, "", false, "testModalFalse");

        assertFalse(dm.isModal());
    }

    @Test
    public void testModalTrue() {
        Frame parent = new Frame();
        DialogMsg dm = new DialogMsg(parent, "", true, "testModalTrue");

        assertTrue(dm.isModal());
    }

    @Test
    public void testParent() {
        Frame parent = new Frame();
        DialogMsg dm = new DialogMsg(parent, "", true, "testParent");

        assertEquals(parent, dm.getParent());
    }

    @Test
    public void testMessage() {
        Frame parent = new Frame();
        DialogMsg dm = new DialogMsg(parent, "", true, "testMessage");

        TextArea textArea = (TextArea) ((Panel) dm.getComponent(0)).getComponent(0);
        assertEquals("testMessage", textArea.getText());
    }

}