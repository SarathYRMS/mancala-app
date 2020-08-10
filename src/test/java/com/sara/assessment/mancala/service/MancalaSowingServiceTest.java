package com.sara.assessment.mancala.service;

import com.sara.assessment.mancala.exception.MancalaInvalidRequestException;
import com.sara.assessment.mancala.model.MancalaGame;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext
public class MancalaSowingServiceTest {

    private static final Integer DEFAULT_PIT_STONES = 6;
    private MancalaGame mancalaGame;
    private MancalaSowingService mancalaSowingService;
    private final String INITIAL_BOARD = "[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]";

    @Before
    public void setUpTest() {
        this.mancalaGame = new MancalaGame(DEFAULT_PIT_STONES);
        this.mancalaSowingService = new MancalaSowingServiceImpl();
    }
    @Test
    public void testGameCreation() {
        Assert.assertNotNull(mancalaGame);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo(INITIAL_BOARD);
    }

    @Test
    public void testSowOfSecondPitPlayerOne() {
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("TWO");
    }

    @Test
    public void testSowOfSecondPitPlayerTwo() {
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("ONE");
    }

    @Test
    public void testSowOfSixthPitPlayerOne() {
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 6);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("TWO");
    }

    @Test(expected = MancalaInvalidRequestException.class)
    public void testWrongTurnByPlayerOne() {
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 6);
        // This is a wrong turn therefore the output remains the same as well as player turn
        mancalaGame = mancalaSowingService.sow(mancalaGame, 1);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("TWO");
    }

    @Test
    public void testSowOfSixthPitPlayerTwo() {
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 6);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 13);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("ONE");
    }

    @Test(expected = MancalaInvalidRequestException.class)
    public void testWrongTurnByPlayerTwo() {
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 6);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 13);
        // This is a wrong turn therefore the output remains the same as well as player turn
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("ONE");
    }

    @Test
    public void testSowOfCollectingOppositePitStoneByPlayerOne() {
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 6);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 13);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 3);

        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:2, 10:9, 11:9, 12:8, 13:0, 14:2]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("TWO");

        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:0, 10:10, 11:10, 12:8, 13:0, 14:2]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("ONE");

        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:8, 2:0, 3:0, 4:9, 5:9, 6:2, 7:14, 8:10, 9:0, 10:10, 11:0, 12:8, 13:0, 14:2]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("TWO");
    }

    @Test
    public void testSowOfCollectingOppositePitStoneByPlayerTwo() {
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 6);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 13);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 3);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 8);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 1);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 9);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 2);
        mancalaGame = mancalaSowingService.sow(mancalaGame, 8);
        Assertions.assertThat(mancalaGame.getPits().toString()).isEqualTo("[1:0, 2:0, 3:3, 4:12, 5:0, 6:3, 7:15, 8:0, 9:0, 10:13, 11:2, 12:9, 13:1, 14:14]");
        Assertions.assertThat(mancalaGame.getPlayerTurn().getTurn()).isEqualTo("ONE");
    }
}
