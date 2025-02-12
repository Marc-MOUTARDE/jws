package fr.epita.assistants.item_producer.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "item")
public class ItemModel {
    @Id @GeneratedValue
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private Float quantity;

    @Getter @Setter
    private String type;

    public ItemModel(float quantity, String type)
    {
        this.quantity = quantity;
        this.type = type;
    }

    public ItemModel() {

    }
}
