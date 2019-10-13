package mancala.model;
  
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTester {

    @Test
    void testBoard() {
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
        assertEquals(testBoard.isStore(h), true); // should pass
    }

}