package com.codenjoy.dojo.snake.client;


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.List;


public class YourSolver implements Solver<Board> {

    private static final String USER_NAME = "qwerty.snb@gmail.com";

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;

        Point head = board.getHead();
        Point apple = board.getApples().get(0);
        Point stone = board.getStones().get(0);
        List<Point> snake = board.getSnake();
        List<Point> wall = board.getWalls();
        

        for(Point body : snake){
            System.out.println("BodyX: "+body.getX());
            System.out.println("BodyY: "+body.getY());
        }




        // found snakeHead
        int snakeHeadX = head.getX();
        int snakeHeadY = head.getY();


        // found apple
        int appleX = apple.getX();
        int appleY = apple.getY();


        int dx = snakeHeadX - appleX;
        int dy = snakeHeadY - appleY;

        //decision point

        if (dx < 0) {
            return Direction.RIGHT.toString();
        }
        if (dx > 0) {
            return Direction.LEFT.toString();
        }
        if (dy < 0) {
            return Direction.DOWN.toString();
        }
        if (dy > 0) {
            return Direction.UP.toString();
        }

        return Direction.UP.toString();
    }

    public static void main(String[] args) {
        start(USER_NAME, WebSocketRunner.Host.REMOTE);
    }

    public static void start(String name, WebSocketRunner.Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(new RandomDice()),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
