package com.codenjoy.dojo.snake.client;


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.LinkedList;
import java.util.List;


public class YourSolver implements Solver<Board> {

    private static final String USER_NAME = "qwerty.snb@gmail.com";
    private Board board;

    @Override
    public String get(Board board) {
        this.board = board;

        Point head = board.getHead();
        Point apple = board.getApples().get(0);
        return getDirection(head, apple).toString();
    }

    private Direction getDirection(Point head, Point apple) {
        int dx = head.getX() - apple.getX();
        int dy = head.getY() - apple.getY();

        LinkedList directions = getDirections(dx, dy);

        Direction direction = (Direction) directions.getFirst();

        if (!board.isTailOn(head, direction) &&
                !board.isStoneOn(head, direction)) {
            return direction;
        }
        if (directions.size() == 1) {
            return board.getSnakeDirection().clockwise();
        }
        return (Direction) directions.getLast();
    }

    private LinkedList getDirections(int dx, int dy){
        LinkedList result = new LinkedList();
        if(dx < 0){
            result.add(Direction.RIGHT);
        }
        if (dx > 0){
            result.add(Direction.LEFT);
        }
        if (dy < 0){
            result.add(Direction.DOWN);
        }
        if (dy > 0){
            result.add(Direction.UP);
        }
        return result;
    }

    public static void main(String[] args) {
        start(USER_NAME, WebSocketRunner.Host.REMOTE);
    }

    public static void start(String name, WebSocketRunner.Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
