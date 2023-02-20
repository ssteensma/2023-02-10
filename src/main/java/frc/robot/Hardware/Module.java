package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Module {

    public EncTalonFX    SteerEncoder;
    public int           ModuleNumber;
    public String        ModuleName;
    public WPI_TalonFX   DriveMotor;
    public WPI_TalonFX   SteerMotor;
    public PIDController SteerPID;
    public double        SpeedPlus    = 0;
    public double        LastPosition = 0;
    public boolean       still_turning_flag = false;

    public double        DriveRatio = 0;
    public double        SteerRatio = 0;
    public double        minTurn    = 0;
    public double        reverse    = 0;
    public double        turnDir    = 0;
    public double        turnMag    = 0;

    public Module ( String ModuleName, int ModuleNumber ) {

        // REMEMBER VALUES
        this.ModuleName   = ModuleName;
        this.ModuleNumber = ModuleNumber;

        // ID'S FOLLOW A PATTERN BASED ON MODULE NUMBERS
        int DriveMotorID = ModuleNumber *2 -1;
        int SteerMotorID = ModuleNumber *2 -0;

        // DEFINE AND CONFIGURE DRIVE MOTOR
        DriveMotor = new WPI_TalonFX ( DriveMotorID );
        DriveMotor.setNeutralMode( NeutralMode.Brake );

        // DEFINE AND CONFIGURE STEER MOTOR
        SteerMotor = new WPI_TalonFX ( SteerMotorID );
        SteerMotor.setNeutralMode( NeutralMode.Brake );

        // DEFINE STEER ENCODER
        SteerEncoder = new EncTalonFX ( ModuleNumber );

        if ( ModuleNumber == 1 ) {
            // SteerMotor.configSelectedFeedbackSensor(
            //     FeedbackDevice., 1, 50 
            // );

            SteerMotor.setSensorPhase( true ); // Invert if phase is incorret
        
            SteerMotor.configNominalOutputForward( 0, 30 ); // These are percent values
            SteerMotor.configNominalOutputReverse( 0, 30 );
            SteerMotor.configPeakOutputForward(  1, 30 );
            SteerMotor.configPeakOutputReverse( -1, 30 );

            SteerMotor.selectProfileSlot( 0, 0 );
            SteerMotor.config_kF( 0, 0.00, 30 );
            SteerMotor.config_kP( 0, 0.10, 30 );
            SteerMotor.config_kI( 0, 0.00, 30 );
            SteerMotor.config_kD( 0, 0.00, 30 );

            SteerMotor.configMotionCruiseVelocity( 0.10, 30 );
            SteerMotor.configMotionAcceleration  ( 0.10, 30 );
        }

        // Set deadband to 0.1%. Default is 4%
        SteerMotor.configNeutralDeadband( 0.001, 30 );

    }

    public void Display () {
        // STEER ENCODER
        SmartDashboard.putNumber(ModuleName + " PV", this.SteerEncoder.FalconEncoder.getAbsolutePosition() );
    }

    public void ResetDriveEncoder () {
        // DriveMotor
    }

    public double GetDirection () {
        return SteerEncoder.FalconEncoder.getAbsolutePosition();
    }

    public void Update ( SwerveModuleState state ) {

        // CALCULATE DRIVE VALUES
        DriveRatio = state.speedMetersPerSecond;
        reverse = 1;

        // CALCULATE TURN VALUES
        double SP = state.angle.getDegrees(); // Desired state (Final)
        double PV = GetDirection();           // Current state (Initial)
            PV = ( PV % 360  + 360 ) % 360;          // Ensure SP is between 0 and 360
            SP = ( SP % 360 + 360 ) % 360;          // Ensure SP is between 0 and 360

        // SMALLEST ANGLE TO SWIVEL: -180 to 180
        minTurn = ( PV - SP + 180 ) % 360 - 180;
            turnMag = Math.abs   ( minTurn );
            turnDir = Math.signum( minTurn );

        // MINIMIZE WHEEL SWIVEL: +120 becomes -60
        if ( turnMag > 90 ) {
            turnMag  = 180 - turnMag; // Turn smaller angle
            turnDir *= -1;            // and reverse swivel
            reverse *= -1;            // and reverse drive
        }

        // DETERMINE POWER USING PSEUDO PID CONTROLLER
        if      ( turnMag > 20 ) { SteerRatio = 0.20; }
        else if ( turnMag > 10 ) { SteerRatio = 0.15; }
        else if ( turnMag >  1 ) { SteerRatio = 0.08; }
        else                     { SteerRatio = 0.00; }

        // If any the heading difference of any wheel is more than one degree and the
        // module has not turned in the last 20 ms then increase the turning speed.
        // double  CurrentPosition = SteerEncoder.FalconEncoder.getAbsolutePosition();
        // boolean is_moving = CurrentPosition == LastPosition ? false : true;
        // if      ( turnMag < 1 ) { SpeedPlus  = 0.000; }
        // else if ( ! is_moving ) { SpeedPlus += 0.001; }
        // else                    {                     }

        // SET TURNING FLAG, CHECKED BY DRIVETRAIN
        // still_turning_flag = turnMag >= 5 ? true : false;

        // RESET LAST POSITION TO SEE IF MOVEMENT OCCURED
        // LastPosition = CurrentPosition;
        // SteerRatio  += SpeedPlus;

        // SET MOTOR CONTROLLERS
        DriveMotor.set( ControlMode.Velocity, 30 );
        SteerMotor.set( ControlMode.Position, 30 );

        // DriveMotor.setVoltage( DriveRatio * 10 * reverse );
        // SteerMotor.setVoltage( SteerRatio * 10 * turnDir );
    }

}
