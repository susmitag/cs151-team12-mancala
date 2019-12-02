package edu.sjsu.cs.cs151.mancala.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HoleTest {
	
	@Test
	void testAddMarble() {
		Hole h = new Hole();
		h.addMarble();
		int marbleCount = h.getMarblecount();
		assertEquals(1 + Hole.INITIAL_HOLE_MARBLE_COUNT, marbleCount);
	}
	
	@Test
	void testRemoveMarble() {
		Hole h = new Hole();
		h.removeMarbles();
		int marbleCount = h.getMarblecount();
		assertEquals(0, marbleCount);
	}

	@Test
	void testGetIndex() {
		Hole h = new Hole();
		int index = h.getIndex();
		assertEquals(Hole.INITIAL_HOLE_INDEX, index);
	}	
	
	@Test
	void testSetIndex() {
		Hole h = new Hole();
		h.setIndex(1);
		int index = h.getIndex();
		assertEquals(1, index);
	}	

}
