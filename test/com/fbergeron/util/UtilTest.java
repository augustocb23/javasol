package com.fbergeron.util;

import com.fbergeron.card.ClassicCard;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertNotNull;

public class UtilTest {

    @Test
    public void getImageResourceFile() {
        Image image = Util.getImageResourceFile("Legal.png", ClassicCard.class);

        assertNotNull(image);
    }

    @Test(expected = NullPointerException.class)
    public void getImageResourceFile_stringNull() {
        Util.getImageResourceFile(null, ClassicCard.class);
    }
}