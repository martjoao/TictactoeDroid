package com.martins.joao.tictactoedroid.model;

import com.martins.joao.tictactoedroid.exception.FullCellException;

import java.util.Arrays;

/**
 * Created by João on 12/02/2017.
 */

public class TictactoeBoard {

    /**
     *
     * Tic Tac Toe Board
     *
     * Cells IDs are:
     *
     *  0 | 1 | 2
     *  ---------
     *  3 | 4 | 5
     *  ---------
     *  6 | 7 | 8
     *
     *  ALSO:
     *
     *  O,O | 0,1 | 0,2
     *  ---------------
     *  1,O | 1,1 | 1,2
     *  ---------------
     *  2,O | 2,1 | 2,2
     */

    private Player[] mCellOwner;

    private boolean mXTurn = true;

    /**
     * Constructor initializes cells to Player.NONE
     */
    public TictactoeBoard() {
        mCellOwner = new Player[9];
        Arrays.fill(mCellOwner, Player.NONE);
    }

    /**
     * Sets a cell on the board to Player.X or Player.Y
     *
     * @param pos   Position to be set
     * @return      Game winner. Null if game is not over. Player.NONE if game tied
     * @throws FullCellException    thwrown if cell is not empty
     */
    public Player play(int pos) throws FullCellException {
        if (isCellEmpty(pos)) {
            throw new FullCellException("Cell is already taken");
        }

        if (mXTurn) {
            setCellOwner(pos, Player.X);
        }
        else {
            setCellOwner(pos, Player.O);
        }

        mXTurn = !mXTurn;

        return scored(pos);
    }

    /**
     * Checks if cell at pos is part of a complete line/columns/diagonal
     * @param pos   Position to be checked
     * @return      Game winner. Null if game is not over. Player.NONE if game tied
     */
    public Player scored(int pos) {
        int xPos = pos / 3;
        int yPos = pos % 3;

        Player val = getCellOwner(pos);

        //If row is complete
        if (val == getCellOwner(xPos, 0) &&
            val == getCellOwner(xPos, 1)  &&
            val == getCellOwner(xPos, 2)) {
            return val;
        }

        //if column is complete
        if (val == getCellOwner(0, yPos) &&
            val == getCellOwner(1, yPos)  &&
            val == getCellOwner(2, yPos)) {
            return val;
        }

        //If in any diagonal
        if (xPos == yPos || xPos + yPos == 2) {
            //if main diagonal is complete
            if (val == getCellOwner(0, 0) &&
                val == getCellOwner(1, 1)  &&
                val == getCellOwner(2, 2)) {
                   return val;
            }
            //if secondary diagonal is complete
            if (val == getCellOwner(2, 0) &&
                val == getCellOwner(1, 1)  &&
                val == getCellOwner(0, 2)) {
                return val;
            }
        }

        if (isBoardFull())
            return Player.NONE;
        else
            return null;

    }


    public Player getCellOwner(int pos) {
        return mCellOwner[pos];
    }

    public void setCellOwner(int pos, Player owner) {
        mCellOwner[pos] = owner;
    }

    public boolean isCellEmpty(int pos) {
        return mCellOwner[pos] == Player.NONE;
    }

    public Player getCellOwner(int xpos, int ypos) {
        return getCellOwner(xpos * 3 + ypos);
    }

    public void setCellOwner(int xpos, int ypos, Player owner) {
        setCellOwner(xpos * 3 + ypos, owner);
    }

    public boolean isCellEmpty(int xpos, int ypos) {
        return isCellEmpty(xpos * 3 + ypos);
    }

    public boolean isBoardEmpty() {
        for (Player c : mCellOwner) {
            if (c != Player.NONE) {
                return false;
            }
        }
        return true;
    }

    public boolean isBoardFull() {
        for (Player c : mCellOwner) {
            if (c == Player.NONE) {
                return false;
            }
        }
        return true;
    }

    public boolean isXTurn() {
        return mXTurn;
    }

    public enum Player {
        NONE, X, O
    }
}
