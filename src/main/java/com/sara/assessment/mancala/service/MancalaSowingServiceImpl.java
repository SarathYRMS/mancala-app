package com.sara.assessment.mancala.service;


import com.sara.assessment.mancala.model.MancalaGame;
import com.sara.assessment.mancala.model.MancalaPit;
import com.sara.assessment.mancala.model.PlayerTurn;
import com.sara.assessment.mancala.constant.MancalaConstant;
import com.sara.assessment.mancala.util.MancalaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.IntStream;

@Slf4j
@Service
public class MancalaSowingServiceImpl implements MancalaSowingService {

    @Override
    public MancalaGame sow(MancalaGame mancalaGame, int pitIndex) {
        if (mancalaGame.getPlayerTurn() == null) {
            if (pitIndex < MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID)
                mancalaGame.setPlayerTurn(PlayerTurn.PlayerOne);
            else
                mancalaGame.setPlayerTurn(PlayerTurn.PlayerTwo);
        }

        MancalaUtil.validatePlayerAndPitIndex(mancalaGame.getPlayerTurn(), pitIndex);

        MancalaPit selectedPit = mancalaGame.getPit(pitIndex);
        int stones = selectedPit.getStones();
        if (stones == MancalaConstant.EMPTY)
            return mancalaGame;
        selectedPit.setStones(MancalaConstant.EMPTY);
        mancalaGame.setCurrentPitIndex(pitIndex);
        IntStream.range(0, stones - 1).forEach(index -> sowRight(mancalaGame, false));
        sowRight(mancalaGame, true);
        int currentPitIndex = mancalaGame.getCurrentPitIndex();
        if (currentPitIndex != MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID && currentPitIndex != MancalaConstant.PLAYER_TWO_PIT_HOUSE_ID)
            mancalaGame.setPlayerTurn(MancalaUtil.nextTurn(mancalaGame.getPlayerTurn()));
        log.info(mancalaGame.displayBoard(mancalaGame));
        return mancalaGame;
    }

    private void sowRight(MancalaGame game, boolean lastStone) {
        int currentPitIndex = game.getCurrentPitIndex() % MancalaConstant.TOTAL_PITS + 1;
        PlayerTurn playerTurn = game.getPlayerTurn();
        if ((currentPitIndex == MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID && playerTurn == PlayerTurn.PlayerTwo) ||
                (currentPitIndex == MancalaConstant.PLAYER_TWO_PIT_HOUSE_ID && playerTurn == PlayerTurn.PlayerOne))
            currentPitIndex = currentPitIndex % MancalaConstant.TOTAL_PITS + 1;
        game.setCurrentPitIndex(currentPitIndex);

        MancalaPit targetPit = game.getPit(currentPitIndex);
        if (!lastStone || currentPitIndex == MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID || currentPitIndex == MancalaConstant.PLAYER_TWO_PIT_HOUSE_ID) {
            targetPit.sow();
            return;
        }

        MancalaPit oppositePit = game.getPit(MancalaConstant.TOTAL_PITS - currentPitIndex);
        if (targetPit.isEmpty() && MancalaUtil.checkCurrentPit(targetPit.getId(), game.getPlayerTurn().getTurn())) {
            Integer oppositeStones = oppositePit.getStones();
            oppositePit.clear();
            Integer pitHouseIndex = currentPitIndex < MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID ? MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID : MancalaConstant.PLAYER_TWO_PIT_HOUSE_ID;
            MancalaPit pitHouse = game.getPit(pitHouseIndex);
            pitHouse.addStones(oppositeStones + 1);
            PlayerTurn winner = game.decideWinnerAndEndGame(game);
            if(!StringUtils.isEmpty(winner)){
                if(PlayerTurn.TIE.equals(winner)){
                    log.info("It's a TIE");
                    return;
                }
                log.info("Winner is Player {}",winner.getTurn());
                return;
            }else{
                return;
            }
        }
        targetPit.sow();
    }
}