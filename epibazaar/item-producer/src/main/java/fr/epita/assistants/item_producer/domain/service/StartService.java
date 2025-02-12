package fr.epita.assistants.item_producer.domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epita.assistants.common.api.response.StartResponse;
import fr.epita.assistants.common.command.ResetInventoryCommand;
import fr.epita.assistants.item_producer.data.repository.PlayerRepository;
import fr.epita.assistants.item_producer.domain.entity.MapEntity;
import fr.epita.assistants.item_producer.errors.Result;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ApplicationScoped
public class StartService {
    @Inject
    PlayerRepository playerRepository;
    @Inject
    PlayerService playerService;
    @Getter
    private boolean activate = false;

    @Inject
    GameService gameService;

    @Inject
    @Channel("reset-inventory-command")
    Emitter<ResetInventoryCommand> resetInventoryCommandEmitter;

    public StartService()
    {
        this.activate = false;
    }

    public Result<MapEntity, String> start_application(String info)
    {
        gameService.clear();
        playerService.clear();


        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(info);
            JsonNode node = root.get("mapPath");
            String path;
            List<String> lines;

            if (node == null) {
                return Result.error("Invalid path provided.");
            }

            path = node.asText();

            lines = Files.readAllLines(Path.of(path));

            resetInventoryCommandEmitter.send(new ResetInventoryCommand());

            for (String l : lines) {
                System.out.println(l);
            }
            if (!gameService.parse_map(lines)) {
                return Result.error("Invalid path provided.");
            }
            playerService.create_player();
            activate = true;
            return Result.success(new MapEntity(gameService.getMap()));
        } catch (Exception e) {
            return Result.error("Invalid path provided.");
        }
    }
}
