package com.example.basketballplayerregistration.service.Impl;

import java.util.List;

import com.example.basketballplayerregistration.data.model.dto.PlayerDto;
import com.example.basketballplayerregistration.data.model.entity.Player;
import com.example.basketballplayerregistration.data.model.mapper.PlayerMapper;
import com.example.basketballplayerregistration.data.repository.PlayerRepository;
import com.example.basketballplayerregistration.service.IPlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PlayerService implements IPlayerService {
    public static final int TEAM_MAXIMUM_CAPACITY = 5;

    private final PlayerRepository playerRepository;
    
    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;        
    }

    @Override
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player addPlayer(PlayerDto player) {
        validatePlayer(player);
        
        if(playerRepository.count() > 4) // capacity check
            throw new IllegalStateException("Team is full");

        // log.info("player with id: {} created by: {}");
        return playerRepository.save((PlayerMapper.toPlayer(player)));
    }

    @Override
    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
        // log.info("player with id: {} deleted by {}", id, );
    }
    
    /*********** PRIVATE METHODS ***********/

    private void validatePlayer(PlayerDto player) {
        if (player.getName() == null || player.getName().isEmpty() || player.getName().length() < 3 || player.getName().length() > 20)
            throw new IllegalArgumentException("Player name must be between 2 to 20 characters");
    
        if (player.getSurname() == null || player.getSurname().isEmpty() || player.getSurname().length() < 3 || player.getSurname().length() > 20)
            throw new IllegalArgumentException("Player surname must be between 2 to 20 characters");
        
        if (player.getPosition() == null)
            throw new IllegalArgumentException("Player position must be selected");
    }

}