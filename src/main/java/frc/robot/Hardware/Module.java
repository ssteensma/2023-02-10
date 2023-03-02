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
    public double        TurnDiff     = 0;
    public double        SpeedPlus    = 0;
    public double        LastPosition = 0;
    public boolean       still_turning_flag = false;

    public double        DriveRatio = 0;
    public double        SteerRatio = 0;
    public double        minTurn    = 0;
    public double        reverse    = 0;
    public double        turnDir    = 0;
    public double        turnMag    = 0;
    public double        Power      = 0;

    public double        SteerOffset = 0;

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
        reverse    = 1;

        // CALCULATE TURN VALUES
        double SP = state.angle.getDegrees(); // Desired state (Final)
        double PV = GetDirection();           // Current state (Initial)
            PV = ( PV + 360 ) % 360;    // Ensure SP is between 0 and 360
            SP = ( SP + 360 ) % 360;    // Ensure SP is between 0 and 360

        // SMALLEST ANGLE TO SWIVEL: -180 to 180
        minTurn = ( PV - SP + 180 ) % 360 - 180;
            turnMag = Math.abs   ( minTurn );
            turnDir = Math.signum( minTurn );

            // MINIMIZE WHEEL SWIVEL: +120 becomes -60
            if ( turnMag > +90 ) {
                turnMag  = 180 - turnMag; // Turn smaller angle
                turnDir  = -1;            // and reverse swivel
                reverse  = -1;            // and reverse drive
            }

        // DETERMINE POWER USING PSEUDO PID CONTROLLER
        double SteerRatio = turnMag / 200; 

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
        DriveMotor.set( ControlMode.PercentOutput, DriveRatio * reverse );
        SteerMotor.set( ControlMode.PercentOutput, SteerRatio * turnDir );
    }

}
