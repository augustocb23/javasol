package com.fbergeron.solitaire;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GameInfoTest {

    @Test
    public void testEqualsTrue() {
        GameInfo gameInfo1 = new GameInfo(GameInfo.RANDOM, 25);
        GameInfo gameInfo2 = new GameInfo(GameInfo.RANDOM, 25);

        assertEquals(gameInfo1, gameInfo2);
    }

    @Test
    public void testEqualsType() {
        GameInfo gameInfo1 = new GameInfo(GameInfo.WINNABLE_EASY, 25);
        GameInfo gameInfo2 = new GameInfo(GameInfo.WINNABLE_HARD, 25);

        assertNotEquals(gameInfo1, gameInfo2);
    }

    @Test
    public void testEqualsSeed() {
        GameInfo gameInfo1 = new GameInfo(GameInfo.RANDOM, 25);
        GameInfo gameInfo2 = new GameInfo(GameInfo.RANDOM, 50);

        assertNotEquals(gameInfo1, gameInfo2);
    }

    @Test
    public void testHashCode() {
        GameInfo gameInfo = new GameInfo();
        String result = "Random|-1";

        assertEquals(result.hashCode(), gameInfo.hashCode());
    }

    @Test
    public void testToString() {
        GameInfo gameInfo = new GameInfo();
        String result = "Random|-1";

        assertEquals(result, gameInfo.toString());
    }

    @Test
    public void getSeed() {
        GameInfo gameInfo = new GameInfo(GameInfo.RANDOM, 50);

        assertEquals(50, gameInfo.getSeed());
    }

    @Test
    public void setSeed() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setSeed(50);

        assertEquals(50, gameInfo.getSeed());
    }

    @Test
    public void getType() {
        GameInfo gameInfo = new GameInfo(GameInfo.WINNABLE_HARD, 25);

        assertEquals(GameInfo.WINNABLE_HARD, gameInfo.getType());
    }

    @Test
    public void setType() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setType(GameInfo.WINNABLE_NORMAL);

        assertEquals(GameInfo.WINNABLE_NORMAL, gameInfo.getType());
    }
}