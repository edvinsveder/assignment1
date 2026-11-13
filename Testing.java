public class Testing {
    private static void reportResult(String testName, boolean success) {
        IO.println((success ? "SUCCESS: " : "FAILED: ") + testName);
    }

    private static void testTurbo() {
        Saab95 slowSaab = new Saab95();
        Saab95 fastSaab = new Saab95();
        fastSaab.setTurboOn();
        slowSaab.setTurboOff();
        fastSaab.gas(0.5);
        slowSaab.gas(0.5);
        boolean result = slowSaab.getCurrentSpeed() < fastSaab.getCurrentSpeed();
        reportResult("Turbo yields more speed", result);
    }

    private static void testMovement() {
        Saab95 saab = new Saab95();
        Volvo240 volvo = new Volvo240();
        
        // Set gas to max for both types of cars
        saab.gas(1);
        saab.move();
        boolean saabSpeed = (saab.getCurrentSpeed() > 0 && saab.getX() == 0);
        reportResult("Saab moved", saabSpeed);

        volvo.gas(1);
        volvo.move();
        boolean volvoSpeed = (volvo.getCurrentSpeed() > 0 && volvo.getX() == 0);
        reportResult("Volvo moved", volvoSpeed);


        // Turn left 4 times in a row - verify no overflow
        for (int i = 0; i < 4; i++) {
            saab.turnLeft();
            saab.move();
            IO.println("Saab turning left.");
            IO.println("Current direction:" + saab.getDirection());
        }

        boolean saabResult = saab.getDirection() == 0;
        reportResult("Saab completed a double u-turn", saabResult);

        for (int i = 0; i < 4; i++) {
            volvo.turnLeft();
            volvo.move();

            IO.println("Volvo turning left.");
            IO.println("Current direction:" + volvo.getDirection());
        }
        boolean volvoResult = volvo.getDirection() == 0;
        reportResult("Volvo completed a double u-turn", volvoResult);
    }    

    private static void testReverse() {
        Saab95 saab = new Saab95();
        Volvo240 volvo = new Volvo240();
        // Car is not allowed to reverse.
        saab.gas(-1);
        volvo.gas(-1);
        boolean saabResult = (saab.getCurrentSpeed() >= 0);
        reportResult("Saab speed cannot be set to negative value", saabResult);

        boolean volvoResult = (volvo.getCurrentSpeed() >= 0);
        reportResult("Volvo speed cannot be set to negative value", volvoResult);
    }

    private static void testGasBrake() {
        Saab95 saab = new Saab95();
        Volvo240 volvo = new Volvo240();
        // Speed cannot be higher than 5
        saab.gas(5);
        volvo.gas(5);

        boolean saabResult = (saab.getCurrentSpeed() >= 0);
        reportResult("Saab speed cannot be set to value higher than 1", saabResult);

        boolean volvoResult = (volvo.getCurrentSpeed() >= 0);
        reportResult("Volvo speed cannot be set to value higher than 1", volvoResult);

        saab.brake(-1);
        volvo.brake(-1);

        boolean saabBrakeResult = (saab.getCurrentSpeed() >= 0);
        reportResult("Saab brake cannot be set to negative value", saabBrakeResult);

        boolean volvoBrakeResult = (volvo.getCurrentSpeed() >= 0);
        reportResult("Volvo brake cannot be set to negative value", volvoBrakeResult);

        saab.brake(5);
        volvo.brake(5);

        boolean saabHighBrakeResult = (saab.getCurrentSpeed() >= 0);
        reportResult("Saab brake cannot be set to value higher than 1", saabHighBrakeResult);

        boolean volvoHighBrakeResult = (volvo.getCurrentSpeed() >= 0);
        reportResult("Volvo brake cannot be set to value higher than 1", volvoHighBrakeResult);
    }

    static void main() {
        testTurbo(); 
        testMovement();
        testReverse();
        testGasBrake();
    }
}