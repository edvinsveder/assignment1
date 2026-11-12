import java.awt.*;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;
    
    public Volvo240() {
        super(4, 100, Color.black, "Volvo240");
    }

    // Volvo240 implements its own speedFactor as designadet by its superclass
    protected double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }
}
