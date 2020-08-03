import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {
  @Test
  void should_got_score_0_when_non_pin_is_knockdown_in_one_line () {
    BowlingGame game = new BowlingGame();
    for (int frame = 1; frame <= 10; frame ++) {
      game.throwBall(frame, 0);
    }
    assertEquals(0, game.score);
  }
}
