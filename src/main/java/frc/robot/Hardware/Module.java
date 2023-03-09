package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Driver.RobotRelative;
import frc.robot.Mode.Teleop;

public class Module {

    public EncTalonFX    SteerEncoder;
    public int           ModuleNumber;
    public String        ModuleName;
    public WPI_TalonFX   DriveMotor;
    public WPI_TalonFX   SteerMotor;
    public PIDController SteerPID;
    public double        TurnDiff     = 0;
    public double        SpeedPlus    = 0;
    public double        LastPosition = 0;
    public boolean       still_turning_flag = false;

    public double
        DriveRatio  = 0,
        Drive_SP    = 0,
        Steer_SP    = 0,
        SteerRatio  = 0,
        minTurn     = 0,
        reverse     = 0,
        turnDir     = 0,
        turnMag     = 0,
        Power       = 0,
        SteerOffset = 0;

    public static double
        gF = 1023/20660,
        gP = 0.00,
        gI = 0.00,
        gD = 0.00;

    public Module ( String ModuleName, int ModuleNumber ) {

        //
        // DEFINITIONS
        //
            // REMEMBER VALUES
            this.ModuleName   = ModuleName;
            this.ModuleNumber = ModuleNumber;

            // ID'S FOLLOW A PATTERN BASED ON MODULE NUMBERS
            int DriveMotorID = ModuleNumber *2 -1;
            int SteerMotorID = ModuleNumber *2 -0;

        //
        // DRIVE MOTOR
        //
            // RESET TO DEFAULTS
            DriveMotor = new WPI_TalonFX ( DriveMotorID );
            DriveMotor.configFactoryDefault();
            DriveMotor.configNeutralDeadband( 0.0001 );
    
            // PID GAINS
            // kF: 1023  represents output value to Talon at 100%,
            //     20660 represents Velocity units at 100% output
            DriveMotor.config_kF( 0, gF );
            DriveMotor.config_kP( 0, gP );
            DriveMotor.config_kI( 0, gI );
            DriveMotor.config_kD( 0, gD );
            DriveMotor.selectProfileSlot( 0, 0 );

            // OTHER SETTINGS
            DriveMotor.setNeutralMode( NeutralMode.Brake );
            DriveMotor.setSensorPhase( false );
            DriveMotor.set( ControlMode.Velocity, 0 );
            DriveMotor.configSelectedFeedbackSensor( FeedbackDevice.IntegratedSensor );
            DriveMotor.configNominalOutputForward(  0 );
            DriveMotor.configNominalOutputReverse(  0 );
            DriveMotor.configPeakOutputForward   (  1 );
            DriveMotor.configPeakOutputReverse   ( -1 );

        //
        // STEER MOTOR
        //
            SteerMotor = new WPI_TalonFX ( SteerMotorID );
            SteerMotor.setNeutralMode( NeutralMode.Brake );

            // DEFINE STEER ENCODER
            SteerEncoder = new EncTalonFX ( ModuleNumber );
    }

    public void Display () {
        SmartDashboard.putNumber(ModuleName + " PV",  SteerEncoder.FalconEncoder.getAbsolutePosition() );

        // INDIVIDUAL WHEEL SPEEDS
        SmartDashboard.putNumber(ModuleName + " Vpv", DriveMotor.getSelectedSensorVelocity()           );
        
        // VELOCITY SET POINT FROM DASHBOARD
        double V = SmartDashboard.getNumber( "Vsp", Drive_SP ); if ( Drive_SP != V ) { Drive_SP = V; }

        // SET GAINS FROM DASHBOARD
        double F = SmartDashboard.getNumber( "F", gF ); if ( gF != F ) { gF = F; DriveMotor.config_kF( 0, gF ); }
        double P = SmartDashboard.getNumber( "P", gP ); if ( gP != P ) { gP = P; DriveMotor.config_kP( 0, gP ); }
        double I = SmartDashboard.getNumber( "I", gI ); if ( gI != I ) { gI = I; DriveMotor.config_kI( 0, gI ); }
        double D = SmartDashboard.getNumber( "D", gD ); if ( gD != D ) { gD = D; DriveMotor.config_kD( 0, gD ); }
    }

    public void Update ( SwerveModuleState state ) {

        //
        // DRIVE MOTOR
        //
            // CALCULATE DRIVE VALUES
            DriveRatio = Drive_SP;
            // DriveRatio = state.speedMetersPerSecond;

        //
        // STEER MOTOR
        //
            // CALCULATE TURN VALUES
            double Steer_SP = state.angle.getDegrees(); // Desired state (Final)
            double Steer_PV = GetDirection();           // Current state (Initial)
                Steer_PV = ( Steer_PV + 360 ) % 360;    // Ensure SP is between 0 and 360
                Steer_SP = ( Steer_SP + 360 ) % 360;    // Ensure SP is between 0 and 360

            // SMALLEST ANGLE TO SWIVEL: -180 to 180
            minTurn = ( Steer_PV - Steer_SP + 180 ) % 360 - 180;
                turnMag = Math.abs   ( minTurn );
                turnDir = Math.signum( minTurn );

            // MINIMIZE WHEEL SWIVEL: +120 becomes -60
            if ( turnMag > +90 ) {
                turnMag  = 180 - turnMag; // Turn smaller angle
                turnDir  = -1;            // and reverse swivel
                reverse  = -1;            // and reverse drive
            }

            // DETERMINE POWER USING PSEUDO PID CONTROLLER
            // double SteerRatio = turnMag / 200; 

        // INCREASE STEERE OFFSET IF NOT MOVING
        // double CurrentTurnSpeed = SteerMotor.getSelectedSensorVelocity();
        // if ( CurrentTurnSpeed == 0 & turnMag > 1 ) {
        //     SteerOffset += 0.0005;
        // }

        // ONE DEGREEE OFF IS GOOD ENOUGH
        // if ( turnMag < 5 ) {
            // SteerOffset = 0;
            // SteerRatio  = 0.10;
            // turnDir     = 1;
        // }

        // SET MOTOR CONTROLLERS
        double encFeedValue = ( DriveRatio / 600 ) * 2048;
        DriveMotor.set( ControlMode.Velocity, encFeedValue );

        // DriveMotor.set( ControlMode.PercentOutput, DriveRatio * reverse );
        // SteerMotor.set( ControlMode.PercentOutput, SteerRatio * turnDir );
    }



    public double GetDirection () {
        return SteerEncoder.FalconEncoder.getAbsolutePosition();
    }

    public void ResetDriveEncoder () {
        // DriveMotor
    }

    public void SetBrakeMode () {
        DriveMotor.setNeutralMode( NeutralMode.Brake );
    }

    public void SetCoastMode () {
        DriveMotor.setNeutralMode( NeutralMode.Coast );
    }

}
