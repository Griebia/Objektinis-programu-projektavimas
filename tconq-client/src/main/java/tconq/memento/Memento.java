package tconq.memento;

import tconq.entity.IEntity;

import java.util.List;

public class Memento {
    private List<IEntity> state;

    public Memento(List<IEntity> state) {
        this.state = state;
    }

    public List<IEntity> getState() {
        return state;
    }

    public void setState(List<IEntity> state) {
        this.state = state;
    }
}
