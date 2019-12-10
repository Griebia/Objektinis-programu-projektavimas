package tconq.interpreter;

import tconq.App;

public class Gold extends Expression {

    public void add(int amount) {
        App.player.addGold(amount);
        System.out.println("Current gold: " + App.player.getGold());
    }

    public void remove(int amount) {
        App.player.addGold(-amount);
        System.out.println("Current gold: " + App.player.getGold());
    }
}
