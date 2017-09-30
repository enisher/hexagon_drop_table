package com.anahoret.anadea_bot;

import com.anahoret.anadea_bot.dto.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameStorage
{
    private final Map<String, Game> games = new HashMap<>();

    public void create(String gameId, Game game) {
        games.put(gameId, game);
    }

    public Game load(String gameId)
    {
        return games.get(gameId);
    }

    public void delete(String gameId) {
        games.remove(gameId);
    }

    public void update(String gameId, Game game)
    {
        games.put(gameId, game);
    }
}
