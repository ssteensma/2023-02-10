package frc.robot.Hardware;

import frc.robot.Elevator.Arm;
import frc.robot.Elevator.Lift;
import frc.robot.Elevator.Roller;

public class Path {

    public static void Track_00 () {
        switch ( Stage.StageNumber ) {
            case 0:
                Stage.WaitForDuration( 1.00 );
                break;

            default:
                Stage.Last();
                break;
        }
    }

    public static void Track_01 () {
        switch ( Stage.StageNumber ) {
            case 0:
                Autopilot.Stop();
                Arm.SetHI();
                Lift.SetHigh();
                Stage.WaitForDuration( 2 );
                break;

            case 1:
                Arm.SetLO();
                Stage.WaitForDuration( 2 );
                break;

            case 2:
                Roller.Spit();
                Stage. WaitForDuration( 2 );
                break;

            case 3:
                Roller.Stop();
                Stage.WaitForDuration( 1 );
                break;

            case 4:
                Arm.SetHI();
                Stage.WaitForDuration( 2 );
                break;

            case 5:
                Lift.SetLow();
                Stage.WaitForDuration( 2 );
                break;

            default:
                Stage.Last();
                break;
        }
    }
        
    public static void Track_02 () {
        switch ( Stage.StageNumber ) {
            case 0:
                Stage.WaitForDuration( 2.00 );
                break;
                
            default:
                Stage.Last();
                break;
        }
    }
    
    public static void Track_03 () {
        switch ( Stage.StageNumber ) {
            case 0:
                Autopilot.DriveNorth( 0.20 );
                Stage.WaitForDistance( 20 * 100 );
                break;

            case 1:
                Autopilot.TurnToHeading( 0 );
                Stage.WaitForDuration( 3.00 );
                break;

            // case 2:
            //     Autopilot.DriveSouth( 0.20 );
            //     Stage.WaitForDistance( 136 );
            //     break;

            default:
                Stage.Last();
                break;
        }
    }
    
    public static void Track_04 () {
        switch ( Stage.StageNumber ) {
            case 0:
                Autopilot.TurnToHeading( 90 );;
                Stage.WaitForDuration( 2.00 );
                break;

            case 1:
                Autopilot.TurnToHeading( 180 );;
                Stage.WaitForDuration( 2.00 );
                break;

            default:
                Stage.Last();
                break;
        }
    }

    public static void Track_05 () {
        switch ( Stage.StageNumber ) {
            case 0:
                Stage.WaitForDuration( 1.00 );
                break;

            default:
                Stage.Last();
                break;
        }
    }    

    public static void Track_06 () {
        switch ( Stage.StageNumber ) {
            case 0:
                Stage.WaitForDuration( 1.00 );
                break;

            default:
                Stage.Last();
                break;
        }
    }    

}
