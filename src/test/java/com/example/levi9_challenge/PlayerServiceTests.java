package com.example.levi9_challenge;

import com.example.levi9_challenge.exception.EntityNotFoundException;
import com.example.levi9_challenge.model.Player;
import com.example.levi9_challenge.repo.PlayerRepo;
import com.example.levi9_challenge.service.impl.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlayerServiceTests {
    @Autowired
    private PlayerService playerService;

    @MockBean
    private PlayerRepo playerRepo;

    @Test
    public void testSavePlayer() {
        Player player = new Player();
        when(playerRepo.save(player)).thenReturn(player);

        Player savedPlayer = playerService.savePlayer(player);

        assertEquals(player, savedPlayer);
        verify(playerRepo).save(player);
    }

    @Test
    public void testAddPlayer_Exists() {
        Player player = new Player();
        when(playerRepo.findByFullName(player.getFullName())).thenReturn(Optional.of(player));

        Player addedPlayer = playerService.addPlayer(player);

        assertEquals(player, addedPlayer);
        verify(playerRepo).findByFullName(player.getFullName());
    }

    @Test
    public void testAddPlayer_NotExist() {
        Player player = new Player();
        when(playerRepo.findByFullName(player.getFullName())).thenReturn(Optional.empty());
        when(playerRepo.save(player)).thenReturn(player);

        Player addedPlayer = playerService.addPlayer(player);

        assertEquals(player, addedPlayer);
        verify(playerRepo).findByFullName(player.getFullName());
        verify(playerRepo).save(player);
    }

    @Test
    public void testFindByFullName_Exists() {
        Player player = new Player();
        String fullName = "Danica Gazdic";
        when(playerRepo.findByFullName(fullName)).thenReturn(Optional.of(player));
        Player foundPlayer = playerService.findByFullName(fullName);

        assertEquals(player, foundPlayer);
        verify(playerRepo).findByFullName(fullName);
    }

    @Test
    public void testFindByFullName_NotExist() {
        String fullName = "Danica Gazdic";
        when(playerRepo.findByFullName(fullName)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> playerService.findByFullName(fullName));
        verify(playerRepo).findByFullName(fullName);
    }

}

