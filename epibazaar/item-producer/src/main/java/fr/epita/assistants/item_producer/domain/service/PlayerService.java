package fr.epita.assistants.item_producer.domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.common.command.CollectItemCommand;
import fr.epita.assistants.common.command.ResetInventoryCommand;
import fr.epita.assistants.common.utils.Direction;
import fr.epita.assistants.common.utils.Point;
import fr.epita.assistants.item_producer.data.model.PlayerModel;
import fr.epita.assistants.item_producer.data.repository.PlayerRepository;
import fr.epita.assistants.item_producer.domain.entity.MapEntity;
import fr.epita.assistants.item_producer.domain.entity.PlayerEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import fr.epita.assistants.item_producer.errors.Result;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.time.LocalDateTime;

@ApplicationScoped
public class PlayerService {
    @Inject
    GameService gameService;

    @Inject
    PlayerRepository playerRepository;


    @Inject
    @Channel("collect-item-command")
    Emitter<CollectItemCommand> collectItemCommandEmitter;

    @Transactional
    public PlayerEntity get_player()
    {
        PlayerModel p = playerRepository.listAll().getFirst();
        return new PlayerEntity(p.getCollectRateMultiplier(), p.getMoveSpeedMultiplier(), p.getPosX(), p.getPosY(), p.getStaminaMultiplier(), p.getLastCollect(), p.getLastMove());
    }

    @Transactional
    public void set_player(PlayerEntity player)
    {
        PlayerModel current = playerRepository.listAll().getFirst();
        current.setCollectRateMultiplier(player.getCollectRateMultiplier());
        current.setMoveSpeedMultiplier(player.getMoveSpeedMultiplier());
        current.setPosX(player.getPosX());
        current.setPosY(player.getPosY());
        current.setStaminaMultiplier(player.getStaminaMultiplier());
        current.setLastCollect(player.getLastCollect());
        current.setLastMove(player.getLastMove());
        playerRepository.persist(current);
    }

    private boolean is_movement_valid(PlayerEntity player, Point direction)
    {
        int width, height, x, y;

        height = gameService.getMap().size();
        width = gameService.getMap().get(0).size();

        x = player.getPosX() + direction.getPosX();
        y = player.getPosY() + direction.getPosY();

        if ((x < 0 || y < 0 || x >= width || y >= height)) {
            return false;
        }

        return  gameService.getMap().get(y).get(x).getItemInfo().isWalkable();
    }

    @Transactional
    public void create_player()
    {
        playerRepository.persist(new PlayerModel());
    }

    public Result<PlayerEntity, String> move(String info)
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(info);
            JsonNode node = root.get("direction");
            Direction direction = Direction.valueOf(node.asText());

            PlayerEntity player = get_player();

            if (player.getLastMove() != null) {
                // handle movement
            }

            if (!is_movement_valid(player, direction.getPoint())) {
                return Result.error("Invalid direction or the game is not running.");
            }

            player.setPosX(player.getPosX() + direction.getPoint().getPosX());
            player.setPosY(player.getPosY() + direction.getPoint().getPosY());

            player.setLastMove(LocalDateTime.now());
            set_player(player);
            return Result.success(player);
        } catch (Exception e) {
            return Result.error("Invalid direction or the game is not running.");
        }
    }

    public Result<MapEntity, String> collect()
    {
        PlayerEntity p = get_player();
        if (!gameService.getMap().get(p.getPosY()).get(p.getPosX()).getItemInfo().isCollectable())
            return Result.error("Invalid tile or the game is not running.");

        collectItemCommandEmitter.send(new CollectItemCommand(gameService.getMap().get(p.getPosX()).get(p.getPosY()), p.getCollectRateMultiplier()));
        gameService.getMap().get(p.getPosY()).set(p.getPosX(), ItemAggregate.ResourceType.GROUND);
        gameService.update_map();
        p.setLastCollect(LocalDateTime.now());
        set_player(p);
        return Result.success(new MapEntity(gameService.getMap()));
    }

    @Transactional
    public void clear()
    {
        playerRepository.deleteAll();
    }
}
