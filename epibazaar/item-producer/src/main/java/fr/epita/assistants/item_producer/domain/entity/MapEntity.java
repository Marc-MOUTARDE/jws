package fr.epita.assistants.item_producer.domain.entity;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class MapEntity {
    @Getter @Setter
    public List<List<ItemAggregate.ResourceType>> map;
}
