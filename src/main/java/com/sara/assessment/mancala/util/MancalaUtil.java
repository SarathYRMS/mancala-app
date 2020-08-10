package com.sara.assessment.mancala.util;

import com.sara.assessment.mancala.exception.MancalaInvalidRequestException;
import com.sara.assessment.mancala.model.PlayerTurn;
import com.sara.assessment.mancala.constant.MancalaConstant;
import org.springframework.util.StringUtils;

public class MancalaUtil {

    public static void validatePlayerAndPitIndex(PlayerTurn playerTurn, Integer pitIndex) {
        String playerTurnMsg = null;
        if (playerTurn == PlayerTurn.PlayerOne && pitIndex > MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID)
            playerTurnMsg = "Please select Player One index, it should be between 1..6";
        if (playerTurn == PlayerTurn.PlayerTwo && pitIndex < MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID)
            playerTurnMsg = "Please select Player Two index, it should be between 8..13";
        if (!StringUtils.isEmpty(playerTurnMsg))
            throw new MancalaInvalidRequestException(playerTurnMsg);
    }

    public static boolean checkCurrentPit(int pitIndex, String player){
        return pitIndex < MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID && player.equals(PlayerTurn.PlayerOne.getTurn()) ||
                pitIndex > MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID && player.equals(PlayerTurn.PlayerTwo.getTurn());
    }

    public static PlayerTurn nextTurn(PlayerTurn currentTurn) {
        if (currentTurn == PlayerTurn.PlayerOne)
            return PlayerTurn.PlayerTwo;
        return PlayerTurn.PlayerOne;
    }
}
