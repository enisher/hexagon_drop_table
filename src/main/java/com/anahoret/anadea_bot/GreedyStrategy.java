package com.anahoret.anadea_bot;

import com.anahoret.anadea_bot.dto.Board;
import com.anahoret.anadea_bot.dto.ClientMove;
import com.anahoret.anadea_bot.dto.Game;
import com.anahoret.anadea_bot.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GreedyStrategy implements IStrategy{

    @Autowired
    protected Strategy randomStrategy;

    @Override
    public ClientMove nextMove(Game game, int color) {
        List<ClientMove> possibleMoves = randomStrategy.allValidMoves(game, color);
        List<EvaluatedMove> evaluatedMoves = evaluateMoves(possibleMoves, game, color);
        return evaluatedMoves.stream()
                .max(Comparator.comparing(EvaluatedMove::getScore)).get().move;
    }

    private List<EvaluatedMove> evaluateMoves(List<ClientMove> moves, Game game, int color) {
        return moves.stream()
                .map(move -> evaluate(move, game, color))
                .collect(Collectors.toList());
    }

    protected EvaluatedMove evaluate(ClientMove move, Game game, int color){
        Board nextBoard = game.getBoard().applyMove(move);


        EvaluatedMove evaluatedMove = new EvaluatedMove();
        evaluatedMove.move = move;
        evaluatedMove.score = nextBoard.findAll(color).size();
        return evaluatedMove;
    }

    class EvaluatedMove{
        ClientMove move;
        double score;
        public EvaluatedMove(){}

        public EvaluatedMove(ClientMove move, double score) {
            this.move = move;
            this.score = score;
        }

        public double getScore() {
            return score;
        }
    }
}
