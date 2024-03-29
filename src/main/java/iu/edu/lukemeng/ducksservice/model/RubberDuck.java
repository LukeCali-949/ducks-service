package iu.edu.lukemeng.ducksservice.model;

public class RubberDuck extends Duck{
    public RubberDuck(int id, DuckType type) {
        super(id, type);
        setFlyBehavior(new FlyNoWay());

        setQuackBehavior(new Squeak("quack.mp4"));
    }

    @Override
    void display() {
        System.out.println("Looks like a rubberduck");
    }
}
