package com.example.basketballplayerregistration.resolver;

import com.example.basketballplayerregistration.data.model.entity.Player;
import com.example.basketballplayerregistration.service.IPlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class PlayerMutationResolver implements GraphQLMutationResolver{
 
    @Autowired
    IPlayerService playerService;

    public Player addPlayer(Player player){
        return playerService.addPlayer(player);
    }

    public String deletePlayer(int id){
        playerService.deletePlayer(id);
        return  "Player deleted";
    }
    
}
