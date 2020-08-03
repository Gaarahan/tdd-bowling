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
    for (int framePoint = 0; framePoint < 9; framePoint++) {
      score += this.getFirstNineFrameScore(framePoint);
    }
    return score + this.getTheTenthFrameScore();
  }

  private int getTheTenthFrameScore() {
    int score = 0;
    ArrayList<Integer> theTenthThrow = this.scoreList.get(9);
    int firstScore = theTenthThrow.get(0);
    int secondScore = theTenthThrow.get(1);
    if (firstScore == FULL_MARKS_SCORE) {
      score += firstScore + secondScore + theTenthThrow.get(2);
    } else if (firstScore + secondScore == FULL_MARKS_SCORE) {
      int thirdScore = theTenthThrow.get(2);
      score += FULL_MARKS_SCORE + thirdScore;
    } else {
      score += firstScore + secondScore;
    }
    return score;
  }

  private int getFirstNineFrameScore(int curFrameNum) {
    int score = 0;
    ArrayList<Integer> curFrame = this.scoreList.get(curFrameNum);

    int firstScore = curFrame.get(0);
    if (firstScore == FULL_MARKS_SCORE) {
      score += firstScore + this.getNextTwoThrowScore(curFrameNum);
    } else {
      int secondScore = curFrame.get(1);
      if (firstScore + secondScore == FULL_MARKS_SCORE) {
        score += FULL_MARKS_SCORE + this.scoreList.get(curFrameNum + 1).get(0);
      } else {
        score += firstScore + secondScore;
      }
    }
    return score;
  }

  private int getNextTwoThrowScore(int curFramePoint) {
    ArrayList<Integer> nextFrame = this.scoreList.get(curFramePoint + 1);
    int nextScore = nextFrame.get(0);
    int nextNextScore;
    if (nextScore == FULL_MARKS_SCORE) {
      // next frame is the tenth frame
      int nextFrameNum = curFramePoint + 1 + 1;
      if (nextFrameNum == MAX_FRAME_TIMES) {
        nextNextScore = nextFrame.get(1);
      } else {
        int nextNextFrameIndex = curFramePoint +2;
        nextNextScore = this.scoreList.get(nextNextFrameIndex).get(0);
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
        this.moveToNextFrame();
      } else {
        this.getCurFrame().add(curScore);
      }
    } else {
      // second chance
      this.getCurFrame().add(curScore);
      this.moveToNextFrame();
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
        this.moveToNextFrame();
      }
    }
    // third throw
    else if (this.getCurFrame().size() == 2) {
      this.getCurFrame().add(curScore);
      this.moveToNextFrame();
    }
  }

  private void moveToNextFrame () {
    this.frame++;
  }
}
