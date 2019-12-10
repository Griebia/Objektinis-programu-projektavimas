package tconq.memento;

import tconq.entity.IEntity;

import java.util.List;

public class Memento {
    private Data state;

    public Memento(Data state) {
        this.state = state;
    }

    public Data getState() {
        return state;
    }

    public void setState(Data state) {
        this.state = state;
    }
}
