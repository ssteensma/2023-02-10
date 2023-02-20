package frc.robot.Hardware;

public class Settings {

    // AUTON STAGES
    public static int
        MAX_NUMBER_OF_STAGES = 12;

    // CONTROLLER PORTS
    public static int
        DriveStickID = 0,
        ManipStickID = 1;

    // MAXIMUM SWERVE MODULE SPEEDS
    public static double 
        MAX_DRIVE_RATIO = 0.20;

    // MODULE ASSIGNMENTS
    public static int
        FL_moduleNumber = 1,
        FR_moduleNumber = 4,
        RL_moduleNumber = 5,
        RR_moduleNumber = 3;

    // MODULE LOCATIONS
    public static double
        FLx =  1, FLy =  1,
        FRx =  1, FRy = -1,
        RLx = -1, RLy =  1,
        RRx = -1, RRy = -1;

    // CLICKS PER FOOT
    public static double
        IN_PER_CLICK = ( Math.PI * 4 ) / EncTalonFX.kUnitsPerRevolution;

    // ELEVATOR ASSIGNMENTS
    public static int
        Lhand_CAN_ID = 0,
        Rhand_CAN_ID = 1;

    public static double
        ClawGrabSpeed = -0.10,
        ClawDropSpeed = +0.10;

}
