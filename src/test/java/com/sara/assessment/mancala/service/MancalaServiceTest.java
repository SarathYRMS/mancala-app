package com.sara.assessment.mancala.service;

import com.sara.assessment.mancala.model.MancalaGame;
import com.sara.assessment.mancala.model.MancalaPit;
import com.sara.assessment.mancala.repository.MancalaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@RunWith(SpringRunner.class)
@DirtiesContext
public class MancalaServiceTest {

    @MockBean
    private MancalaRepository mancalaRepository;
    private MancalaService mancalaService;

    private MancalaGame expectedGame = null;

    @Before
    public void setUp() {
        expectedGame = new MancalaGame("5d414dcd24e4990006e7c9d3", 6);

        Mockito.when(mancalaRepository.save(Mockito.any(MancalaGame.class))).thenReturn(new MancalaGame(6));
        mancalaService = new MancalaServiceImpl(mancalaRepository);
    }

    @Test
    public void testCreateNewGameInstance() {
        MancalaGame gameInstance = mancalaService.createNewGame(6);

        then(gameInstance.getPlayerTurn()).isNull();
        then(gameInstance.getPits().size()).isEqualTo(14);
        then(gameInstance.getPit(1).getStones()).isEqualTo(6);
        then(gameInstance.getPit(2).getStones()).isEqualTo(6);
        then(gameInstance.getPit(3).getStones()).isEqualTo(6);
        then(gameInstance.getPit(4).getStones()).isEqualTo(6);
        then(gameInstance.getPit(5).getStones()).isEqualTo(6);
        then(gameInstance.getPit(6).getStones()).isEqualTo(6);
        then(gameInstance.getPit(7).getStones()).isEqualTo(0);
        then(gameInstance.getPit(8).getStones()).isEqualTo(6);
        then(gameInstance.getPit(9).getStones()).isEqualTo(6);
        then(gameInstance.getPit(10).getStones()).isEqualTo(6);
        then(gameInstance.getPit(11).getStones()).isEqualTo(6);
        then(gameInstance.getPit(12).getStones()).isEqualTo(6);
        then(gameInstance.getPit(13).getStones()).isEqualTo(6);
        then(gameInstance.getPit(14).getStones()).isEqualTo(0);

        Mockito.verify(mancalaRepository, times(1)).save(Mockito.any(MancalaGame.class));
    }

    @Test
    public void testLoadGameInstanceFromRepository() {
        final List<MancalaPit> mancalaPitList = IntStream.range(1, 15)
                .mapToObj(value -> new MancalaPit(value, value % 7 != 0 ? 6 : 0))
                .collect(toList());

        expectedGame.setPits(mancalaPitList);

        Optional<MancalaGame> gameOptional = Optional.of(expectedGame);
        Mockito.when(mancalaRepository.findById("5d414dcd24e4990006e7c9d3")).thenReturn(gameOptional);
        MancalaGame gameInstance = mancalaService.loadGameIfExists("5d414dcd24e4990006e7c9d3");

        then(gameInstance.getId()).isEqualTo("5d414dcd24e4990006e7c9d3");
        then(gameInstance.getPlayerTurn()).isNull();
        then(gameInstance.getPits()).size().isEqualTo(14);
        then(gameInstance.getPit(1).getStones()).isEqualTo(6);
        then(gameInstance.getPit(2).getStones()).isEqualTo(6);
        then(gameInstance.getPit(3).getStones()).isEqualTo(6);
        then(gameInstance.getPit(4).getStones()).isEqualTo(6);
        then(gameInstance.getPit(5).getStones()).isEqualTo(6);
        then(gameInstance.getPit(6).getStones()).isEqualTo(6);
        then(gameInstance.getPit(7).getStones()).isEqualTo(0);
        then(gameInstance.getPit(8).getStones()).isEqualTo(6);
        then(gameInstance.getPit(9).getStones()).isEqualTo(6);
        then(gameInstance.getPit(10).getStones()).isEqualTo(6);
        then(gameInstance.getPit(11).getStones()).isEqualTo(6);
        then(gameInstance.getPit(12).getStones()).isEqualTo(6);
        then(gameInstance.getPit(13).getStones()).isEqualTo(6);
        then(gameInstance.getPit(14).getStones()).isEqualTo(0);

        Mockito.verify(mancalaRepository, times(1)).findById("5d414dcd24e4990006e7c9d3");
    }

    @Test
    public void testUpdatingGameInstanceIntoRepository() {

        final List<MancalaPit> mancalaPitList = IntStream.range(1, 15)
                .mapToObj(value -> new MancalaPit(value, value % 7 != 0 ? 6 : 0))
                .collect(toList());
        expectedGame.setPits(mancalaPitList);

        Mockito.when(mancalaRepository.save(expectedGame)).thenReturn(expectedGame);

        MancalaGame gameInstance = mancalaService.updateGameIfExists(expectedGame);

        then(gameInstance.getId()).isEqualTo("5d414dcd24e4990006e7c9d3");
        then(gameInstance.getPlayerTurn()).isNull();
        then(gameInstance.getPits()).size().isEqualTo(14);
        then(gameInstance.getPit(1).getStones()).isEqualTo(6);
        then(gameInstance.getPit(2).getStones()).isEqualTo(6);
        then(gameInstance.getPit(3).getStones()).isEqualTo(6);
        then(gameInstance.getPit(4).getStones()).isEqualTo(6);
        then(gameInstance.getPit(5).getStones()).isEqualTo(6);
        then(gameInstance.getPit(6).getStones()).isEqualTo(6);
        then(gameInstance.getPit(7).getStones()).isEqualTo(0);
        then(gameInstance.getPit(8).getStones()).isEqualTo(6);
        then(gameInstance.getPit(9).getStones()).isEqualTo(6);
        then(gameInstance.getPit(10).getStones()).isEqualTo(6);
        then(gameInstance.getPit(11).getStones()).isEqualTo(6);
        then(gameInstance.getPit(12).getStones()).isEqualTo(6);
        then(gameInstance.getPit(13).getStones()).isEqualTo(6);
        then(gameInstance.getPit(14).getStones()).isEqualTo(0);

        Mockito.verify(mancalaRepository, times(1)).save(expectedGame);
    }
}
