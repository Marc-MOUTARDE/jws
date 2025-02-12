package fr.epita.assistants.item_producer.domain.service;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.item_producer.data.model.ItemModel;
import fr.epita.assistants.item_producer.data.repository.ItemRepository;
import fr.epita.assistants.item_producer.domain.entity.ItemEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InventoryService {
    @Inject
    ItemRepository itemRepository;

    @Transactional
    public List<ItemEntity> get_inventory()
    {
        List<ItemEntity> items = new ArrayList<>();
        for (ItemModel m : itemRepository.listAll()) {
            items.add(new ItemEntity(m.getId(), m.getQuantity(), ItemAggregate.ResourceType.valueOf(m.getType())));
        }
        return items;
    }
}
