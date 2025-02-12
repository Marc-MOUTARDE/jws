package fr.epita.assistants.common.api.response;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class StartResponse {
    @Getter
    private List<List<ItemAggregate.ResourceType>> map;
}
