package com.martins.joao.tictactoedroid.exception;

/**
 * Created by João on 12/02/2017.
 */

public class GameAlreadyFinishedException extends Exception {
    public GameAlreadyFinishedException(String message) {
        super(message);
    }
}
