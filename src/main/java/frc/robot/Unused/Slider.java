package frc.robot.Unused;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Slider {

    public static double SP = 0;

//
//
//
    public static void Initialize () {

    }

    public static void Periodic () {
        // Some sort of controller to find the position
        // difference and set the motor ratio. Might need
        // a PID controller to hold position.
    }

    public static void Display () {
        SmartDashboard.putNumber("Slider Pos", GetPosition() );
        SmartDashboard.putNumber("Slider Tar", SP            );
    }

//
//
//
    public static double GetPosition () {
        return 0;
    }

    public static void SetPosition ( double pos ) {
        SP = pos;
    }

//
//
//
    public static void Reset () {
        Retract();
    }

    public static void Extend () {
        
    }
    
    public static void Retract () {
        
    }

}
