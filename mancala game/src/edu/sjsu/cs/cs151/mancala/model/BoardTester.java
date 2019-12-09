package edu.sjsu.cs.cs151.mancala.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoardTester {
    @Test
    public void testBoard() {
        Board testBoard = new Board();
        Hole h = testBoard.getHoleAt(13);
        h = testBoard.getNextHole(h);
        assertEquals(h.getIndex(), 0); // should pass
    }

    @Test
    void testGetHoleAt() {
        Board testBoard = new Board();
        Hole h = testBoard.getHoleAt(0);
        Hole h1 = testBoard.getHoleAt(13);
        assertEquals(h, h1);        // should fail
    }

    @Test
    void testGetNextHole() {
        Board testBoard = new Board();
        Hole h = testBoard.getHoleAt(1);
        while (h.getIndex() != 0) {
            h = testBoard.getNextHole(h);
        }
        assertEquals(h.getIndex(), 0); // should pass
    }

    @Test
    void testIsStore() {
        Board testBoard = new Board();
        Hole h = testBoard.getHoleAt(6);
        assertEquals(testBoard.checkIfStore(h), true); // should pass
    }

    //This functionality has been removed
/*
    @Test
    void testGetLast_hole_index() {
        Board b = new Board();
        int index = b.getLastHoleIndex();
        assertTrue("Last index is less than 0", 0 < index);
        assertTrue("Last index is greater than 13", 13 > index);
    }
    @Test
    void testSetLastHoleIndex() {
        Board b = new Board();
        b.setLastHoleIndex(1);
        int index = b.getLastHoleIndex();
        assertEquals(1, index);
    }
   */
}
