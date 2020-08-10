package com.sara.assessment.mancala.service;

import com.sara.assessment.mancala.model.MancalaGame;

public interface MancalaService {

    MancalaGame createNewGame(Integer pitStones);
    MancalaGame loadGameIfExists(String id);
    MancalaGame updateGameIfExists(MancalaGame mancalaGame);
}
