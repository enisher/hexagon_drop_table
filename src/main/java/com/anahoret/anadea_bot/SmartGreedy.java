package com.anahoret.anadea_bot;

import com.anahoret.anadea_bot.dto.Board;
import com.anahoret.anadea_bot.dto.ClientMove;
import com.anahoret.anadea_bot.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmartGreedy extends GreedyStrategy {
    @Autowired
    GreedyStrategy greedyStrategy;

    @Override
    protected EvaluatedMove evaluate(ClientMove move, Game game, int color) {
        Board board = game.getBoard();
        Game opponentsGame = new Game();
        Board opponentsBoard = board.applyMove(move);
        opponentsGame.setBoard(opponentsBoard);
        opponentsGame.setJumps(game.getJumps());

        ClientMove opponentsMove = greedyStrategy.nextMove(game, color % 2 + 1);
        Board myNextBoard = board.applyMove(opponentsMove);

        return new EvaluatedMove(move, myNextBoard.findAll(color).size());
    }
}
