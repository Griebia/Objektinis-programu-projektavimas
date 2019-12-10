package tconq.interpreter;

import tconq.App;

public class Points extends Expression {

    public void add(int amount) {
        App.player.addPoints(amount);
        System.out.println("Current points: " + App.player.getPoints());
    }

    public void remove(int amount) {
        App.player.addPoints(-amount);
        System.out.println("Current points: " + App.player.getPoints());
    }

    @Override
    public void interpret(Context context) {
        // TODO Auto-generated method stub

    }
}