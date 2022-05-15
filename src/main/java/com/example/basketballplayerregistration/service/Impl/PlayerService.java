package com.example.basketballplayerregistration.service.Impl;

import java.util.List;

import com.example.basketballplayerregistration.data.model.entity.Player;
import com.example.basketballplayerregistration.data.repository.PlayerRepository;
import com.example.basketballplayerregistration.service.IPlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }
    
}
