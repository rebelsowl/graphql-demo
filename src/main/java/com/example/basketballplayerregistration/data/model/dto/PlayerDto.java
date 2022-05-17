package com.example.basketballplayerregistration.data.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.basketballplayerregistration.data.model.enums.PlayerPositionType;

import lombok.Data;

@Data
public class PlayerDto {
    
    @Size(min = 2, max = 20, message = "Name must be between 2 to 20 characters")
    private String name;
    
    @Size(min = 2, max = 20, message = "Surname must be between 2 to 20 characters" )
    private String surname;
    
    @NotNull
    private PlayerPositionType position;
    
}