package com.anahoret.anadea_bot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class Game
{

    private String id;
    private boolean first_turn;
    private boolean training;
    private HashMap<String, Integer> jumps;
    private Board board;

    @Override
    public String toString()
    {
        return "Game{" +
                "id='" + id + '\'' +
                ", first_turn=" + first_turn +
                ", training=" + training +
                ", jumps=" + jumps +
                ", board=" + board +
                '}';
    }

    public void applyServerMove(ServerMove move)
    {
        jumps = move.getJumps();

        board.applyChanges(move.getChanges());
    }

    public boolean jumpsAvailable(int color)
    {
        return jumps.getOrDefault(String.valueOf(color), 0) > 0;
    }
}
