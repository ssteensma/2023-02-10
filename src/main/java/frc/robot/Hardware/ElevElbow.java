package frc.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevElbow {

    public static double target_position;
    public static double last_position;

//
// Need to determine what kind of motor controller is being
// used here. Of course, it might also be pneumatics.
//
    public static void Initialize () {

    }

    public static void Periodic () {
        // Some sort of controller to find the position
        // difference and set the motor ratio. Might need
        // a PID controller to hold position.
    }

    public static void Display () {
        SmartDashboard.putNumber("Elevator-Wrist Pos", GetPosition()   );
        SmartDashboard.putNumber("Elevator-Wrist Tar", target_position );
    }

//
//
//
    public static double GetPosition () {
        return 0;
    }

    public static void SetPosition ( double pos ) {
        target_position = pos;
    }

//
//
//
    public static void Reset () {
        Bend();
    }

    public static void Bend () {
        
    }
    
    public static void Straighten () {
        
    }

}
