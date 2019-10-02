package mancala;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MarblesInHandTest {

	@Test
	void test() {
		Player test = new Player();
		int output = test.marblesInHand();
		assertEquals(4,output);
	}

}
