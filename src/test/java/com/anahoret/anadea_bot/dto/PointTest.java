package com.anahoret.anadea_bot.dto;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class PointTest
{
    @Test
    public void name() throws Exception
    {
        final Set<Point> result = new Point(0, 7).allMoves(true);
        System.out.println(result);
    }
}
