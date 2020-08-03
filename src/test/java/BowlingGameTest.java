import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {
  @Test
  void should_got_score_0_when_non_pin_is_knockdown_in_one_line () {
    BowlingGame game = new BowlingGame();
    for (int frame = 1; frame <= 10; frame ++) {
      game.throwBall(0);
    }
    assertEquals(0, game.getScore());
  }

  @Test
  void should_got_correct_score_when_first_nine_throw_is_not_strike_or_spare () {
    BowlingGame game = new BowlingGame();
    for (int frame = 1; frame < 10; frame ++) {
      game.throwBall(5);
      game.throwBall(2);
    }

    // the tenth throw
    game.throwBall(0);
    game.throwBall(0);

    assertEquals(63, game.getScore());
  }

  @Test
  void should_got_correct_score_when_first_nine_is_strike () {
    BowlingGame game = new BowlingGame();
    for (int frame = 1; frame < 10; frame ++) {
      game.throwBall(10);
    }

    // the tenth throw
    game.throwBall(0);
    game.throwBall(0);

    assertEquals(240, game.getScore());
  }

  @Test
  void should_got_correct_score_when_first_nine_is_spare () {
    BowlingGame game = new BowlingGame();
    for (int frame = 1; frame < 10; frame ++) {
      game.throwBall(7);
      game.throwBall(3);
    }

    // the tenth throw
    game.throwBall(0);
    game.throwBall(0);

    assertEquals(146, game.getScore());
  }

  @Test
  void should_got_correct_score_when_the_tenth_strike () {
    BowlingGame game = new BowlingGame();
    for (int frame = 1; frame < 10; frame ++) {
      game.throwBall(0);
      game.throwBall(0);
    }

    // the tenth throw
    game.throwBall(10);
    game.throwBall(4);
    game.throwBall(5);

    assertEquals(19, game.getScore());
  }

  @Test
  void should_got_correct_score_when_the_tenth_spare () {
    BowlingGame game = new BowlingGame();
    for (int frame = 1; frame < 10; frame ++) {
      game.throwBall(0);
      game.throwBall(0);
    }

    // the tenth throw
    game.throwBall(6);
    game.throwBall(4);
    game.throwBall(5);

    assertEquals(15, game.getScore());
  }

  @Test
  void should_got_correct_score_when_the_tenth_not_strike_or_spare () {
    BowlingGame game = new BowlingGame();
    for (int frame = 1; frame < 10; frame ++) {
      game.throwBall(0);
      game.throwBall(0);
    }

    // the tenth throw
    game.throwBall(5);
    game.throwBall(4);

    assertEquals(9, game.getScore());
  }
}
