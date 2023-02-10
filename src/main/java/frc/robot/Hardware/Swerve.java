package frc.robot.Hardware;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Swerve {

    // CHASSIS SPEEDS
    public static ChassisSpeeds
        RobotSpeed;

    // MODULE DEFINITIONS
    public static Module
        FL_module,
        FR_module,
        RL_module,
        RR_module;

    // TRANSLATION OBJECTS
    static Translation2d
        FL_Trans2d,
        FR_Trans2d,
        RL_Trans2d,
        RR_Trans2d;

    // KINEMATICS OBJECT
    public static SwerveDriveKinematics
        Kinematics;

    // ODOMETRY OBJECT
    // public static SwerveDriveOdometry
    //     Odometry;

    // INITIALIZE
    public static void Initialize () {

        // CHASSIS SPEEDS
        RobotSpeed = new ChassisSpeeds( 0, 0, 0 );

        // MODULE DEFINITIONS
        FL_module = new Module( "FL", Settings.FL_moduleNumber );
        FR_module = new Module( "FR", Settings.FR_moduleNumber );
        RL_module = new Module( "RL", Settings.RL_moduleNumber );
        RR_module = new Module( "RR", Settings.RR_moduleNumber );

        // TRANSLATION OBJECT
        FL_Trans2d = new Translation2d( Settings.FLx, Settings.FLy );
        FR_Trans2d = new Translation2d( Settings.FRx, Settings.FRy );
        RL_Trans2d = new Translation2d( Settings.RLx, Settings.RLy );
        RR_Trans2d = new Translation2d( Settings.RRx, Settings.RRy );

        // KINEMATICS OBJECT
        Kinematics = new SwerveDriveKinematics( FL_Trans2d, FR_Trans2d, RL_Trans2d, RR_Trans2d );
    }

    public static void Display () {
        SmartDashboard.putNumber("Robot-vx", RobotSpeed.vxMetersPerSecond     );
        SmartDashboard.putNumber("Robot-vy", RobotSpeed.vyMetersPerSecond     );
        SmartDashboard.putNumber("Robot-vt", RobotSpeed.omegaRadiansPerSecond );

        FL_module.Display();
        FR_module.Display();
        RL_module.Display();
        RR_module.Display();
    }

    public static void UpdateFieldRelative ( double vx, double vy, double vt ) {
        Rotation2d    Rot2d  = Rotation2d.fromDegrees( Navigation.GetYaw() );
        ChassisSpeeds Speeds = ChassisSpeeds.fromFieldRelativeSpeeds( vx, vy, vt, Rot2d );
        Update( Speeds );
    }

    public static void UpdateRobotRelative ( double vx, double vy, double vt ) {
        ChassisSpeeds Speeds = new ChassisSpeeds( vx, vy, vt );
        Update( Speeds );
    }

    private static void Update ( ChassisSpeeds Speeds ) {

        // WAIT FOR WHEEL TO ADJUST TO HEADING
        boolean ok_to_drive = true;
        if ( FL_module.turning_flag ) { ok_to_drive = false; }
        if ( FR_module.turning_flag ) { ok_to_drive = false; }
        if ( RL_module.turning_flag ) { ok_to_drive = false; }
        if ( RR_module.turning_flag ) { ok_to_drive = false; }

        // ALIGN WHEELS BEFORE TRANSLATION
        if ( ! ok_to_drive ) {
            Speeds.vxMetersPerSecond = 0;
            Speeds.vyMetersPerSecond = 0;
        }

        // CALCULATE INDIVIDUAL MODULE STATES
        SwerveModuleState[] ModuleStates = Kinematics.toSwerveModuleStates( Speeds );

        // NORMALIZE WHEEL RATIOS IF ANY SPEED IS ABOVE SPECIFIED MAXIMUM
        SwerveDriveKinematics.desaturateWheelSpeeds( ModuleStates, Settings.MAX_DRIVE_RATIO );

        // UPDATE ROBOT SPEEDS
        RobotSpeed = Kinematics.toChassisSpeeds( ModuleStates );

        // UPDATE EACH MODULE
        FL_module.Update( ModuleStates[0] );
        FR_module.Update( ModuleStates[1] );
        RL_module.Update( ModuleStates[2] );
        RR_module.Update( ModuleStates[3] );
    }

}