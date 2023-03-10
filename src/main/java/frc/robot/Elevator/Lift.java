package frc.robot.Elevator;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Hardware.Settings;

public class Lift {

    public static VictorSPX 
        liftMotorL,
        liftMotorR;

    public static double     SP;
    public static double     power = 0;

    public static Ultrasonic Sonar;

    public static double
        HI = 27.0,
        MD = 10.0,
        LO = 4.5;

    public static double
        kP =  0.2;

    public static double
        direction,
        displacement,
        ratio,
        tolerance;

//
//
//
    public static void Initialize () {
        liftMotorL = new VictorSPX( Settings.LiftL_CANID );
        liftMotorR = new VictorSPX( Settings.LiftR_CANID );

        Sonar = new Ultrasonic(
            Settings.LiftSonar_DIO[0], // Input
            Settings.LiftSonar_DIO[1]  // Output
        );

        Ultrasonic.setAutomaticMode( true );

        SP = LO;
    }

    public static void Periodic () {

        // READ FROM SENSOR
        double PV = GetPosition();

        // CALCULATE DISPLACEMENT AND PSEUDO VELOCITY
        displacement = SP - PV;    // Displacement in inches
        ratio = displacement / 28;

        direction    = Math.signum( displacement ); // +1 for up; -1 for down
        // ratio        = displacement / ( HI - LO );  // Displacement ratio ( 0 to 1 )

        // SIMPLE PID CONTROLLER BASED RATIO
        power = direction * 0.80;

        if ( Math.abs( displacement ) < 2 ) { power = 0; }

        // SET MOTOR POWER
        liftMotorL.set( VictorSPXControlMode.PercentOutput, power );
        liftMotorR.set( VictorSPXControlMode.PercentOutput, power );
    }

//
//
//
    // public static void increase_power () { power += 0.001; }
    // public static void decrease_power () { power -= 0.001; }

    public static void Display () {
        SmartDashboard.putNumber("Lift PV", GetPosition() );
        SmartDashboard.putNumber("Lift SP", SP            );
        SmartDashboard.putNumber("Lift Dir", direction    );
        SmartDashboard.putNumber("Lift Pow", power        );
    }

//
// 
//
    public static double GetPosition () {
        return Sonar.getRangeInches();
    }

//
// This intent of these methods is to have presets of where
// the lift mechanism is intended to stop.
//
    public static void SetPosition ( double pos ) {
        SP = pos;
    }

    public static void Reset () {
        SetLO();
    }

    public static void SetHI () {
        SP = HI;
    }

    public static void SetMD () {
        SP = MD;
    }

    public static void SetLO () {
        SP = LO;
    }

}
