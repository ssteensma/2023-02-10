package frc.robot.Mode;

import frc.robot.Hardware.AutonChooser;
import frc.robot.Hardware.Autopilot;
import frc.robot.Hardware.Elevator;
import frc.robot.Hardware.Stage;
import frc.robot.Hardware.Swerve;
import frc.robot.Hardware.Path;

public class Autonomous {

    public static void Initialize () {
        Stage.Initialize();
    }

    public static void Periodic () {
        Stage.Begin();

        switch ( AutonChooser.chooser.getSelected() ) {
            case "Default" : Path.Track_00(); break;
            case "Path-01" : Path.Track_01(); break;
            case "Path-02" : Path.Track_02(); break;
            case "Path-03" : Path.Track_03(); break;
            case "Path-04" : Path.Track_04(); break;
            case "Path-05" : Path.Track_05(); break;
        }

        Stage.Next();

        if ( Stage.StageNumber <= 40 ) { Stage.Display(); }

        // EXECUTE COMMANDS
        Elevator.Periodic();
        Swerve.UpdateRobotRelative( Autopilot.vx, Autopilot.vy, Autopilot.vt );
 
        // Example chassis speeds: 1 meter per second forward, 3 meters
        // per second to the left, and rotation at 1.5 radians per second
        // counterclockwise.                         F    L   CCW
        // ChassisSpeeds speeds = new ChassisSpeeds(1.0, 3.0, 1.5);

        // THESE NEED TO BE SET BY THE AUTONOMOUS MODE

        // double curPitch  = Navigation.GetPitch();
        // SmartDashboard.putNumber("Robot-Pitch", curPitch);
        // SmartDashboard.putNumber("Robot-Stage", stage );

        // Navigation.Periodic();
        // double curAng = Navigation.GetDirection();
        // double target = 0;

        // double diff = curAng - target;

        // // SMALLEST ANGLE TO SWIVEL: -180 to 180
        // double minTurn = ( diff + 180 ) % 360 - 180;
        //     double turnMag = Math.abs   ( minTurn );
        //     double turnDir = Math.signum( minTurn );

        // // MINIMIZE WHEEL SWIVEL: +120 becomes -60
        // if ( turnMag > 0 ) {
        //     // turnMag  = 180 - minTurn; // Turn smaller angle
        //     turnDir *= -1;            // and reverse swivel
        // }

        // // DETERMINE POWER USING PSEUDO PID CONTROLLER
        // if      ( turnMag > 20 ) { vt = 0.15; }
        // else if ( turnMag > 10 ) { vt = 0.10; } 
        // else if ( turnMag >  3 ) { vt = 0.06; } 
        // else if ( turnMag >  2 ) { vt = 0.00; } 
        // else if ( turnMag >  1 ) { vt = 0.00; } 
        // else                     { vt = 0.00; }

        // vt *= turnDir;

        // SET MOTOR CONTROLLERS
        // SteerMotor.set( TalonFXControlMode.PercentOutput, PID.calculate( SP, PV ) );

        // double diff = ( cur  ) % 360 - 180;
     
    }

}
