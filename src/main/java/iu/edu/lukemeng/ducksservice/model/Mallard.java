package iu.edu.lukemeng.ducksservice.model;

public class Mallard extends Duck{


    public Mallard(int id, DuckType type) {
        super(id, type);
    }

    @Override
    void display() {
        System.out.println("Looks like a mallard");
    }
}
