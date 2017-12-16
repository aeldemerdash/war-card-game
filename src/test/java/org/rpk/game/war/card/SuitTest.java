package org.rpk.game.war.card;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.testng.annotations.Test;

/**
 * Unit test.
 * 
 * @author R. Paul Kennedy 
 */
@Test
public class SuitTest {

	public void symbolIsUnique() {
		Set<Character> symbolSet = new HashSet<>();
		IntStream.range(0, Suit.values().length).forEachOrdered(i -> {
			final char symbol = Suit.values()[i].symbol();
			assertFalse(symbolSet.contains(symbol));
			symbolSet.add(symbol);
		});
	}

	public void valueOf() {
		assertEquals(
			Stream.of(Suit.values())
			.parallel()
			.map(suit -> suit.name())
			.map(name -> Suit.valueOf(name))
			.filter(suit -> null == suit)
			.collect(Collectors.toList())
			.size(), 0);
		
	}
}
