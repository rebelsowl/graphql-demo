package com.example.basketballplayerregistration.data.model.mapper;

import com.example.basketballplayerregistration.data.model.dto.PlayerDto;
import com.example.basketballplayerregistration.data.model.entity.Player;

public class PlayerMapper {

    // TODO: public static PlayerDto toPlayerDto(Player player){ }
    
    public static Player toPlayer(PlayerDto playerDto){
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setSurname(playerDto.getSurname());
        player.setPosition(playerDto.getPosition());
        return player;
    }

}
