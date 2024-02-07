package iu.edu.lukemeng.ducksservice.model;

public class Squeak implements QuackBehavior {
    private String quackFileName;

    public Squeak(String quackFileName) {
        this.quackFileName = quackFileName;
    }

    @Override
    public void quack() {
        System.out.println("Squeak Squeak");
    }
}
