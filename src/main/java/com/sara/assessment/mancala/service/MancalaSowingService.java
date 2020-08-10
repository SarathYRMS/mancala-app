package com.sara.assessment.mancala.service;

import com.sara.assessment.mancala.model.MancalaGame;

public interface MancalaSowingService {
    MancalaGame sow(MancalaGame mancalaGame, int pitIndex);
}
