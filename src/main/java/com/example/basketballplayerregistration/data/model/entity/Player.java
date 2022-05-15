package com.example.basketballplayerregistration.data.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// import com.example.basketballplayerregistration.data.model.enums.PlayerPositionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PLAYERS")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "surname", length = 20)
    private String surname;

    @Column(name = "position", length = 20)
    private String position;
    //private PlayerPositionType position;
    
}