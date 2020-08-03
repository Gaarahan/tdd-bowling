import java.lang.reflect.Array;
import java.util.*;

/**
 * @author gaarahan
 */
public class BowlingGame {
  private int frame;
  private final ArrayList<ArrayList<Integer>> scoreList;

  public BowlingGame() {
    this.frame = 0;
    this.scoreList = new ArrayList<>();
    for (int framePoint = 0; framePoint < 10; framePoint++) {
      this.scoreList.add(new ArrayList<>());
    }
  }

  public void throwBall(int curScore) {
    // first nine frame
    if (this.frame < 9) {
      //first throw
      if (scoreList.get(this.frame).size() == 0) {
        // strike
        if (curScore == 10) {
          scoreList.get(this.frame).add(curScore);
          // no second throw chance
          this.frame++;
        } else {
          scoreList.get(this.frame).add(curScore);
        }
      } else {
        // second chance
        scoreList.get(this.frame).add(curScore);
        this.frame++;
      }
    } else if (this.frame == 9) {
      // first throw
      if (this.scoreList.get(this.frame).size() == 0) {
        scoreList.get(this.frame).add(curScore);
      }
      // second throw
      else if (this.scoreList.get(this.frame).size() == 1) {
        if (this.scoreList.get(this.frame).get(0) == 10) {
          this.scoreList.get(this.frame).add(curScore);
        } else if (this.scoreList.get(this.frame).get(0) + curScore == 10) {
          this.scoreList.get(this.frame).add(curScore);
        } else {
          this.scoreList.get(this.frame).add(curScore);
          this.frame++;
        }
      }
      // third throw
      else if (this.scoreList.get(this.frame).size() == 2) {
        this.scoreList.get(this.frame).add(curScore);
        this.frame++;
      }
    } else {
      System.out.println("You throw 10 frame,this bowling game is over.");
    }

  }

  public int getScore() {
    int score = 0;
     // count first nine throw
    for (int framePoint = 0; framePoint < 9; framePoint++) {
      ArrayList<Integer> curFrame = this.scoreList.get(framePoint);

      int firstScore = curFrame.get(0);
      if (firstScore == 10) {
        score += firstScore + this.getNextTwoThrowScore(framePoint);
      } else {
        int secondScore = curFrame.get(1);
        if (firstScore + secondScore == 10) {
          score += 10 + this.scoreList.get(framePoint + 1).get(0);
        } else {
          score += firstScore + secondScore;
        }
      }
    }

    // count the tenth throw
    ArrayList<Integer> theTenthThrow = this.scoreList.get(9);
    int firstScore = theTenthThrow.get(0);
    int secondScore = theTenthThrow.get(1);
    if (firstScore == 10) {
      score += firstScore + secondScore + theTenthThrow.get(2);
    } else if (firstScore + secondScore == 10) {
      score += 10 + theTenthThrow.get(2);
    } else {
      score += firstScore + secondScore;
    }

    return score;
  }

  private int getNextTwoThrowScore(int curFramePoint) {
    ArrayList<Integer> nextFrame = this.scoreList.get(curFramePoint + 1);
    int nextScore = nextFrame.get(0);
    int nextNextScore;
    if (nextScore == 10) {
      // next throw is the tenth throw
      if (this.frame + 1 == 9) {
        nextNextScore = nextFrame.get(1);
      } else {
        nextNextScore = this.scoreList.get(curFramePoint + 2).get(0);
      }
    } else {
      nextNextScore = nextFrame.get(1);
    }
    return nextScore + nextNextScore;
  }
}
