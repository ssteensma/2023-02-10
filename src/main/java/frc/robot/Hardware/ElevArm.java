package frc.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevArm {

    public static double target_position = 0;

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
        SmartDashboard.putNumber("Elevator-Arm Pos", GetPosition()   );
        SmartDashboard.putNumber("Elevator-Arm Tar", target_position );
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
        Retract();
    }

    public static void Extend () {
        
    }
    
    public static void Retract () {
        
    }

}
