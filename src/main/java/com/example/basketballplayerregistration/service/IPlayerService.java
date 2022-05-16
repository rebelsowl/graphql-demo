package com.example.basketballplayerregistration.service;

import java.util.List;

import com.example.basketballplayerregistration.data.model.entity.Player;

public interface IPlayerService {
    
    List<Player> getPlayers();

    Player addPlayer(Player player);
    void deletePlayer(int id);
    
}
