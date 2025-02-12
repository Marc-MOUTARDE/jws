package fr.epita.assistants.item_producer.domain.service;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.item_producer.data.model.GameModel;
import fr.epita.assistants.item_producer.data.repository.GameRepository;
import io.quarkus.arc.runtime.ConfigStaticInitCheckInterceptor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class GameService {
    @Inject
    GameRepository gameRepository;

    @Getter
    private List<List<ItemAggregate.ResourceType>> map;

    public String compress_map()
    {
        int count = 0;
        String data = "";
        ItemAggregate.ResourceType last = null;

        for (List<ItemAggregate.ResourceType> r : map)
        {
            last = r.getFirst();
            for (ItemAggregate.ResourceType t : r) {
                if (last == t) {
                    count++;
                } else {
                    while (count >= 10) {
                        data += "9" + last.getItemInfo().getValue();
                        count -= 9;
                    }

                    if (count > 0) {
                        data += String.valueOf(count) + last.getItemInfo().getValue();
                        count = 1;
                    }

                    last = t;
                }
            }

            while (count >= 10) {
                data += "9" + last.getItemInfo().getValue();
                count -= 9;
            }

            if (count > 0) {
                data += String.valueOf(count) + last.getItemInfo().getValue();
                data += ";";
                count = 0;
            }
        }

        return data.substring(0, data.length() - 1);
    }

    public void set_map(List<String> m)
    {
        String input = "";

        for (String l : m) {
            input += l + ";";
        }
        System.out.println("Pass: " + input);
        save_map(input.substring(0, input.length() - 1));
    }

    @Transactional
    public void save_map(String map)
    {
        gameRepository.deleteAll();
        gameRepository.persist(new GameModel(map));
    }

    public void update_map()
    {
        String new_map = compress_map();
        save_map(new_map);
    }

    public boolean parse_map(List<String> input)
    {
        map = new ArrayList<>();

        for (String l : input) {
            int repeat = 0;
            List<ItemAggregate.ResourceType> row = new ArrayList<>();

            for (int i = 0; i < l.length(); ++i) {
                if (l.charAt(i) >= '0' && l.charAt(i) <= '9') {
                    repeat = l.charAt(i) - '0';
                } else {
                    for (int j = 0; j < repeat; ++j) {
                        row.add(ItemAggregate.ResourceType.getResource(l.charAt(i)));
                    }
                }
            }

            map.add(row);
        }

        System.out.println("Comp: " + compress_map());
        set_map(input);
        return true;
    }

    @Transactional
    public void clear()
    {
        gameRepository.deleteAll();
    }
}
