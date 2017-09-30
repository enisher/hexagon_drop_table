package com.anahoret.anadea_bot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
public class Board {

    private int size;
    private int[][] cells;

    @Override
    public String toString() {
        return "Board{" +
                "size=" + size +
                ", cells=" + Arrays.deepToString(cells) +
                '}';
    }

    public void applyChanges(int[][] changes)
    {
        for (int[] change : changes)
        {
            int i = change[0];
            int j = change[1];
            int oldColor = change[2];
            int newColor = change[3];


            cells[i][j] = newColor;
        }
    }
}
