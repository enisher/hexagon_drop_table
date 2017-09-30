package com.anahoret.anadea_bot;


import com.anahoret.anadea_bot.dto.ClientMove;
import com.anahoret.anadea_bot.dto.Game;

public interface IStrategy {
    ClientMove nextMove(Game game, int color);
}
