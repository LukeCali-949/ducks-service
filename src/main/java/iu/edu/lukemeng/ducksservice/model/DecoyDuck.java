package iu.edu.lukemeng.ducksservice.model;

public class DecoyDuck extends Duck{
    public DecoyDuck(int id, DuckType type) {
        super(id, type);
        FlyBehavior FlyNoWay = new FlyNoWay();
        setFlyBehavior(FlyNoWay);

        setQuackBehavior(new MuteQuack("quack.mp4"));
    }

    @Override
    void display() {
        System.out.println("looks like a decoy duck");
    }
}
