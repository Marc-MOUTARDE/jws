package fr.epita.assistants.common.api.response;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ItemResponse {
    @AllArgsConstructor
    public static class ItemInfo
    {
        public Integer id;
        public Float quantity;
        public ItemAggregate.ResourceType type;
    }

    public List<ItemInfo> itemsResponse;
}
