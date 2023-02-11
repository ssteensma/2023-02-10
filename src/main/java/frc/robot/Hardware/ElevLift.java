package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevLift {

    public static double    last_position   = 0;
    public static double    target_position = 0;
    public static VictorSPX liftMotor;
    public static double    power = 0;

    public static void Initialize () {
        liftMotor = new VictorSPX( 20 );
    }

    public static void Periodic () {

        // READ FROM SENSOR
        double current_position = GetPosition();

        // CALCULATE DISPLACEMENT AND PSEUDO VELOCITY
        double displacement = target_position  - current_position;
        double velocity     = current_position - last_position;

        // TOLERANCES
        double disTolerance  =  5; // Tolerance to consider at height
        double velTolerance  =  3; // Tolerance to consider not moving

        boolean at_position = Math.abs( displacement ) < disTolerance ? true : false;
        boolean at_speed    = Math.abs( velocity     ) < velTolerance ? true : false;

        //
        // SITUATIONS
        //
            // TURN MOTOR OFF IF POSSIBLE
            if ( target_position == 0 & current_position == 0 ) { power = 0; }

            // AT POSITION
            else if ( at_position      & at_speed       ) {                   } // On target
            else if ( at_position      & velocity <   0 ) { increase_power(); } // Drifting down
            else if ( at_position      & velocity >   0 ) { decrease_power(); } // Drifting up

            // TOO LOW
            else if ( displacement < 0 & velocity ==  0 ) { increase_power(); } // Not moving
            else if ( displacement < 0 & velocity <   0 ) { increase_power(); } // Wrong direction
            else if ( displacement < 0 & velocity <   5 ) { increase_power(); } // Up too slow
            else if ( displacement < 0 & velocity >  10 ) { decrease_power(); } // Up too fast
            else if ( displacement < 0                  ) {                   } // Just right

            // TOO HIGH
            else if ( displacement > 0 & velocity ==  0 ) { decrease_power(); } // Not moving
            else if ( displacement > 0 & velocity >   0 ) { decrease_power(); } // Wrong direction
            else if ( displacement > 0 & velocity >  -5 ) { decrease_power(); } // Down too slow
            else if ( displacement > 0 & velocity < -10 ) { increase_power(); } // Down too fast
            else if ( displacement > 0                  ) {                   } // Just right

        // ENSURE POWER IS SAFE FOR TESTING
        if ( power < -0.30 ) { power = -0.30; }
        if ( power >  0.30 ) { power =  0.30; }

        // SAFETY WHILE TESTING
        power = 0;

        liftMotor.set( VictorSPXControlMode.PercentOutput, power );
    }

    public static void increase_power () { power += 0.001; }
    public static void decrease_power () { power -= 0.001; }

    public static void Display () {
        SmartDashboard.putNumber("Elevator-Lift Pos", GetPosition()   );
        SmartDashboard.putNumber("Elevator-Lift Tar", target_position );
    }

//
//
//
    public static double GetPosition () {
        return 0;
    }

    public static void SetPosition ( double pos ) {
        target_position = pos;
    }

    public static void Reset () {
        SetPosition( 0 );
    }

//
//
//
    public static void SetHigh () {
        target_position = 100;
    }

    public static void SetMedium () {
        target_position = 50;
    }

    public static void SetLow () {
        target_position = 0;
    }


}
