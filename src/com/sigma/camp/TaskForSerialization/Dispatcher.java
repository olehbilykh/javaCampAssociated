package com.sigma.camp.TaskForSerialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dispatcher {
    public static void main(String[] args) {
        List<Plane> planes = new ArrayList<>(List.of(
                new Plane(new Engine("rocketFuel", 123000), 2000, 5000, "Jet", 800, new Chassis(new Wheel(1, 15), 6)),
                new Plane(new Engine("diesel", 1200), 2012, 1000, "Cargo", 1500, new Chassis(new Wheel(2, 100), 12)),
                new Plane(new Engine("benzine", 23000), 1991, 1500, "Passenger", 1000, new Chassis(new Wheel(1.7, 70), 8))
        ));
        planes.sort(Comparator.comparing(Vehicle::getYearOfProduction));
        System.out.println("1 " + planes);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/com/sigma/camp/TaskForSerialization/planes"))) {
            out.writeObject(planes);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<Plane> planesDeserealized;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/com/sigma/camp/TaskForSerialization/planes"))) {
            planesDeserealized = (List<Plane>) in.readObject();
            System.out.println("2 " + planesDeserealized);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}

class Vehicle {
    private Engine engine;
    private int yearOfProduction;
    private double speed;

    public Vehicle() {
    }

    public Vehicle(Engine engine, int yearOfProduction, double speed) {
        this.engine = engine;
        this.yearOfProduction = yearOfProduction;
        this.speed = speed;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}

class Plane extends Vehicle implements Serializable {
    private String model;
    private double maxFlightLength;
    private transient Chassis chassis;

    public Plane(String model, double maxFlightLength, Chassis chassis) {
        this.model = model;
        this.maxFlightLength = maxFlightLength;
        this.chassis = chassis;
    }

    public Plane(Engine engine, int yearOfProduction, double speed, String model, double maxFlightLength, Chassis chassis) {
        super(engine, yearOfProduction, speed);
        this.model = model;
        this.maxFlightLength = maxFlightLength;
        this.chassis = chassis;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getMaxFlightLength() {
        return maxFlightLength;
    }

    public void setMaxFlightLength(double maxFlightLength) {
        this.maxFlightLength = maxFlightLength;
    }

    public Chassis getChassis() {
        return chassis;
    }

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    private void writeObject(ObjectOutputStream out) {

        try {
            out.defaultWriteObject();
            out.writeInt(chassis.getNumberOfWheels());
            out.writeDouble(chassis.getWheel().getDiameter());
            out.writeDouble(chassis.getWheel().getLoad());
        } catch (IOException e) {
            System.out.println("Exception when trying to serialize: " + e.getMessage());
        }

    }

    private void readObject(ObjectInputStream in) {
        try {
            in.defaultReadObject();
            int numberOfWheels = in.readInt();
            double wheelDiameter = in.readDouble();
            double wheelLoad = in.readDouble();
            this.chassis = new Chassis(new Wheel(wheelDiameter, wheelLoad), numberOfWheels);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception when trying to deserialize: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Plane{" +
                "model='" + model + '\'' +
                ", maxFlightLength=" + maxFlightLength +
                ", chassis=" + chassis +
                '}';
    }
}

class Ship extends Vehicle implements Externalizable {

    private double waterTonnage;
    private double length;
    private Bumboat bumboat;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}

class Engine {
    private String type;
    private double power;

    public Engine() {
    }

    public Engine(String type, double power) {
        this.type = type;
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "type='" + type + '\'' +
                ", power=" + power +
                '}';
    }
}

class Chassis {
    private transient Wheel wheel;
    private int numberOfWheels;

    public Chassis() {
    }

    public Chassis(Wheel wheel, int numberOfWheels) {
        this.wheel = wheel;
        this.numberOfWheels = numberOfWheels;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }
//    private void writeObject(ObjectOutputStream out){
//        try {
//            out.defaultWriteObject();
//            out.writeDouble(wheel.getDiameter());
//            out.writeDouble(wheel.getLoad());
//        } catch (IOException e) {
//            System.out.println("Exception when trying to serialize: " + e.getMessage());
//        }
//    }

    @Override
    public String toString() {
        return "Chassis{" +
                "wheel=" + wheel +
                ", numberOfWheels=" + numberOfWheels +
                '}';
    }
}

class Bumboat {
    private int numberOfPassengers;
    private String material;
}

class Wheel {
    private double diameter;
    private double load;

    public Wheel() {
    }

    public Wheel(double diameter, double load) {
        this.diameter = diameter;
        this.load = load;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "diameter=" + diameter +
                ", load=" + load +
                '}';
    }
}
