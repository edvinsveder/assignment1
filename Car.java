import java.awt.*;

public abstract class Car implements Movable {

    // Private fields used within the class 
    private int nrDoors;        // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed;// The current speed of the car
    private double savedSpeed;  // Saving the speed in another variable
    private Color color;        // Color of the car
    private String modelName;   // The car model name
    private double x_cord;  // Current X location 
    private double y_cord;  // Current Y location
    private int direction;  // Current heading / direciton

    protected abstract double speedFactor(); // Abstract var must be implemented in each subclass
    
    protected Car(int nrDoors, double enginePower, Color color, String modelName) { // Constructor
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.x_cord = 0;
        this.y_cord = 0;
        this.direction = 0;
        stopEngine();
    }

    public double getX() {
        return x_cord;
    }

    public double getY() {
        return y_cord;
    }

    public int getDirection() {
        return direction;
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
        }
    }

    private void decrementSpeed(double amount) {
        savedSpeed = getCurrentSpeed(); // Saves speed in new variable in case speed goes below 0 so we can revert
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
        if (getCurrentSpeed() < 0 ) {
            currentSpeed = savedSpeed; // Reverting the change
        }
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }
     
    public void move() { // Update  
        switch (direction) {
            case 0:
                this.y_cord = this.y_cord + currentSpeed; // Moves north
                return;
            
            case 1:
                this.x_cord = this.x_cord + currentSpeed; // Moves east
                return;
            
            case 2:
                this.y_cord = this.y_cord - currentSpeed; // Moves south
                return;

            case 3:
                this.x_cord = this.x_cord - currentSpeed; // Moves west
                return;

            default:
                return;
        }        
    }
    
    public void turnLeft() {
        this.direction--;
        if(this.direction < 0) {
            this.direction = 3;
        }
    }
    
    public void turnRight() {
        this.direction++;
        if(this.direction > 3) {
            this.direction = 0;
        }
    }

    public void gas(double amount) {
        if (amount > 1) amount = 1;
        if (amount < 0) amount = 0;
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount > 1) amount = 1;
        if (amount < 0) amount = 0;
        decrementSpeed(amount);
    }   
}
