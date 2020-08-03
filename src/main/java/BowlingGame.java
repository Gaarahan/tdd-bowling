import java.util.*;

/**
 * @author gaarahan
 */
public class BowlingGame {
  private int frame;
  private final ArrayList<ArrayList<Integer>> scoreList;
  private final static int FULL_MARKS_SCORE = 10;
  private final static int MAX_FRAME_TIMES = 10;

  public BowlingGame() {
    this.frame = 0;
    this.scoreList = new ArrayList<>();
    for (int framePoint = 0; framePoint < MAX_FRAME_TIMES; framePoint++) {
      this.scoreList.add(new ArrayList<>());
    }
  }

  public void throwBall(int curScore) {
    if (this.frame < 9) {
      this.resolveFirstNineThrow(curScore);
    } else if (this.frame == 9) {
      this.resolveTheTenthThrow(curScore);
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
      if (firstScore == FULL_MARKS_SCORE) {
        score += firstScore + this.getNextTwoThrowScore(framePoint);
      } else {
        int secondScore = curFrame.get(1);
        if (firstScore + secondScore == FULL_MARKS_SCORE) {
          score += FULL_MARKS_SCORE + this.scoreList.get(framePoint + 1).get(0);
        } else {
          score += firstScore + secondScore;
        }
      }
    }

    // count the tenth throw
    ArrayList<Integer> theTenthThrow = this.scoreList.get(9);
    int firstScore = theTenthThrow.get(0);
    int secondScore = theTenthThrow.get(1);
    if (firstScore == FULL_MARKS_SCORE) {
      score += firstScore + secondScore + theTenthThrow.get(2);
    } else if (firstScore + secondScore == FULL_MARKS_SCORE) {
      score += FULL_MARKS_SCORE + theTenthThrow.get(2);
    } else {
      score += firstScore + secondScore;
    }

    return score;
  }

  private int getNextTwoThrowScore(int curFramePoint) {
    ArrayList<Integer> nextFrame = this.scoreList.get(curFramePoint + 1);
    int nextScore = nextFrame.get(0);
    int nextNextScore;
    if (nextScore == FULL_MARKS_SCORE) {
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
  
  private ArrayList<Integer> getCurFrame () {
    return this.scoreList.get(this.frame);
  }

  private void resolveFirstNineThrow(int curScore) {
    // first throw
    if (scoreList.get(this.frame).size() == 0) {
      // strike
      if (curScore == FULL_MARKS_SCORE) {
        scoreList.get(this.frame).add(curScore);
        // no second throw chance
        this.frame++;
      } else {
        this.getCurFrame().add(curScore);
      }
    } else {
      // second chance
      this.getCurFrame().add(curScore);
      this.frame++;
    }
  }

  private void resolveTheTenthThrow(int curScore) {
    // first throw
    if (this.getCurFrame().size() == 0) {
      this.getCurFrame().add(curScore);
    }
    // second throw
    else if (this.getCurFrame().size() == 1) {
      if (this.getCurFrame().get(0) == FULL_MARKS_SCORE) {
        this.getCurFrame().add(curScore);
      } else if (this.getCurFrame().get(0) + curScore == FULL_MARKS_SCORE) {
        this.getCurFrame().add(curScore);
      } else {
        this.getCurFrame().add(curScore);
        this.frame++;
      }
    }
    // third throw
    else if (this.getCurFrame().size() == 2) {
      this.getCurFrame().add(curScore);
      this.frame++;
    }
  }
}
