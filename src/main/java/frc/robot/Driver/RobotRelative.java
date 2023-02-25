package frc.robot.Driver;

import frc.robot.Elevator.Claw;
import frc.robot.Elevator.Roller;
import frc.robot.Hardware.Elevator;
import frc.robot.Hardware.Swerve;
import frc.robot.Mode.Teleop;

public class RobotRelative {
 
    public static StringBuilder SB = new StringBuilder();
    public static double Position = 0;

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
        if ( Teleop.DriveStick.getRawButton( 5 ) ) {
            Elevator.SetLO();
        }

        if ( Teleop.DriveStick.getRawButton( 3 ) ) {
            Roller.Suck();
        } else {
            Roller.Stop();
        }

        if ( Teleop.DriveStick.getRawButtonPressed( 11 ) ) {
            Claw.Toggle();
        }

        if ( Teleop.DriveStick.getRawButton( 6 ) ) {
            Elevator.PullIn();
        }

        if ( Teleop.DriveStick.getRawButton( 7 ) ) {
            Elevator.SetHI();
        }

        if ( Teleop.DriveStick.getRawButton( 4 ) ) {
            Roller.Spit();
        }

        // TESTING COMMANDS
        // if ( Teleop.DriveStick.getRawButton( 7 ) ) {
        //     Elevator.GoHigh();
        // } else if ( Teleop.DriveStick.getRawButton( 9 ) ) {
        //     Elevator.GoLow();
        // }

        // if ( Teleop.DriveStick.getRawButton( 7 ) ) {
        //     Arm.SetHI();
        // }

        // if ( Teleop.DriveStick.getRawButton( 9 ) ) {
        //     Arm.SetMD();
        // }

        // if ( Teleop.DriveStick.getRawButton(11 ) ) {
        //     Arm.SetLO();
        // }

        // if ( Teleop.DriveStick.getRawButtonPressed( 7 ) ) {
        //     Claw.Toggle();;
        // }



        // if ( Teleop.DriveStick.getRawButton( 7 ) ) {
        //     Lift.SetHigh();
        // }

        // if ( Teleop.DriveStick.getRawButton( 11 ) ) {
        //     Lift.SetLow();
        // }


        // System.out.println( SB.toString() );
        // SB.setLength(0);

        // SEND SPEEDS TO SWERVE CLASS
        Swerve.UpdateRobotRelative( Xmag*Xsig, Ymag*Ysig, Tmag*Tsig );
    }    
}
