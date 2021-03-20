import java.awt.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum CPUDifficulty {
    Easy, // Easy Difficulty, the bot picks moves at random
    Hard, // Bot uses a super duper sophisticated AI to make moves.
}

public class TicTacToeCPU {
    CPUDifficulty difficulty;

    public TicTacToeCPU(CPUDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Point getMove(String[][] board) {
        if (difficulty == CPUDifficulty.Easy) {
            return randomMove(board);
        } else {
            return calculatedMove(board);
        }
    }

    private Point calculatedMove(String[][] board) {
        return null;
    }

    private Point randomMove(String[][] board) {
        List<Point> possibleSlots = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                String slot = board[row][column];
                if (slot.equals("")) {
                    possibleSlots.add(new Point(column, row));
                }
            }
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(possibleSlots.size());
        return possibleSlots.get(randomIndex);
    }
}
