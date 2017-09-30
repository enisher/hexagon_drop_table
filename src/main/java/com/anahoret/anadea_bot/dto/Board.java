package com.anahoret.anadea_bot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class Board implements Cloneable
{

    private int size;
    private int[][] cells;

    @Override
    public String toString()
    {
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

    public List<Point> findAll(int colour)
    {
        List<Point> result = new ArrayList<>();

        for (int i = 0; i < cells.length; i++)
        {
            final int[] row = cells[i];
            for (int j = 0; j < row.length; j++)
            {
                final int cell = row[j];

                if (colour == cell)
                {
                    result.add(new Point(i, j));
                }
            }
        }

        return result;
    }

    public boolean validate(Point point)
    {
        int realSize = cells[0].length;
        return point.getI() >= 0 && point.getI() < realSize
                && point.getJ() >= 0 && point.getJ() < realSize;
    }

    public int getColor(Point point)
    {
        return cells[point.getI()][point.getJ()];
    }

    public boolean checkIfEmpty(Point point)
    {
        return getColor(point) == 0;
    }

    public Board applyMove(ClientMove move){
        Point from = new Point(move.getMove_from());
        Point to = new Point(move.getMove_to());
        Board nextState = clone();

        int myColor = cells[from.getI()][from.getJ()];
        nextState.cells[to.getI()][to.getJ()] = myColor;
        if(!from.neighbors().contains(to)){ //jump, have to clean from
            nextState.cells[from.getI()][from.getJ()] = 0;
        }
        for(Point neighbour: to.neighbors()){
            if (!validate(neighbour)) continue;
            int neighbourColor = nextState.cells[neighbour.getI()][neighbour.getJ()];
            if (neighbourColor > 0 & neighbourColor != myColor){
                nextState.cells[neighbour.getI()][neighbour.getJ()] = myColor;
            }
        }
        return nextState;
    }

    @Override
    protected Board clone(){
        Board board = new Board();
        board.setSize(size);
        board.setCells(deepCopy(cells));
        return board;
    }

    private static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
