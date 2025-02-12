package fr.epita.assistants.item_producer.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "game")
public class GameModel {
    @Getter @Setter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter @Setter
    private String map;

    public GameModel(String input) {
        this.map = input;
    }

    public GameModel() {

    }
}
