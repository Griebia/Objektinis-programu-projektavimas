package tconq.factories;

public interface AbstractFactory<T>{
    T create(String UnitOrBuildingType);
}