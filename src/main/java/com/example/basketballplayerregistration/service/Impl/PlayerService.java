package com.example.basketballplayerregistration.service.Impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

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

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player addPlayer(PlayerDto player) {
        // capacity check
        if(playerRepository.count() > 4)
            throw new IllegalStateException("Team is full");

        Set<ConstraintViolation<PlayerDto>> violations = validator.validate(player);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<PlayerDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            // log.error("addPlayer: {} player: {}", sb.toString(), player.toString());
            throw new ConstraintViolationException(sb.toString(), violations);
        }
        // log.info("player with id: {} created by: {}");
        return playerRepository.save((PlayerMapper.toPlayer(player)));
    }

    @Override
    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
        // log.info("player with id: {} deleted by {}", id, );
    }
    
}
