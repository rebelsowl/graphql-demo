package com.example.basketballplayerregistration.resolver;

import java.util.List;

import com.example.basketballplayerregistration.data.model.entity.Player;
import com.example.basketballplayerregistration.service.IPlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class PlayerQueryResolver implements GraphQLQueryResolver {
    
    @Autowired
    IPlayerService playerService;

    public List<Player> getPlayers(){
        return playerService.getPlayers();
    }
}
