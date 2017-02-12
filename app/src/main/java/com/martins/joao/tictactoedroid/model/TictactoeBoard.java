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

    private CellOwner[] mCells;

    private boolean mXTurn = true;

    /**
     * Constructor initializes cells to CellOwner.EMPTY
     */
    public TictactoeBoard() {
        mCells = new CellOwner[9];
        Arrays.fill(mCells, CellOwner.EMPTY);
    }

    /**
     * Sets a cell on the board to CellOwner.X or CellOwner.Y
     *
     * @param pos   Position to be set
     * @return      true if the current player wins the game
     * @throws FullCellException    thwrown if cell is not empty
     */
    public boolean play(int pos) throws FullCellException {
        if (isCellEmpty(pos)) {
            throw new FullCellException("Cell is already taken");
        }

        if (mXTurn) {
            setCellOwner(pos, CellOwner.X);
        }
        else {
            setCellOwner(pos, CellOwner.O);
        }

        mXTurn = !mXTurn;

        return scored(pos);
    }

    /**
     * Checks if cell at pos is part of a complete line/columns/diagonal
     * @param pos   Position to be checked
     * @return      true if player has scored
     */
    public boolean scored(int pos) {
        int xPos = pos / 3;
        int yPos = pos % 3;

        CellOwner val = getCellOwner(pos);

        //If row is complete
        if (val == getCellOwner(xPos, 0) &&
            val == getCellOwner(xPos, 1)  &&
            val == getCellOwner(xPos, 2)) {
            return true;
        }

        //if column is complete
        if (val == getCellOwner(0, yPos) &&
            val == getCellOwner(1, yPos)  &&
            val == getCellOwner(2, yPos)) {
            return true;
        }

        //If in any diagonal
        if (xPos == yPos || xPos + yPos == 2) {
            //if main diagonal is complete
            if (val == getCellOwner(0, 0) &&
                val == getCellOwner(1, 1)  &&
                val == getCellOwner(2, 2)) {
                   return true;
            }
            //if secondary diagonal is complete
            if (val == getCellOwner(2, 0) &&
                val == getCellOwner(1, 1)  &&
                val == getCellOwner(0, 2)) {
                return true;
            }
        }
        return false;

    }


    public CellOwner getCellOwner(int pos) {
        return mCells[pos];
    }

    public void setCellOwner(int pos, CellOwner owner) {
        mCells[pos] = owner;
    }

    public boolean isCellEmpty(int pos) {
        return mCells[pos] == CellOwner.EMPTY;
    }

    public CellOwner getCellOwner(int xpos, int ypos) {
        return getCellOwner(xpos * 3 + ypos);
    }

    public void setCellOwner(int xpos, int ypos, CellOwner owner) {
        setCellOwner(xpos * 3 + ypos, owner);
    }

    public boolean isCellEmpty(int xpos, int ypos) {
        return isCellEmpty(xpos * 3 + ypos);
    }

    public boolean isBoardEmpty() {
        for (CellOwner c : mCells) {
            if (c != CellOwner.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public boolean isBoardFull() {
        for (CellOwner c : mCells) {
            if (c == CellOwner.EMPTY) {
                return false;
            }
        }
        return true;
    }

    public boolean isXTurn() {
        return mXTurn;
    }

    public enum CellOwner {
        EMPTY, X, O
    }
}
