package frc.robot.Hardware;

public class Settings {

    // AUTON STAGES
    public static int
        MAX_NUMBER_OF_STAGES = 20;

    // CONTROLLER PORTS
    public static int
        DriveStickID = 0,
        ManipStickID = 1;

    // MAXIMUM SWERVE MODULE SPEEDS
    public static double 
        MAX_DRIVE_RATIO = 1.00;

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

    // CLICKS PER INCH
    public static double
        IN_PER_CLICK = ( Math.PI * 4 ) / EncTalonFX.kUnitsPerRevolution;

//
// ELEVATOR ASSIGNMENTS
//
    // ARM
    public static int
        Arm_CANID = 15;

    // ROLLER (Left, Right)
    public static int[]
        Roller_CANID = { 30, 35 };

    // LIFT
    public static int
        Lift_CANID = 20;

    public static int[]
        LiftSonar_DIO = { 0, 1 };

}
