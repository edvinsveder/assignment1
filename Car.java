import java.awt.*;

public abstract class Car {

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private double savedSpeed; // Saving the speed in another variable
    private Color color; // Color of the car
    private String modelName; // The car model name00
    protected abstract double speedFactor();   // Abstract var must be implemented in each subclass
    
    protected Car(int nrDoors, double enginePower, Color color, String modelName) { // Constructor
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    private void incrementSpeed(double amount) {
        savedSpeed = getCurrentSpeed(); // Saves speed in new variable in case speed goes above engine power so we can revert
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        if (getCurrentSpeed() > getEnginePower()) {
            currentSpeed = savedSpeed; // Reverting the change
            throw new IllegalArgumentException("Speed cannot go above engine power");
        }
    }

    private void decrementSpeed(double amount) {
        savedSpeed = getCurrentSpeed(); // Saves speed in new variable in case speed goes below 0 so we can revert
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
        if (getCurrentSpeed() < 0 ) {
            currentSpeed = savedSpeed; // Reverting the change
            throw new IllegalArgumentException("Speed cannot go below 0");
        }

    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1 ) { // Checks to make sure amount is in accepted range
            throw new IllegalArgumentException("Amount cannot go below 0 or above 1");
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1 ) { // Checks to make sure amount is in accepted range
            throw new IllegalArgumentException("Amount cannot go below 0 or above 1");
        }
        decrementSpeed(amount);
    }
}
