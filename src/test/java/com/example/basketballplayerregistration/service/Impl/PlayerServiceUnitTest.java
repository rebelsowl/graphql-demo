package com.example.basketballplayerregistration.service.Impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.basketballplayerregistration.data.model.dto.PlayerDto;
import com.example.basketballplayerregistration.data.model.entity.Player;
import com.example.basketballplayerregistration.data.model.enums.PlayerPositionType;
import com.example.basketballplayerregistration.data.repository.PlayerRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlayerServiceUnitTest {
    private static String TEST_NAME = "testName";
    private static String TEST_SURNAME = "testSurname";

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;


    @Test
    void testGetPlayers() {
        Player aMockPlayer = new Player();
        aMockPlayer.setName(TEST_NAME);
        List<Player> mockList = new ArrayList<Player>();
        mockList.add(aMockPlayer);

        when(playerRepository.findAll()).thenReturn(mockList);

        List<Player> players = playerRepository.findAll();
        assertThat(players.size()).isEqualTo(1);

        Player player = players.get(0);
        assertThat(player.getName()).isEqualTo(TEST_NAME);        
    }


    @Test
    void testAddPlayer() {
        Player aMockPlayer = new Player();
        aMockPlayer.setId(1);
        aMockPlayer.setName(TEST_NAME);

        when(playerRepository.save(any(Player.class))).thenReturn(aMockPlayer);

        PlayerDto inputPlayer = new PlayerDto();
        inputPlayer.setName(TEST_NAME);
        inputPlayer.setSurname(TEST_SURNAME);
        inputPlayer.setPosition(PlayerPositionType.PF);

        Player player = playerService.addPlayer(inputPlayer);

        assertThat(player.getId()).isEqualTo(1);
        assertThat(player.getName()).isEqualTo(TEST_NAME);
    }

    @Test
    void testAddPlayerWithNoName() {
        PlayerDto inputPlayer = new PlayerDto();
        inputPlayer.setSurname(TEST_SURNAME);
        inputPlayer.setPosition(PlayerPositionType.PF);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.addPlayer(inputPlayer);
        });
        assertThat(exception.getMessage()).isEqualTo("Player name must be between 2 to 20 characters");        
    }

    @Test
    void testAddPlayerWithLongName() {
        PlayerDto inputPlayer = new PlayerDto();
        inputPlayer.setName("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest");
        inputPlayer.setSurname(TEST_SURNAME);
        inputPlayer.setPosition(PlayerPositionType.PF);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.addPlayer(inputPlayer);
        });
        assertThat(exception.getMessage()).isEqualTo("Player name must be between 2 to 20 characters");        
    }
    
    @Test
    void testAddPlayerWithNoSurname() {
        PlayerDto inputPlayer = new PlayerDto();
        inputPlayer.setName(TEST_NAME);
        inputPlayer.setPosition(PlayerPositionType.PF);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.addPlayer(inputPlayer);
        });
        assertThat(exception.getMessage()).isEqualTo("Player surname must be between 2 to 20 characters");  
    }

    @Test
    void testAddPlayerWithLongSurname() {
        PlayerDto inputPlayer = new PlayerDto();
        inputPlayer.setName(TEST_NAME);
        inputPlayer.setSurname("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest");
        inputPlayer.setPosition(PlayerPositionType.PF);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.addPlayer(inputPlayer);
        });
        assertThat(exception.getMessage()).isEqualTo("Player surname must be between 2 to 20 characters");  
    }

    @Test
    void testAddPlayerWithNoPosition(){
        PlayerDto inputPlayer = new PlayerDto();
        inputPlayer.setName(TEST_NAME);
        inputPlayer.setSurname(TEST_SURNAME);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.addPlayer(inputPlayer);
        });
        assertThat(exception.getMessage()).isEqualTo("Player position must be selected");  
    }
    
    @Test
    void testAddPlayerExceedingTeamLimit(){
        PlayerDto inputPlayer = new PlayerDto();
        inputPlayer.setName(TEST_NAME);
        inputPlayer.setSurname(TEST_SURNAME);
        inputPlayer.setPosition(PlayerPositionType.PF);

        when(playerRepository.count()).thenReturn(5L); // already 5 players in team

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            playerService.addPlayer(inputPlayer);
        });
        assertThat(exception.getMessage()).isEqualTo("Team is full");  
    }

    @Test
    void deletePlayer(){
        Player inputPlayer = new Player();
        inputPlayer.setId(1);
        inputPlayer.setName(TEST_NAME);
        inputPlayer.setSurname(TEST_SURNAME);
        inputPlayer.setPosition(PlayerPositionType.PF);

        when(playerRepository.findById(inputPlayer.getId())).thenReturn(Optional.of(inputPlayer));

        playerService.deletePlayer(inputPlayer.getId());
        verify(playerRepository).deleteById(inputPlayer.getId());
    }
}
