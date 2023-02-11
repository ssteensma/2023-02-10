package frc.robot.Driver;

import frc.robot.Hardware.ElevLift;
import frc.robot.Hardware.Swerve;
import frc.robot.Mode.Teleop;

public class Default {
 
    public static void Periodic () {

        // GET VALUES
        double Xratio = Teleop.Xratio;
        double Yratio = Teleop.Yratio;
        double Tratio = Teleop.Tratio;

        // JOYSTICK COMPONENTS
        double Xmag = Math.abs( Xratio ); double Xsig = Math.signum( Xratio );
        double Ymag = Math.abs( Yratio ); double Ysig = Math.signum( Yratio );
        double Tmag = Math.abs( Tratio ); double Tsig = Math.signum( Tratio );

        // APPLY DEADZONE AND SCALE SPEEDS: e.g., 0.20 is a 20% dead zone
        if ( Xmag < 0.10 ) { Xmag = 0; } else { Xmag = Math.pow( Xmag-0.10, 2 ) / 2; }
        if ( Ymag < 0.10 ) { Ymag = 0; } else { Ymag = Math.pow( Ymag-0.10, 2 ) / 2; }
        if ( Tmag < 0.20 ) { Tmag = 0; } else { Tmag = Math.pow( Tmag-0.20, 2 ) / 2; }
        
        // TESTING COMMANDS
        if      ( Teleop.DriveStick.getRawButton( 7 ) ) { ElevLift.SetHigh (); }
        else                                            { ElevLift.SetLow  (); }

        // SEND SPEEDS TO SWERVE CLASS
        Swerve.UpdateRobotRelative( Xmag*Xsig, Ymag*Ysig, Tmag*Tsig );
    }    
}
