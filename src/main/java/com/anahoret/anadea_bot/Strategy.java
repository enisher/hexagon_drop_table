package com.anahoret.anadea_bot;

import com.anahoret.anadea_bot.dto.Board;
import com.anahoret.anadea_bot.dto.ClientMove;
import com.anahoret.anadea_bot.dto.Game;
import com.anahoret.anadea_bot.dto.Point;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Strategy
{
    public ClientMove firstValidMove(Game game, int color)
    {
        final List<ClientMove> clientMoves = allValidMoves(game, color);

        Collections.shuffle(clientMoves);

        return clientMoves.stream()
                .findFirst()
                .orElse(new ClientMove(new int[]{0, 2}, new int[]{0, 3}));
    }

    public List<ClientMove> allValidMoves(Game game, int color)
    {
        final Board board = game.getBoard();
        final List<Point> myCells = game.getBoard().findAll(color);

        return myCells.stream()
                .flatMap(
                        moveFrom -> moveFrom.allMoves(game.jumpsAvailable(color))
                                .stream()
                                .filter(board::validate)
                                .filter(board::checkIfEmpty)
                                .map(moveTo -> new ClientMove(moveFrom.toArray(), moveTo.toArray()))
                ).collect(Collectors.toList());

    }
}
