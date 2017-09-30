package com.anahoret.anadea_bot.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Point
{
    private final int i, j;

    public int[] toArray() {
        return new int[] {i, j};
    }

    public List<Point> neighbors() {
        return Arrays.asList(
                right(),
                upRight(),
                upLeft(),
                left(),
                downLeft(),
                downRight()
        );
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
