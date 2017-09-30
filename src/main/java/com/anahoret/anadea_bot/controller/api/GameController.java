package com.anahoret.anadea_bot.controller.api;

import com.anahoret.anadea_bot.GameStorage;
import com.anahoret.anadea_bot.GreedyStrategy;
import com.anahoret.anadea_bot.Strategy;
import com.anahoret.anadea_bot.dto.ClientMove;
import com.anahoret.anadea_bot.dto.Game;
import com.anahoret.anadea_bot.dto.ResponseStatusDto;
import com.anahoret.anadea_bot.dto.ServerMove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameStorage gameStorage;

    @Autowired
    private GreedyStrategy strategy;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseStatusDto startGame(@RequestBody Game game) {
        System.out.println("startGame endpoint hit");
        System.out.println(game);

        gameStorage.create(game.getId(), game);

        return new ResponseStatusDto("ok");
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    @ResponseBody
    public ClientMove makeMove(@PathVariable("gameId") String gameId, @RequestParam("color") int color) {
        System.out.println("makeMove endpoint hit");
        System.out.println("gameId=" + gameId);
        System.out.println("color=" + color);

        final Game game = gameStorage.load(gameId);

        return strategy.nextMove(game, color);
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseStatusDto handleMove(@PathVariable("gameId") String gameId, @RequestBody ServerMove serverMove) {
        System.out.println("handleMove endpoint hit");
        System.out.println("gameId=" + gameId);
        System.out.println(serverMove);

        final Game game = gameStorage.load(gameId);

        game.applyServerMove(serverMove);

        gameStorage.update(gameId, game);

        return new ResponseStatusDto("ok");
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseStatusDto finishGame(@PathVariable("gameId") String gameId) {
        System.out.println("finishGame endpoint hit");
        System.out.println("gameId=" + gameId);

        gameStorage.delete(gameId);

        return new ResponseStatusDto("ok");
    }

}
