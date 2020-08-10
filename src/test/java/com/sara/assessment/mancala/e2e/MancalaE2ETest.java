package com.sara.assessment.mancala.e2e;

import com.sara.assessment.mancala.model.PlayerTurn;
import com.sara.assessment.mancala.repository.MancalaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MancalaE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MancalaRepository mancalaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testEntireFlowForDeafultOrInitWith6Stones() throws Exception{
        //Create game instance
        final MvcResult mvcResult = mockMvc.perform(post("/mancala/createGame"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.message").value("Mancala game instance created successfully."))
                .andReturn();

        final JsonNode jsonNode = objectMapper.readTree(mvcResult.getResponse().getContentAsByteArray());
        final String gameId = jsonNode.at("/id").textValue();
        // checking if data exists in db
        assertTrue(mancalaRepository.findById(gameId).isPresent());

        //1
        mockMvc.perform(put(format("/mancala/sow/%s/pits/1", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andExpect(jsonPath("$.playerTurn").value("PlayerOne"))
                .andReturn();
        //2
        mockMvc.perform(put(format("/mancala/sow/%s/pits/4", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //3
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //4
        mockMvc.perform(put(format("/mancala/sow/%s/pits/5", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //5
        mockMvc.perform(put(format("/mancala/sow/%s/pits/10", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //6
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //7
        mockMvc.perform(put(format("/mancala/sow/%s/pits/10", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //8
        mockMvc.perform(put(format("/mancala/sow/%s/pits/5", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //9
        mockMvc.perform(put(format("/mancala/sow/%s/pits/12", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //10
        mockMvc.perform(put(format("/mancala/sow/%s/pits/3", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //11
        mockMvc.perform(put(format("/mancala/sow/%s/pits/13", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //12
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //13
        mockMvc.perform(put(format("/mancala/sow/%s/pits/12", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //14
        mockMvc.perform(put(format("/mancala/sow/%s/pits/2", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //15
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //16
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //17
        mockMvc.perform(put(format("/mancala/sow/%s/pits/3", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //18
        mockMvc.perform(put(format("/mancala/sow/%s/pits/13", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //19
        mockMvc.perform(put(format("/mancala/sow/%s/pits/12", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //20
        mockMvc.perform(put(format("/mancala/sow/%s/pits/10", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //21
        mockMvc.perform(put(format("/mancala/sow/%s/pits/13", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //22
        mockMvc.perform(put(format("/mancala/sow/%s/pits/2", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //23
        mockMvc.perform(put(format("/mancala/sow/%s/pits/12", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //24
        mockMvc.perform(put(format("/mancala/sow/%s/pits/5", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //25
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //26
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //27
        mockMvc.perform(put(format("/mancala/sow/%s/pits/4", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //28
        mockMvc.perform(put(format("/mancala/sow/%s/pits/10", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //29
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //30
        mockMvc.perform(put(format("/mancala/sow/%s/pits/5", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();

        assertEquals(PlayerTurn.PlayerTwo, mancalaRepository.findById(gameId).get().getPlayerTurn());
    }

    @Test
    public void testInitializeGameWith4StonesEach() throws Exception{
        //Create game instance
        final MvcResult mvcResult = mockMvc.perform(post("/mancala/createGame/4"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.message").value("Mancala game instance created successfully."))
                .andReturn();

        final JsonNode jsonNode = objectMapper.readTree(mvcResult.getResponse().getContentAsByteArray());
        final String gameId = jsonNode.at("/id").textValue();
        // checking if data exists in db
        assertTrue(mancalaRepository.findById(gameId).isPresent());

        //1
        mockMvc.perform(put(format("/mancala/sow/%s/pits/3", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andExpect(jsonPath("$.playerTurn").value("PlayerOne"))
                .andReturn();
        //2
        mockMvc.perform(put(format("/mancala/sow/%s/pits/4", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //3
        mockMvc.perform(put(format("/mancala/sow/%s/pits/10", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //4
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //5
        mockMvc.perform(put(format("/mancala/sow/%s/pits/11", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //6
        mockMvc.perform(put(format("/mancala/sow/%s/pits/3", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //7
        mockMvc.perform(put(format("/mancala/sow/%s/pits/12", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //8
        mockMvc.perform(put(format("/mancala/sow/%s/pits/1", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //8
        mockMvc.perform(put(format("/mancala/sow/%s/pits/5", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //10
        mockMvc.perform(put(format("/mancala/sow/%s/pits/12", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //11
        mockMvc.perform(put(format("/mancala/sow/%s/pits/2", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //12
        mockMvc.perform(put(format("/mancala/sow/%s/pits/11", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //13
        mockMvc.perform(put(format("/mancala/sow/%s/pits/4", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //14
        mockMvc.perform(put(format("/mancala/sow/%s/pits/5", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //15
        mockMvc.perform(put(format("/mancala/sow/%s/pits/3", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //16
        mockMvc.perform(put(format("/mancala/sow/%s/pits/8", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //17
        mockMvc.perform(put(format("/mancala/sow/%s/pits/1", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //18
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //19
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //20
        mockMvc.perform(put(format("/mancala/sow/%s/pits/12", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //21
        mockMvc.perform(put(format("/mancala/sow/%s/pits/5", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //22
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //23
        mockMvc.perform(put(format("/mancala/sow/%s/pits/4", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //24
        mockMvc.perform(put(format("/mancala/sow/%s/pits/10", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //25
        mockMvc.perform(put(format("/mancala/sow/%s/pits/12", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //26
        mockMvc.perform(put(format("/mancala/sow/%s/pits/3", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //27
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //28
        mockMvc.perform(put(format("/mancala/sow/%s/pits/2", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //29
        mockMvc.perform(put(format("/mancala/sow/%s/pits/13", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //30
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //31
        mockMvc.perform(put(format("/mancala/sow/%s/pits/5", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //32
        mockMvc.perform(put(format("/mancala/sow/%s/pits/4", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //33
        mockMvc.perform(put(format("/mancala/sow/%s/pits/13", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //34
        mockMvc.perform(put(format("/mancala/sow/%s/pits/12", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //35
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //36
        mockMvc.perform(put(format("/mancala/sow/%s/pits/3", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //37
        mockMvc.perform(put(format("/mancala/sow/%s/pits/8", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //38
        mockMvc.perform(put(format("/mancala/sow/%s/pits/2", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();

        assertEquals(PlayerTurn.PlayerTwo, mancalaRepository.findById(gameId).get().getPlayerTurn());
    }

    @Test
    public void testEntireFlowOfPlayerTwoWinnerWith6StonesInEachPit() throws Exception{
        //Create game instance
        final MvcResult mvcResult = mockMvc.perform(post("/mancala/createGame"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Mancala game instance created successfully."))
                .andReturn();

        final JsonNode jsonNode = objectMapper.readTree(mvcResult.getResponse().getContentAsByteArray());
        final String gameId = jsonNode.at("/id").textValue();
        // checking if data exists in db
        assertTrue(mancalaRepository.findById(gameId).isPresent());

        //1
        mockMvc.perform(put(format("/mancala/sow/%s/pits/8", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andExpect(jsonPath("$.playerTurn").value("PlayerTwo"))
                .andReturn();
        //2
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //3
        mockMvc.perform(put(format("/mancala/sow/%s/pits/4", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //4
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //5
        mockMvc.perform(put(format("/mancala/sow/%s/pits/1", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //6
        mockMvc.perform(put(format("/mancala/sow/%s/pits/10", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //7
        mockMvc.perform(put(format("/mancala/sow/%s/pits/1", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //8
        mockMvc.perform(put(format("/mancala/sow/%s/pits/8", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //9
        mockMvc.perform(put(format("/mancala/sow/%s/pits/2", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //10
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //11
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //12
        mockMvc.perform(put(format("/mancala/sow/%s/pits/11", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //13
        mockMvc.perform(put(format("/mancala/sow/%s/pits/2", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //14
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //15
        mockMvc.perform(put(format("/mancala/sow/%s/pits/4", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //16
        mockMvc.perform(put(format("/mancala/sow/%s/pits/1", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //17
        mockMvc.perform(put(format("/mancala/sow/%s/pits/8", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //18
        mockMvc.perform(put(format("/mancala/sow/%s/pits/2", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //19
        mockMvc.perform(put(format("/mancala/sow/%s/pits/9", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //20
        mockMvc.perform(put(format("/mancala/sow/%s/pits/6", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();
        //21
        mockMvc.perform(put(format("/mancala/sow/%s/pits/8", gameId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gameId))
                .andReturn();

        assertEquals(PlayerTurn.PlayerOne, mancalaRepository.findById(gameId).get().getPlayerTurn());
    }
}
