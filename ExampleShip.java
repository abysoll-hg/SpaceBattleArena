import java.awt.Color;

import ihs.apcs.spacebattle.*;
import ihs.apcs.spacebattle.commands.*;

public class ExampleShip extends BasicSpaceship {
    private int f = 0;
    private int worldWidth;
    private int worldHeight;
    private Point midpoint;
    public static void main(String[] args)
    {
        TextClient.run("10.56.98.121", new ExampleShip());
    }

    @Override
    public RegistrationData registerShip(int numImages, int worldWidth, int worldHeight)
    {
        this.worldWidth = worldWidth/2;
        this.worldHeight = worldHeight/2;
        this.midpoint = new Point(this.worldWidth, this.worldHeight);
        return new RegistrationData("Torenship", new Color(0, 255, 255), 5);
    }
    public int getAngleToMidpoint(Environment env) {
       ObjectStatus ship = env.getShipStatus();
       return ship.getPosition().getAngleTo(this.midpoint) - ship.getOrientation();
    }
    @Override
    public ShipCommand getNextCommand(BasicEnvironment env)
    {
        f++;
        if (env.getShipStatus().getPosition().getDistanceTo(this.midpoint) < 275) {
            return new BrakeCommand(0.01);
        }
        if (false) {
            return new RadarCommand(2);
        }
        else {
            if (f % 2 == 0) {
                if (env.getShipStatus().getOrientation() > Math.abs(getAngleToMidpoint(env)-5) && env.getShipStatus().getOrientation() < Math.abs(getAngleToMidpoint(env)+5)) {
                     return new ThrustCommand('F',1,1);
                }
                else {
                     return new ThrustCommand('B',1,1);
                }
            }
            else {
                return new RotateCommand(getAngleToMidpoint(env));
            }
        }
    }
}