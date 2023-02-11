package frc.robot.Mode;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Hardware.Autopilot;
import frc.robot.Hardware.Elevator;
import frc.robot.Hardware.Stage;
import frc.robot.Hardware.Swerve;
import frc.robot.Hardware.Track;

public class Autonomous {

    public static final String kDefault = "Nothing";
    public static final String kPath01  = "Path-01";
    public static final String kPath02  = "Path-02";
    public static final String kPath03  = "Path-03";
    public static final String kPath04  = "Path-04";
    public static final SendableChooser<String> chooser = new SendableChooser<>();

    public static void Initialize () {
        chooser.setDefaultOption("Nothing", kDefault );
        chooser.setDefaultOption("Path 01", kPath01  );
        chooser.setDefaultOption("Path 02", kPath02  );
        chooser.setDefaultOption("Path 03", kPath03  );
        chooser.setDefaultOption("Path 04", kPath04  );
        chooser.setDefaultOption("Path 05", kPath04  );
        SmartDashboard.putData  ("PATH",    chooser  );
 
        Stage.Initialize();
    }

    public static void Periodic () {

        Stage.Begin();

        switch ( chooser.getSelected() ) {
            case "Default" : Track.Track_00(); break;
            case "Path-01" : Track.Track_01(); break;
            case "Path-02" : Track.Track_02(); break;
            case "Path-03" : Track.Track_03(); break;
            case "Path-04" : Track.Track_04(); break;
            case "Path-05" : Track.Track_05(); break;
        }

        Stage.Next();

        if ( Stage.Number <= 100 ) { Stage.Display(); }


        SmartDashboard.putString("CURRENT PATH", chooser.getSelected() );

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
