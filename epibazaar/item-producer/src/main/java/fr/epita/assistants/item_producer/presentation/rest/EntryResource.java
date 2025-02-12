package fr.epita.assistants.item_producer.presentation.rest;

import fr.epita.assistants.common.api.response.ItemResponse;
import fr.epita.assistants.common.api.response.MoveResponse;
import fr.epita.assistants.common.api.response.PlayerResponse;
import fr.epita.assistants.common.api.response.StartResponse;
import fr.epita.assistants.common.utils.ErrorInfo;
import fr.epita.assistants.item_producer.domain.entity.ItemEntity;
import fr.epita.assistants.item_producer.domain.entity.MapEntity;
import fr.epita.assistants.item_producer.domain.entity.PlayerEntity;
import fr.epita.assistants.item_producer.domain.service.InventoryService;
import fr.epita.assistants.item_producer.domain.service.PlayerService;
import fr.epita.assistants.item_producer.domain.service.StartService;
import fr.epita.assistants.item_producer.errors.Result;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.ArrayList;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntryResource {
    @Inject
    StartService start;

    @Inject
    PlayerService player;

    @Inject
    InventoryService inventory;

    @Path("/start")
    @POST
    public Response start(String info)
    {
        Result<MapEntity, String> result = start.start_application(info);
        if (result.is_error()) {
            return Response.ok(new ErrorInfo(result.get_error())).status(400).build();
        }
        return Response.ok(new StartResponse(result.get_value().map)).build();
    }

    @Path("/move")
    @POST
    public Response move(String info)
    {
        if (!start.isActivate()) {
            return Response.ok(new ErrorInfo("Invalid direction or the game is not running.")).status(400).build();
        }

        Result<PlayerEntity, String> r = player.move(info);
        if (r.is_error()) {
            return Response.ok(new ErrorInfo(r.get_error())).status(400).build();
        }
        return Response.ok(new MoveResponse(r.get_value().getPosX(), r.get_value().getPosY())).build();
    }

    @Path("/collect")
    @POST
    public Response collect()
    {
        if (!start.isActivate()) {
            return Response.ok(new ErrorInfo("Invalid direction or the game is not running.")).status(400).build();
        }

        Result<MapEntity, String> r = player.collect();
        if (r.is_error()) {
            return Response.ok(new ErrorInfo(r.get_error())).status(400).build();
        }
        return Response.ok(new StartResponse(r.get_value().getMap())).build();
    }

    @Path("/player")
    @GET
    public Response get_player()
    {
        if (!start.isActivate())
            return Response.ok(new ErrorInfo("The game is not running.")).status(400).build();

        PlayerEntity e = player.get_player();
        PlayerResponse r = new PlayerResponse(e.getCollectRateMultiplier(), e.getMoveSpeedMultiplier(), e.getPosX(), e.getPosY(), e.getStaminaMultiplier(), e.getLastCollect(), e.getLastMove());
        return Response.ok(r).build();
    }

    @Path("/")
    @GET
    public Response get_resources()
    {
        if (!start.isActivate())
            return Response.ok(new ErrorInfo("The game is not running.")).status(400).build();

        List<ItemResponse.ItemInfo> items = new ArrayList<>();
        for (ItemEntity e : inventory.get_inventory()) {
            items.add(new ItemResponse.ItemInfo(e.id, e.quantity, e.type));
        }
        return Response.ok(new ItemResponse(items)).build();
    }

    @Path("/upgrade/collect")
    @PATCH
    public Response upgrade_collect()
    {
        return Response.ok(new ErrorInfo("Insufficient funds or the game is not running.")).status(400).build();
    }

    @Path("/upgrade/move")
    @PATCH
    public Response upgrade_move()
    {
        return Response.ok(new ErrorInfo("Insufficient funds or the game is not running.")).status(400).build();
    }

    @Path("/upgrade/stamina")
    @PATCH
    public Response upgrade_stamina()
    {
        return Response.ok(new ErrorInfo("Insufficient funds or the game is not running.")).status(400).build();
    }
}
