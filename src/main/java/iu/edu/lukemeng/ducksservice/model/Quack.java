package iu.edu.lukemeng.ducksservice.model;

public class Quack implements QuackBehavior {
    private String quackFileName;

    public Quack(String quackFileName) {
        this.quackFileName = quackFileName;
    }

    @Override
    public void quack() {
        System.out.println("Quack Quack");
    }
}
