package iu.edu.lukemeng.ducksservice.model;

public class MuteQuack implements QuackBehavior {
    private String quackFileName;

    public MuteQuack(String quackFileName) {
        this.quackFileName = quackFileName;
    }

    @Override
    public void quack() {
        System.out.println("Silence");
    }
}

