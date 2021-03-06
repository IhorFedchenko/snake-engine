package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.*;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.*;

/**
 * User: oleksandr.baglai
 * Date: 10/2/12
 * Time: 12:07 AM
 */
public class Board extends AbstractBoard<Elements> {

    @Override
    public Elements valueOf(char ch) {
        return Elements.valueOf(ch);
    }

    public List<Point> getApples() {
        return get(Elements.GOOD_APPLE);
    }
//Направление движения змейки
    public Direction getSnakeDirection() {
        Point head = getHead();
        if (isAt(head.getX(), head.getY(), Elements.HEAD_LEFT)) {
            return Direction.LEFT;
        } else if (isAt(head.getX(), head.getY(), Elements.HEAD_RIGHT)) {
            return Direction.RIGHT;
        } else if (isAt(head.getX(), head.getY(), Elements.HEAD_UP)) {
            return Direction.UP;
        } else {
            return Direction.DOWN;
        }
    }
//Координаты головы змейки
    public Point getHead() {
        List<Point> result = get(
                Elements.HEAD_UP,
                Elements.HEAD_DOWN,
                Elements.HEAD_LEFT,
                Elements.HEAD_RIGHT);
        return result.get(0);
    }
//Список всех припятствий которые могут быть
    public List<Point> getBarriers() {
        List<Point> result = getSnake();
        result.addAll(getStones());
        result.addAll(getWalls());
        return result;
    }
//Все координаты змейки
    public List<Point> getSnake() {
        List<Point> result = get(
                Elements.TAIL_END_DOWN,
                Elements.TAIL_END_LEFT,
                Elements.TAIL_END_UP,
                Elements.TAIL_END_RIGHT,
                Elements.TAIL_HORIZONTAL,
                Elements.TAIL_VERTICAL,
                Elements.TAIL_LEFT_DOWN,
                Elements.TAIL_LEFT_UP,
                Elements.TAIL_RIGHT_DOWN,
                Elements.TAIL_RIGHT_UP);
        result.add(0, getHead());
        return result;
    }

    public int getSnakeSize() {
        int result = 0;
        char[][] field = getField();
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
               char ch = field[x][y];
               if (ch == Elements.HEAD_UP.ch()||
                ch == Elements.HEAD_LEFT.ch()||
                ch == Elements.HEAD_RIGHT.ch()||
                ch == Elements.HEAD_DOWN.ch()||
                ch == Elements.TAIL_END_DOWN.ch()||
                ch == Elements.TAIL_END_LEFT.ch()||
                ch == Elements.TAIL_END_RIGHT.ch()||
                ch == Elements.TAIL_END_UP.ch()||
                ch == Elements.TAIL_HORIZONTAL.ch()||
                ch == Elements.TAIL_LEFT_DOWN.ch()||
                ch == Elements.TAIL_LEFT_UP.ch()||
                ch == Elements.TAIL_RIGHT_DOWN.ch()||
                ch == Elements.TAIL_RIGHT_UP.ch()||
                ch == Elements.TAIL_VERTICAL.ch()){
                    result++;
               }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("Board:\n%s\n" +
                        "Apple at: %s\n" +
                        "Stones at: %s\n" +
                        "Head at: %s\n" +
                        "Snake at: %s\n" +
                        "Current direction: %s",
                boardAsString(),
                getApples(),
                getStones(),
                getHead(),
                getSnake(),
                getSnakeDirection());
    }

    public List<Point> getStones() {
        return get(Elements.BAD_APPLE);
    }

    public List<Point> getWalls() {
        return get(Elements.BREAK);
    }
    public boolean isTailOn(Point from, Direction direction){
        List<Point> snake = getSnake();
        Point point = direction.change(from);
        return  snake.contains(point);
    }

    public boolean isStoneOn(Point from, Direction direction){
        List<Point> snake = getStones();
        Point point = direction.change(from);
        return  snake.contains(point);
    }
}