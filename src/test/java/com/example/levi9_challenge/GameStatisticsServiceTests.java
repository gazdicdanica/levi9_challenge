package com.example.levi9_challenge;

import com.example.levi9_challenge.dto.StatsDTO;
import com.example.levi9_challenge.model.GameStatistic;
import com.example.levi9_challenge.model.Player;
import com.example.levi9_challenge.repo.GameStatisticsRepo;
import com.example.levi9_challenge.service.impl.GameStatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = GameStatisticsService.class)
public class GameStatisticsServiceTests {

    @Autowired
    private GameStatisticsService gameStatisticsService;

    @MockBean
    private GameStatisticsRepo gameStatisticsRepo;

    @MockBean
    private GameStatistic gameStatistic;

    @MockBean
    private Player player;

    @Test
    void testGetByPlayer() {
        GameStatistic actual = gameStatisticsService.getByPlayer(player);

        assertEquals(actual.getPlayer(), player);
        assertEquals(actual.getGamesPlayed(), 10);
    }

    @Test
    void testGetPlayerStats(){
        StatsDTO statsDTO = gameStatisticsService.getPlayerStats(player);

        assertEquals("Danica Gazdic", statsDTO.getPlayerName());
        assertEquals(10, statsDTO.getGamesPlayed());
        assertEquals(5.0, statsDTO.getTraditional().getFreeThrows().getMade());
        assertEquals(1.0, statsDTO.getTraditional().getFreeThrows().getAttempts());

        assertEquals(3.0, statsDTO.getTraditional().getTwoPoints().getAttempts());
        assertEquals(5.0, statsDTO.getTraditional().getTwoPoints().getMade());

        assertEquals(10.0, statsDTO.getTraditional().getThreePoints().getMade());
        assertEquals(4.0, statsDTO.getTraditional().getThreePoints().getAttempts());

        assertEquals(8.0, statsDTO.getTraditional().getRebounds());
        assertEquals(3.0, statsDTO.getTraditional().getBlocks());
        assertEquals(7.0, statsDTO.getTraditional().getAssists());
        assertEquals(5.0, statsDTO.getTraditional().getSteals());
        assertEquals(4.0, statsDTO.getTraditional().getTurnovers());
        assertEquals(30.0, statsDTO.getTraditional().getPoints());

        assertEquals(25.5, statsDTO.getAdvanced().getValorization());
        assertEquals(60.0, statsDTO.getAdvanced().getEffectiveFieldGoalPercentage());
        assertEquals(65.0, statsDTO.getAdvanced().getTrueShootingPercentage());
        assertEquals(4.5, statsDTO.getAdvanced().getHollingerAssistRatio());
    }

    @BeforeEach
    void setUp(){
        when(gameStatisticsRepo.getByPlayer(player)).thenReturn(gameStatistic);

        when(gameStatistic.getGamesPlayed()).thenReturn(10);
        when(gameStatistic.getFreeThrowMade()).thenReturn(5.0);
        when(gameStatistic.getFreeThrowAttempted()).thenReturn(1.0);
        when(gameStatistic.getTwoPointsMade()).thenReturn(5.0);
        when(gameStatistic.getTwoPointsAttempted()).thenReturn(3.0);
        when(gameStatistic.getThreePointsMade()).thenReturn(10.0);
        when(gameStatistic.getThreePointsAttempted()).thenReturn(4.0);
        when(gameStatistic.getRebounds()).thenReturn(8.0);
        when(gameStatistic.getBlocks()).thenReturn(3.0);
        when(gameStatistic.getAssists()).thenReturn(7.0);
        when(gameStatistic.getSteals()).thenReturn(5.0);
        when(gameStatistic.getTurnovers()).thenReturn(4.0);
        when(gameStatistic.getPoints()).thenReturn(30.0);
        when(gameStatistic.getValorization()).thenReturn(25.5);
        when(gameStatistic.getEffectiveFieldGoalPercentage()).thenReturn(60.0);
        when(gameStatistic.getTrueShootingPercentage()).thenReturn(65.0);
        when(gameStatistic.getHollingerAssistRatio()).thenReturn(4.5);
        when(gameStatistic.getPlayer()).thenReturn(player);

        when(player.getFullName()).thenReturn("Danica Gazdic");
    }

}
