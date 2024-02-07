package iu.edu.lukemeng.ducksservice.model;

public class Redhead extends Duck{
    public Redhead(int id, DuckType type) {
        super(id, type);
    }

    @Override
    void display() {
        System.out.println("Looks like a redhead");
    }
}
