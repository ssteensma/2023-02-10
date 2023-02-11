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
    double        SpeedPlus    = 0;
    double        LastPosition = 0;
    boolean       still_turning_flag = true;

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
        double DriveRatio = state.speedMetersPerSecond;
        double reverse    = 1;

        // CALCULATE TURN VALUES
        double SP = state.angle.getDegrees(); // Desired state (Final)
        double PV = GetDirection();           // Current state (Initial)
            SP = ( SP + 360 ) % 360;          // Ensure SP is between 0 and 360

        // SMALLEST ANGLE TO SWIVEL: -180 to 180
        double minTurn = ( PV - SP + 180 ) % 360 - 180;
            double turnMag = Math.abs   ( minTurn );
            double turnDir = Math.signum( minTurn );

        // MINIMIZE WHEEL SWIVEL: +120 becomes -60
        if ( turnMag > 90 ) {
            turnMag  = 180 - turnMag; // Turn smaller angle
            turnDir *= -1;            // and reverse swivel
            reverse *= -1;            // and reverse drive
        }

        // DETERMINE POWER USING PSEUDO PID CONTROLLER
        double SteerRatio = 0;
        if      ( turnMag > 20 ) { SteerRatio = 0.20; }
        else if ( turnMag > 10 ) { SteerRatio = 0.08; } 
        else if ( turnMag >  1 ) { SteerRatio = 0.07; } 
        else                     { SteerRatio = 0.00; }

        // If any the heading difference of any wheel is more than one degree and the
        // module has not turned in the last 20 ms then increase the turning speed.
        double  CurrentPosition = SteerEncoder.FalconEncoder.getAbsolutePosition();
        boolean is_moving = CurrentPosition == LastPosition ? false : true;
        if      ( turnMag < 1 ) { SpeedPlus  = 0.000; }
        else if ( ! is_moving ) { SpeedPlus += 0.001; }
        else                    {                     }

        // SET TURNING FLAG, CHECKED BY DRIVETRAIN
        still_turning_flag = turnMag >= 5 ? true : false;

        // RESET LAST POSITION TO SEE IF MOVEMENT OCCURED
        LastPosition = CurrentPosition;
        SteerRatio  += SpeedPlus;

        // SET MOTOR CONTROLLERS
        DriveMotor.setVoltage( DriveRatio * 10 * reverse );
        SteerMotor.setVoltage( SteerRatio * 10 * turnDir );
    }

}
