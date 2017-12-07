package org.rpk.game.war.card;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Queue;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpk.game.war.Player;
import org.rpk.game.war.card.Card;
import org.rpk.game.war.card.Dealer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test.
 * 
 * @author R. Paul Kennedy 
 */
@Test
public class DealerTest {

	Dealer dealer;
	
	@Mock Player player1, player2;
	@Mock Queue<Card> cards1, cards2;
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
		dealer = new Dealer();
		when(player1.getHand()).thenReturn(cards1);
		when(player2.getHand()).thenReturn(cards2);
	}

	public void deal() {
		dealer.deal(player1, player2);
		Arrays.asList(player1, player2)
			.parallelStream()
			.forEach(player -> verify(player.getHand(), times(52/2))
				.add(any(Card.class)));
	}
}
