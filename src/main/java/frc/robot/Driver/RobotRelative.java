package frc.robot.Driver;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Elevator.Arm;
import frc.robot.Elevator.Claw;
import frc.robot.Elevator.Roller;
import frc.robot.Hardware.Elevator;
import frc.robot.Hardware.Swerve;
import frc.robot.Mode.Teleop;

public class RobotRelative {
 
    public static void Periodic () {

        // SHORT CUT FOR MANIP STICK
        XboxController Manip = Teleop.ManipStick;

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

        if ( Manip.getStartButton() ) {
            Arm.SetLO();
        }

        if ( Manip.getBackButton() ) {
            Arm.SetHI();
        }


        // TESTING COMMANDS
        if ( Manip.getAButtonPressed() ) {
            Elevator.Preset1();
        }
        
        if ( Manip.getBButtonPressed() ) {
            Elevator.Preset2();
        }

        if ( Manip.getYButtonPressed() ) {
            Elevator.Preset3();
        }

        Roller.Stop();

        if ( Manip.getRightBumper() ) {
            Roller.Suck();
        }
        
        if ( Manip.getLeftBumper() ) {
            Roller.Spit();
        }
        
        if ( Manip.getXButtonPressed() ) {
            Claw.Toggle();
        }

        if ( Manip.getLeftStickButton() ) {
            Arm.Arm.setSelectedSensorPosition( 90 );
        }

        // EXECUTE SWERVE
        Swerve.UpdateRobotRelative( Xmag*Xsig, Ymag*Ysig, Tmag*Tsig );
    }    
}
