package com.sara.assessment.mancala.service;

import com.sara.assessment.mancala.constant.MancalaConstant;
import com.sara.assessment.mancala.exception.MancalaNotFoundException;
import com.sara.assessment.mancala.model.MancalaGame;
import com.sara.assessment.mancala.repository.MancalaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MancalaServiceImpl implements MancalaService {

    private MancalaRepository mancalaRepository;

    @Override
    public MancalaGame createNewGame(Integer pitStones) {
        int totalPitStones = pitStones!=null && pitStones>0?pitStones:MancalaConstant.DEFAULT_PIT_STONES;
        log.info("Total pit stones: "+totalPitStones);
        MancalaGame newGame = new MancalaGame(totalPitStones);
        newGame.setInitialTotalStonesInPit(totalPitStones);
        newGame = mancalaRepository.save(newGame);
        log.info(newGame.displayBoard(newGame));
        return newGame;
    }

    //load the game instance from Cache if game instance exists
    @Cacheable(value = "mancalaGames", key = "#id", unless = "#result == null")
    public MancalaGame loadGameIfExists(String id) {
        Optional<MancalaGame> gameOptional = mancalaRepository.findById(id);
        if (!gameOptional.isPresent())
            throw new MancalaNotFoundException("Game id: " + id + " not found");
        return gameOptional.get();
    }

    //put the updated game instance into Cache as well as data store
    @CachePut(value = "mancalaGames", key = "#mancalaGame.id")
    public MancalaGame updateGameIfExists(MancalaGame mancalaGame) {
        return mancalaRepository.save(mancalaGame);
    }
}