package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Module {

    EncTalonFX    SteerEncoder;
    int           ModuleNumber;
    String        ModuleName;
    WPI_TalonFX   DriveMotor;
    WPI_TalonFX   SteerMotor;
    PIDController SteerPID;
    boolean       turning_flag = true;
    double        SpeedPlus    = 0;
    double        LastPosition = 0;

    public Module ( String ModuleName, int ModuleNumber ) {

        // PIDController PID = new PIDController( 0.01, 0.00, 0.00 );

        // REMEMBER VALUES
        this.ModuleName   = ModuleName;
        this.ModuleNumber = ModuleNumber;

        // ID'S FOLLOW A PATTERN BASED ON MODULE NUMBERS
        int DriveMotorID = ModuleNumber *2 -1;
        int SteerMotorID = ModuleNumber *2 -0;

        // CONFIGURE MOTOR THROUGH SOFTWARE
        // TalonFXConfiguration config = new TalonFXConfiguration();
        // config.supplyCurrLimit.enable                  = false;
        // config.supplyCurrLimit.currentLimit            = 30.0;
        // config.supplyCurrLimit.triggerThresholdCurrent = 30.0;
        // config.supplyCurrLimit.triggerThresholdTime    = 1.5;

        // DEFINE AND CONFIGURE DRIVE MOTOR
        DriveMotor = new WPI_TalonFX ( DriveMotorID );
        DriveMotor.setNeutralMode( NeutralMode.Brake );
        // driveMotor.configAllSettings ( config );

        // DEFINE AND CONFIGURE STEER MOTOR
        SteerMotor = new WPI_TalonFX ( SteerMotorID );
        SteerMotor.setNeutralMode( NeutralMode.Brake );
        // steerMotor.configAllSettings ( config );

        // DEFINE STEER ENCODER
        SteerEncoder = new EncTalonFX ( ModuleNumber );
        // SteerEncoder.FalconEncoder.setPosition( 0 );

        // PID CONTROLLER
        // SteerPID = new PIDController( 0.0001, 0, 0.0001 );
        // SteerPID.enableContinuousInput( 0, 360 );
        // SteerPID.setIntegratorRange( -0.5, 0 );
        // SteerPID.setTolerance( 1, 0 );
    }

    public void Display () {

        // DRIVE MOTOR
        // double DrivePercent = DriveMotor.getMotorOutputPercent();
        // SmartDashboard.putNumber( ModuleName + "_Drive Percent", DrivePercent );

        // STEER MOTOR
        // double SteerPercent = SteerMotor.getMotorOutputPercent();
        // SmartDashboard.putNumber( ModuleName + "_Steer Percent", SteerPercent );

        // STEER ENCODER
        SmartDashboard.putNumber(ModuleName + " PV", this.SteerEncoder.FalconEncoder.getAbsolutePosition() );
        // SmartDashboard.putNumber(ModuleName + " DIFF", diff );
        // SmartDashboard.putNumber(ModuleName + " SMAL", s );
    }

    public void ResetDriveEncoder () {
        // DriveMotor
    }

    public double GetDirection () {
        return SteerEncoder.FalconEncoder.getAbsolutePosition();
    }

    public void Update ( SwerveModuleState state ) {

        // CALCULATE DRIVE VALUES
        double DriveRatio = state.speedMetersPerSecond;
        double reverse    = 1;

        // CALCULATE TURN VALUES
        double SP = state.angle.getDegrees(); // Desired state (Final)
        double PV = GetDirection();           // Current state (Initial)
            SP = ( SP + 360 ) % 360;          // Ensure SP is between 0 and 360

        // SMALLEST ANGLE TO SWIVEL: -180 to 180
        double minTurn = -( SP - PV );       // Why is this negated? Should setInverted have been used?
            double turnMag = Math.abs   ( minTurn );
            double turnDir = Math.signum( minTurn );

        // MINIMIZE WHEEL SWIVEL: +120 becomes -60
        if ( turnMag > 90 ) {
            turnMag  = 180 - turnMag; // Turn smaller angle
            turnDir *= -1;            // and reverse swivel
            reverse *= -1;            // and reverse drive.
        }

        // DETERMINE POWER USING PSEUDO PID CONTROLLER
        double SteerRatio = 0;
        if      ( turnMag > 20 ) { SteerRatio = 0.08; }
        else if ( turnMag > 10 ) { SteerRatio = 0.08; } 
        else if ( turnMag >  1 ) { SteerRatio = 0.08; } 
        else                     { SteerRatio = 0.00; }

        double CurrentPosition = SteerEncoder.FalconEncoder.getAbsolutePosition();
        if ( turnMag > 1 & CurrentPosition == LastPosition ) {
            SpeedPlus += 0.001;
        }

        if ( turnMag <= 3 ) {
            turning_flag = false;
        } else {
            turning_flag = true;
        }

        LastPosition = CurrentPosition;
        SteerRatio  += SpeedPlus;
        SmartDashboard.putNumber( ModuleName + "-SpeedPlus", SpeedPlus );
        SmartDashboard.putNumber( ModuleName + "-Turn Mag", turnMag );

        // SET MOTOR CONTROLLERS
        DriveMotor.setVoltage( DriveRatio * 10 * reverse );
        SteerMotor.setVoltage( SteerRatio * 10 * turnDir );
    }

}
