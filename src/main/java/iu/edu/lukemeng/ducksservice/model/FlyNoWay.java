package iu.edu.lukemeng.ducksservice.model;

public class FlyNoWay implements FlyBehavior {
    private int speed = 0;

    @Override
    public int[] fly(int[] positionBefore) {
        int[] positionAfter = new int[2];
        positionAfter[0] = positionBefore[0];
        positionAfter[1] = positionBefore[1];
        return positionAfter;
    }
}

