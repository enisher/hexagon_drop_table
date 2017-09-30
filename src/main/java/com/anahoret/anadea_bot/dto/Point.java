package com.anahoret.anadea_bot.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Point
{
    public Point(int[] point){
        i = point[0];
        j = point[1];
    }
    private final int i, j;

    public int[] toArray()
    {
        return new int[]{i, j};
    }

    public Set<Point> neighbors()
    {
        final HashSet<Point> result = new HashSet<>();

        result.add(right());
        result.add(upRight());
        result.add(upLeft());
        result.add(left());
        result.add(downLeft());
        result.add(downRight());

        return result;
    }

    public Set<Point> allMoves(boolean jumpsAvailable)
    {
        final Set<Point> neighbors = neighbors();

        if (!jumpsAvailable)
            return neighbors;

        final Set<Point> allCells = neighbors.stream().flatMap(p -> p.neighbors().stream())
                .collect(Collectors.toSet());

        allCells.remove(this);

        return allCells;
    }

    public Set<Point> jumps()
    {
        final Set<Point> neighbors = neighbors();

        final Set<Point> allCells = neighbors.stream().flatMap(p -> p.neighbors().stream())
                .collect(Collectors.toSet());

        allCells.remove(this);
        allCells.removeAll(neighbors);

        return allCells;
    }

    public Point left()
    {
        return new Point(i, j - 1);
    }

    public Point right()
    {
        return new Point(i, j + 1);
    }

    public Point downLeft()
    {
        return new Point(i + 1, j - 1 + (i % 2));
    }

    public Point downRight()
    {
        return new Point(i + 1, j + (i % 2));
    }

    public Point upLeft()
    {
        return new Point(i - 1, j - 1 + (i % 2));
    }

    public Point upRight()
    {
        return new Point(i - 1, j + (i % 2));
    }
}
