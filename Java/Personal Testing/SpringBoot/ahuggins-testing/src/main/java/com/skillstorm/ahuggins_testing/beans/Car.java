package com.skillstorm.ahuggins_testing.beans;

public class Car implements Vehicle {
    private Engine engine;

    public Car(){
        System.out.println("*** DEFAULT CAR CONSTRUCTOR ***");
    }

    public Car(Engine engine){
        this.engine = engine;
        System.out.println("*** CAR CONSTRUCTOR WITH ENGINE ***");
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void drive() {
        this.engine.run();
    }
}
