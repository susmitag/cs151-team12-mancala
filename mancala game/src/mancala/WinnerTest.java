package mancala;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class winnerTest {

	@Test
	void test() {
		Player test = new Player();
		boolean output = test.winner();
		assertEquals(false, output);
	}

}
