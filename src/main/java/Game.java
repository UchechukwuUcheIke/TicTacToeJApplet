public class Game {
    private String[][] board = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
    };
    private int player = 1;


    public void makeMove(int x, int y) {
        assert board[y][x].equals("");
        String marker = (player == 1) ? "X" : "O";

        board[y][x] = marker;

        player = (player == 1) ? 2 : 1;
    }

    public int getPlayer() {
        return player;
    }

    public boolean checkWin() {
        boolean result = false;

        // Check horizontally
        for (int i = 0 ; i < 3; i++) {
            result = result || (board[i][0].equals(board[i][1]) & board[i][1].equals(board[i][2])) & (board[i][0].equals("X") || board[i][0].equals("O"));
        }

        // Check diagonally
        result = result || (board[0][0].equals(board[1][1]) &
                            board[1][1].equals(board[2][2])) &
                            (board[1][1].equals("X") || board[1][1].equals("O"));
        result = result || (board[0][2].equals(board[1][1]) &
                            board[1][1].equals(board[2][0])) &
                            (board[1][1].equals("X") || board[1][1].equals("O"));

        // Check vertically
        result = result || (board[0][0].equals(board[1][0]) &
                            board[1][0].equals(board[2][0])) &
                            (board[0][0].equals("X") || board[0][0].equals("O"));

        result = result || (board[0][1].equals(board[1][1]) &
                            board[1][1].equals(board[2][1])) &
                            (board[1][1].equals("X") || board[1][1].equals("O"));

        result = result || (board[0][2].equals(board[1][2]) &
                            board[1][2].equals(board[2][2])) &
                            (board[2][2].equals("X") || board[2][2].equals("O"));

        return result;
    }

    public void resetGame() {
        String[][] newBoard = {
                {"", "", ""},
                {"", "", ""},
                {"", "", ""}
        };

        board = newBoard;
    }

    public String[][] getBoard() {
        return board;
    }

    public String getMarkerAtSegment(int row, int column) {
        return board[column][row];
    }

    public boolean boardFull() {
        // TODO: Create a method returns whether or not the board is full

        return false;
    }
}