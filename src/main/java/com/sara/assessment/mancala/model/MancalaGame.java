package com.sara.assessment.mancala.model;

import com.sara.assessment.mancala.exception.MancalaException;
import com.sara.assessment.mancala.exception.MancalaInvalidRequestException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sara.assessment.mancala.constant.MancalaConstant.*;
import static com.sara.assessment.mancala.constant.MancalaConstant.EMPTY;
import static java.util.stream.Collectors.toList;

@Document(collection = "games")
@Getter
@Setter
@NoArgsConstructor
public class MancalaGame implements Serializable {

    @Id
    private String id;
    private List<MancalaPit> pits;
    private PlayerTurn playerTurn;
    private int currentPitIndex;
    @JsonIgnore
    private int initialTotalStonesInPit;

    public MancalaGame(int pitStones) {

        pits = IntStream.range(1, 15)
                .mapToObj(value -> new MancalaPit(value, ((value % 7) != EMPTY_PIT) ? pitStones : EMPTY))
                .collect(toList());
    }

    public MancalaGame(String id, Integer pitStones) {
        this(pitStones);
        this.id = id;
    }

    public MancalaPit getPit(Integer pitIndex) throws MancalaInvalidRequestException {
        try {
            return this.pits.get(pitIndex - 1);
        } catch (Exception e) {
            throw new MancalaInvalidRequestException("Invalid index: " + pitIndex + " has given");
        }
    }

    public PlayerTurn decideWinnerAndEndGame(MancalaGame game){
        PlayerTurn player = null;
        List<MancalaPit> p1NonEmptyPits = game.getPits().stream()
                .filter(pit -> pit.getId() < PLAYER_ONE_PIT_HOUSE_ID && !pit.isEmpty())
                .collect(Collectors.toList());
        if(p1NonEmptyPits.isEmpty()){
            addAndClearPits(game);
            player = decideWinner(game);
        }else {
            List<MancalaPit> p2NonEmptyPits = game.getPits().stream()
                    .filter(pit -> (pit.getId() > PLAYER_ONE_PIT_HOUSE_ID && pit.getId() < PLAYER_TWO_PIT_HOUSE_ID) && !pit.isEmpty())
                    .collect(Collectors.toList());
            if(p2NonEmptyPits.isEmpty()){
                addAndClearPits(game);
                player = decideWinner(game);
            }
        }
        return player;
    }

    private void addAndClearPits(MancalaGame game) {
        AtomicInteger p2TotalStones = new AtomicInteger();
        p2TotalStones.addAndGet(game.getPit(PLAYER_TWO_PIT_HOUSE_ID).getStones());
        IntStream.range(8,14).forEach(index -> {
            p2TotalStones.addAndGet(game.getPit(index).getStones());
            game.getPit(index).clear();
        });
        game.getPit(PLAYER_TWO_PIT_HOUSE_ID).setStones(p2TotalStones.get());
        AtomicInteger p1TotalStones = new AtomicInteger();
        p1TotalStones.addAndGet(game.getPit(PLAYER_ONE_PIT_HOUSE_ID).getStones());
        IntStream.range(1,7).forEach(index -> {
            p1TotalStones.addAndGet(game.getPit(index).getStones());
            game.getPit(index).clear();
        });
        game.getPit(PLAYER_ONE_PIT_HOUSE_ID).setStones(p1TotalStones.get());
    }

    private PlayerTurn decideWinner(MancalaGame game) {
        Integer p1TotalStones = game.getPit(PLAYER_ONE_PIT_HOUSE_ID).getStones();
        Integer p2TotalStones = game.getPit(PLAYER_TWO_PIT_HOUSE_ID).getStones();
        if(p1TotalStones.equals(p2TotalStones)){
            return PlayerTurn.TIE;
        }else if(p1TotalStones > p2TotalStones){
            return PlayerTurn.PlayerOne;
        }else
            return PlayerTurn.PlayerTwo;
    }

    public String displayBoard(MancalaGame game) {
        String jsonBoardAsString;
        try {
            jsonBoardAsString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(game);
        } catch (Exception e){
            throw new MancalaException(String.format("%s%s","Json conversion error ",e.getMessage()));
        }
        return jsonBoardAsString;
    }
}
