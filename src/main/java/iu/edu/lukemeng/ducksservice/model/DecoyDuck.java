package iu.edu.lukemeng.ducksservice.model;

public class DecoyDuck extends Duck{
    public DecoyDuck(int id, DuckType type) {
        super(id, type);
    }

    @Override
    void display() {
        System.out.println("looks like a decoy duck");
    }
}
