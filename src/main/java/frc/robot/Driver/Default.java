package frc.robot.Driver;

// import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Hardware.Autopilot;
import frc.robot.Hardware.Swerve;
import frc.robot.Mode.Teleop;

public class Default {
 
    public static void Periodic () {

        Autopilot.vx = 0;
        Autopilot.vy = 0;
        Autopilot.vt = 0;

        // GET VALUES
        // Joystick DriveStick = Teleop.DriveStick;
        double   Xratio     = Teleop.Xratio;
        double   Yratio     = Teleop.Yratio;
        double   Tratio     = Teleop.Tratio;

        if ( Math.abs( Xratio ) < 0.20 ) { Xratio = 0; }
        if ( Math.abs( Yratio ) < 0.20 ) { Yratio = 0; }

        // SIMPLE JOYSTICK DEADBAND
        double Tmag = Math.abs   ( Tratio );
        double Tsig = Math.signum( Tratio );

            // SCALE TURNING SPEED
            if ( Tmag < 0.20 ) {
                Tmag = 0;
            }
            else {
                Tmag = Math.pow( Tmag-0.20, 2 ) / 2;
            }

        // SEND SPEEDS TO SWERVE CLASS
        Swerve.UpdateRobotRelative( Xratio, Yratio, Tmag*Tsig );    }
    
}
