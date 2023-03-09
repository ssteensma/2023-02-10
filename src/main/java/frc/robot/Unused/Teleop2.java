package frc.robot.Unused;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Hardware.Settings;
import frc.robot.Hardware.Swerve;

public class Teleop2 {

    public static Joystick       DriveStick;
    public static XboxController ManipStick;

    public static double
        Xratio,
        Yratio,
        Tratio;

    public static int
        loops = 0;

    public static int
        Timeout = 30;

    static StringBuilder
        SB = new StringBuilder();    

    public static void Initialize () {
        DriveStick = new Joystick      ( Settings.DriveStickID );
        ManipStick = new XboxController( Settings.ManipStickID );

        // SHORT CUTS
        WPI_TalonFX mFL = Swerve.FL_module.DriveMotor;

        mFL.configFactoryDefault();
        mFL.configNeutralDeadband( 0.0001 );
        
        mFL.configSelectedFeedbackSensor(
            TalonFXFeedbackDevice.IntegratedSensor,
            0,  // kPIDLoopIdx
            30  // Timeout
        );

        mFL.configNominalOutputForward(  0, Timeout );
        mFL.configNominalOutputReverse(  0, Timeout );
        mFL.configPeakOutputForward   (  1, Timeout );
        mFL.configPeakOutputReverse   ( -1, Timeout );

        // kF: 1023  represents output value to Talon at 100%,
        //     20660 represents Velocity units at 100% output
        mFL.config_kF( 0, 1023/20660, Timeout );
        mFL.config_kP( 0, 0.100, Timeout );
        mFL.config_kI( 0, 0.000, Timeout );
        mFL.config_kD( 0, 0.000, Timeout );
        // mFL.config_kP( 0, 0.100, Timeout );
        // mFL.config_kI( 0, 0.001, Timeout );
        // mFL.config_kD( 0, 5.000, Timeout );

        // SENSOR PHASE is not needed for TalonFX integrated sensor
        // mFL.setSensorPhase( true );
    }

    public static void Periodic () {
        Xratio = -DriveStick.getY();
        Yratio = -DriveStick.getX();
        Tratio = -DriveStick.getTwist();

        double[] DAxs = { Xratio, Yratio, Tratio }; 

        // SHORT CUTS
        Joystick    DS  = DriveStick;
        WPI_TalonFX mFL = Swerve.FL_module.DriveMotor;

        boolean[] DBtn = new boolean[ 8 ]; 
            for ( int i=1; i<8; ++i ) { DBtn[i] = DS.getRawButton(i); }

        SB.append( "\tOut: " );
        SB.append( (int) (mFL.getMotorOutputPercent() * 100) );
        SB.append( "%" );

        SB.append( "\tSpd: " );
		SB.append( mFL.getSelectedSensorVelocity( 0 ) );
		SB.append( "u" );

        // CLOSED LOOP VELOCITY
        /**
         * Convert 2000 RPM to units / 100ms.
         * 2048 Units/Rev * 2000 RPM / 600 100ms/min in either direction:
         * velocity setpoint is in units/100ms
         */
        if ( DBtn[1] ) {
            double Velocity_SP = -DS.getX() * 2000.0 * 2048.0 / 600.0;
            mFL.set( ControlMode.Velocity, Velocity_SP );

            /* Append more signals to print when in speed mode. */
            SB.append( "\tErr:" );
            SB.append( mFL.getClosedLoopError( 0 ) );
            SB.append( "\tTrg:" );
            SB.append( Velocity_SP );
        } else {
            mFL.set( ControlMode.PercentOutput, 0.5 );
        }

        if ( loops >= 10 ) {
            loops = 0;
            System.out.println( SB.toString() );
        }
        SB.setLength( 0 );

        // UPDATE MECHANISMS
        // Driver   .Periodic();
        // Elevator .Periodic();
    }

    public static void Display () {
    }
}