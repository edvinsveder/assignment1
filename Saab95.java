import java.awt.*;

public class Saab95 extends Car{

    public Saab95() {
        super(2, 125, Color.red, "Saab95");
        turboOn = false;
    }

    private boolean turboOn;

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }
    
    protected double speedFactor() {
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
    

}
