package fr.epita.assistants.item_producer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class PlayerEntity {
    @Getter
    @Setter
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

}
