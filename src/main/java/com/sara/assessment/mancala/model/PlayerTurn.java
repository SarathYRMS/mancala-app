package com.sara.assessment.mancala.model;

import lombok.Getter;

@Getter
public enum PlayerTurn {

    PlayerOne("ONE"),
    PlayerTwo("TWO"),
    TIE("TIE");

    private String turn;

    PlayerTurn(String turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return "PlayerTurn{" +
                "turn='" + turn + '\'' +
                '}';
    }
}
