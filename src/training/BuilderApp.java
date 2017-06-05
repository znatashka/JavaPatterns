package training;

public class BuilderApp {

    public static void main(String[] args) {
        System.out.println(new CarBuilder().build());
        System.out.println(new CarBuilder()
                .buildMake("BMW")
                .buildTransmission(Transmission.AUTO)
                .buildMaxSpeed(240)
                .build()
        );

        Director director = new Director();

        director.setBuilder(new FordMondeoBuilder());
        System.out.println(director.buildCar());

        director.setBuilder(new SubaruBuilder());
        System.out.println(director.buildCar());
    }
}

enum Transmission {
    MANUAL, AUTO
}

class Car {
    private String make;
    private Transmission transmission;
    private int maxSpeed;

    void setMake(String make) {
        this.make = make;
    }

    void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", transmission=" + transmission +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}

class CarBuilder {
    private String m = "Default";
    private Transmission t = Transmission.MANUAL;
    private int s = 120;

    CarBuilder buildMake(String m) {
        this.m = m;
        return this;
    }

    CarBuilder buildTransmission(Transmission t) {
        this.t = t;
        return this;
    }

    CarBuilder buildMaxSpeed(int s) {
        this.s = s;
        return this;
    }

    Car build() {
        Car car = new Car();
        car.setMake(m);
        car.setTransmission(t);
        car.setMaxSpeed(s);
        return car;
    }
}

abstract class NewCarBuilder {
    Car car;

    void createCar() {
        car = new Car();
    }

    abstract void buildMake();

    abstract void buildTransmission();

    abstract void buildMaxSpeed();

    Car getCar() {
        return car;
    }
}

class FordMondeoBuilder extends NewCarBuilder {

    @Override
    void buildMake() {
        car.setMake("Ford Mondeo");
    }

    @Override
    void buildTransmission() {
        car.setTransmission(Transmission.AUTO);
    }

    @Override
    void buildMaxSpeed() {
        car.setMaxSpeed(260);
    }
}

class SubaruBuilder extends NewCarBuilder {

    @Override
    void buildMake() {
        car.setMake("Subaru");
    }

    @Override
    void buildTransmission() {
        car.setTransmission(Transmission.MANUAL);
    }

    @Override
    void buildMaxSpeed() {
        car.setMaxSpeed(320);
    }
}

class Director {
    private NewCarBuilder builder;

    public void setBuilder(NewCarBuilder builder) {
        this.builder = builder;
    }

    Car buildCar() {
        builder.createCar();

        builder.buildMake();
        builder.buildTransmission();
        builder.buildMaxSpeed();

        return builder.getCar();
    }
}