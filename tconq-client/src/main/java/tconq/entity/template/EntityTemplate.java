package tconq.entity.template;

abstract class EntityTemplate{
    public abstract void addPoints();

    public final void processEntity(){
        addPoints();
    }
}