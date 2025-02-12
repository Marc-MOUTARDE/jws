package fr.epita.assistants.item_producer.domain.entity;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ItemEntity {
    public Integer id;
    public float quantity;
    public ItemAggregate.ResourceType type;
}
