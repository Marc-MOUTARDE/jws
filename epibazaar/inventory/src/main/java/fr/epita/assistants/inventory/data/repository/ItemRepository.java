package fr.epita.assistants.inventory.data.repository;

import fr.epita.assistants.inventory.data.model.ItemModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<ItemModel> {
}
