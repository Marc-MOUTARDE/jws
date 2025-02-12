package fr.epita.assistants.item_producer.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "player")
public class PlayerModel {
    @Getter @Setter
    @Id @GeneratedValue
    private Integer id;

    @Getter @Setter
    private Float collectRateMultiplier;

    @Getter @Setter
    private Float moveSpeedMultiplier;

    @Getter @Setter
    private Integer posX;

    @Getter @Setter
    private Integer posY;

    @Getter @Setter
    private Float staminaMultiplier;

    @Getter @Setter
    public LocalDateTime lastCollect;

    @Getter @Setter
    public LocalDateTime lastMove;

    public PlayerModel()
    {
        this.collectRateMultiplier = 1.0f;
        this.moveSpeedMultiplier = 1.0f;
        this.posX = 0;
        this.posY = 0;
        this.staminaMultiplier = 1.0f;
        this.lastCollect = null;
        this.lastMove = null;
    }
}
