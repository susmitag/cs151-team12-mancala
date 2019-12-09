package edu.sjsu.cs.cs151.mancala.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class WinnerTest {

    @Test
    void test() {
        Player test = new Player();
        boolean output = test.winner();
        assertEquals(false, output);
    }

}
