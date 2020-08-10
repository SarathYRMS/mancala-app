package com.sara.assessment.mancala.controller;

import com.sara.assessment.mancala.constant.MancalaConstant;
import com.sara.assessment.mancala.exception.MancalaInvalidRequestException;
import com.sara.assessment.mancala.model.MancalaGame;
import com.sara.assessment.mancala.model.MancalaGameDto;
import com.sara.assessment.mancala.service.MancalaService;
import com.sara.assessment.mancala.service.MancalaSowingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/mancala")
public class MancalaController {

    private MancalaService mancalaService;
    private MancalaSowingService mancalaSowingService;

     /**
     * This method is allowed to create the Mancala game instance
     * @return the Mancala game response with game id
     */
    @PostMapping(value = {"/createGame", "/createGame/{pitStones}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MancalaGameDto> createNewGame(@PathVariable(required = false) Integer pitStones){
        MancalaGame gameInstance = mancalaService.createNewGame(pitStones);
        log.info("Game Id: "+gameInstance.getId());
        return ResponseEntity.ok(new MancalaGameDto(gameInstance.getId(), "Mancala game instance created successfully."));
    }

    /**
     * Get the mancala game by using the game id
     * @param gameId
     * @return Mancala game response
     */
    @GetMapping(value = "/getById/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MancalaGame> getGameById(@PathVariable(value = "gameId") String gameId){
        log.info("Game Id: "+gameId);
        return ResponseEntity.ok(mancalaService.loadGameIfExists(gameId));
    }

    /**
     * Sow the  mancala game by using the game id and pit id
     * @param gameId
     * @param pitIndex
     * @return Updated mancala game response
     */
    @PutMapping(value = "/sow/{gameId}/pits/{pitIndex}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MancalaGame> sowGame(@PathVariable(value = "gameId") String gameId,
                                               @PathVariable(value = "pitIndex") Integer pitIndex){
        if (pitIndex == null || pitIndex < 1
                || pitIndex >= MancalaConstant.PLAYER_TWO_PIT_HOUSE_ID || pitIndex == MancalaConstant.PLAYER_ONE_PIT_HOUSE_ID)
            throw new MancalaInvalidRequestException("Invalid pit index!. It should be between 1..6 or 8..13");

        MancalaGame mancalaGame = mancalaService.loadGameIfExists(gameId);

        // check if the selected pit index is empty
        final long pitIsAlreadyEmpty = mancalaGame.getPits().stream()
                .filter(mancalaPit -> mancalaPit.getId().equals(pitIndex) && mancalaPit.isEmpty())
                .count();

        if(pitIsAlreadyEmpty > 0)
            throw new MancalaInvalidRequestException("Pit is already empty, please select another Pit");

        mancalaGame = mancalaSowingService.sow(mancalaGame, pitIndex);
        mancalaService.updateGameIfExists(mancalaGame);

        return ResponseEntity.ok(mancalaGame);
    }
}

