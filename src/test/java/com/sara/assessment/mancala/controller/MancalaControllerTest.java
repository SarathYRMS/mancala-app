package com.sara.assessment.mancala.controller;

import com.sara.assessment.mancala.exception.MancalaNotFoundException;
import com.sara.assessment.mancala.model.MancalaGame;
import com.sara.assessment.mancala.service.MancalaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@RunWith(SpringRunner.class)
@DirtiesContext
public class MancalaControllerTest {

    @MockBean
    MancalaService mancalaService;

    private final Resource jsonOfCreatedMancalaGame = new ClassPathResource("mancala-create-response.json");
    private final Resource jsonOfMancalaGameSowPit2AfterCreation = new ClassPathResource("mancala-sow-2.json");

    @Autowired
    private MockMvc mockMvc;

    private String asJson(Resource resource) throws Exception {
        return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
    }

    @Test
    public void testGameCreation() throws Exception {
        MancalaGame expectedGame = new MancalaGame("5d414dcd24e4990006e7c900", 6);

        Mockito.when(mancalaService.createNewGame(6)).thenReturn(expectedGame);

        mockMvc.perform(post("/mancala/createGame/6"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(expectedGame.getId()))
                .andExpect(content().json(asJson(jsonOfCreatedMancalaGame), false))
                .andReturn();

        Mockito.verify(mancalaService, times(1)).createNewGame(6);
    }

    @Test
    public void testGetGameById() throws Exception {
        MancalaGame expectedGame = new MancalaGame("5d414dcd24e4990006e7c900", 6);

        Mockito.when(mancalaService.loadGameIfExists("5d414dcd24e4990006e7c900"))
                .thenReturn(expectedGame);

        mockMvc.perform(get("/mancala/getById/5d414dcd24e4990006e7c900"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(expectedGame.getId()))
                .andReturn();
        Mockito.verify(mancalaService, times(1)).loadGameIfExists("5d414dcd24e4990006e7c900");
    }

    @Test
    public void testGetGameByWrongId() throws Exception{
        Mockito.when(mancalaService.loadGameIfExists("5d414dcd24e4990006e7c902"))
                .thenThrow(new MancalaNotFoundException("Game id 5d414dcd24e4990006e7c902 not found!"));

        mockMvc.perform(get("/mancala/getById/5d414dcd24e4990006e7c902/"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message").value("Game id 5d414dcd24e4990006e7c902 not found!"))
                .andReturn();
        Mockito.verify(mancalaService, times(1)).loadGameIfExists("5d414dcd24e4990006e7c902");
    }

    @Test
    public void testSowPitIndex2() throws Exception {
        MancalaGame expectedGame = new MancalaGame("5d414dcd24e4990006e7c900", 6);

        Mockito.when(mancalaService.loadGameIfExists("5d414dcd24e4990006e7c900"))
                .thenReturn(expectedGame);

        mockMvc.perform(put("/mancala/sow/5d414dcd24e4990006e7c900/pits/2"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(asJson(jsonOfMancalaGameSowPit2AfterCreation)))
                .andReturn();
        Mockito.verify(mancalaService, times(1)).loadGameIfExists("5d414dcd24e4990006e7c900");
    }

    @Test
    public void testSowingTheGameOfInvalidId() throws Exception {

        Mockito.when(mancalaService.loadGameIfExists("5d414dcd24e4990006e7c902"))
                .thenThrow(new MancalaNotFoundException("Game id 5d414dcd24e4990006e7c902 not found!"));

        mockMvc.perform(put("/mancala/sow/5d414dcd24e4990006e7c902/pits/2"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message").value("Game id 5d414dcd24e4990006e7c902 not found!"))
                .andReturn();
        Mockito.verify(mancalaService, times(1)).loadGameIfExists("5d414dcd24e4990006e7c902");
    }

    @Test
    public void testSowingTheGameAtInvalidPitIndex() throws Exception {
        mockMvc.perform(put("/mancala/sow/5d414dcd24e4990006e7c900/pits/7"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message").value("Invalid pit index!. It should be between 1..6 or 8..13"))
                .andReturn();
    }
}
