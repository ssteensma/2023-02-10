package frc.robot.Elevator;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Hardware.Settings;

public class Lift {

    public static VictorSPX liftMotor;
    public static double    SP    = 0;
    public static double    power = 0;

    // CONSIDER: Since the encoder is relative, it must always start
    // in the same position or have a method to reset it when at a 
    // particular location.

    public static double
        LO =     0,
        HI = 25000;

    // 

//
//
//
    public static void Initialize () {
        liftMotor = new VictorSPX( Settings.Lift_CANID );
    }

    public static void Periodic () {

        // READ FROM SENSOR
        // double PV = GetPosition();

        // CALCULATE DISPLACEMENT AND PSEUDO VELOCITY
        // double displacement = SP  - PV;

        // TOLERANCES
        // double disTolerance  =  5; // Tolerance to consider at height
        // double velTolerance  =  3; // Tolerance to consider not moving

        // boolean at_position = Math.abs( displacement ) < disTolerance ? true : false;
        // boolean at_speed    = Math.abs( velocity     ) < velTolerance ? true : false;

        //
        // SITUATIONS
        //
        //     // TURN MOTOR OFF IF POSSIBLE
        //     if ( target_position == 0 & current_position == 0 ) { power = 0; }

        //     // AT POSITION
        //     else if ( at_position      & at_speed       ) {                   } // On target
        //     else if ( at_position      & velocity <   0 ) { increase_power(); } // Drifting down
        //     else if ( at_position      & velocity >   0 ) { decrease_power(); } // Drifting up

        //     // TOO LOW
        //     else if ( displacement < 0 & velocity ==  0 ) { increase_power(); } // Not moving
        //     else if ( displacement < 0 & velocity <   0 ) { increase_power(); } // Wrong direction
        //     else if ( displacement < 0 & velocity <   5 ) { increase_power(); } // Up too slow
        //     else if ( displacement < 0 & velocity >  10 ) { decrease_power(); } // Up too fast
        //     else if ( displacement < 0                  ) {                   } // Just right

        //     // TOO HIGH
        //     else if ( displacement > 0 & velocity ==  0 ) { decrease_power(); } // Not moving
        //     else if ( displacement > 0 & velocity >   0 ) { decrease_power(); } // Wrong direction
        //     else if ( displacement > 0 & velocity >  -5 ) { decrease_power(); } // Down too slow
        //     else if ( displacement > 0 & velocity < -10 ) { increase_power(); } // Down too fast
        //     else if ( displacement > 0                  ) {                   } // Just right

        // // ENSURE POWER IS SAFE FOR TESTING
        // if ( power < -0.30 ) { power = -0.30; }
        // if ( power >  0.30 ) { power =  0.30; }

        // SAFETY WHILE TESTING
        // power = 0;

        liftMotor.set( VictorSPXControlMode.PercentOutput, power );
    }

//
//
//
    public static void increase_power () { power += 0.001; }
    public static void decrease_power () { power -= 0.001; }

    public static void Display () {
        SmartDashboard.putNumber("Elevator-Lift Pos", GetPosition() );
        SmartDashboard.putNumber("Elevator-Lift Tar", SP            );
    }

    public static void Stop () {
        power = 0;
    }

    public static void GoUp () {
        power = 1.00;
    }

    public static void GoDown () {
        power = -0.50;
    }

//
// Not sure if an encoder will be part of the Lift Mechanism.
// If not, this will need to be done using limit switches or
// some other creative method.
//
    public static double GetPosition () {
        return 0;
    }

    public static void SetPosition ( double pos ) {
        SP = pos;
    }

    public static void Reset () {
        SetPosition( 0 );
    }

//
// This intent of these methods is to have presets of where
// the lift mechanism is intended to stop. Hopefully there will
// be an encoder as part of the mechanism.
//
    public static void SetHigh () {
        SP = 100;
    }

    public static void SetMedium () {
        SP = 50;
    }

    public static void SetLow () {
        SP = 0;
    }


}
