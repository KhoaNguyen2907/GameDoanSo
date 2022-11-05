import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import cybersoft.java18.backend.gamedoanso.Game;

@TestInstance(Lifecycle.PER_CLASS)
public class GameTest {
	private Game game;

	@BeforeAll
	public void setupTest() {
		game = new Game();
	}

	@Test
	public void ShouldGameWorkCorrectly() {
		assertDoesNotThrow(() -> game.start());
	}
}
