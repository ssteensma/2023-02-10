package frc.robot.Hardware;

import frc.robot.Elevator.Arm;
import frc.robot.Elevator.Lift;
import frc.robot.Elevator.Roller;

public class Path {

    static double counter = 0;

    static double SlowSpeed         = 0.10;
    static double GoalDistance      = 24.0;
    static double CommunityDistance = 18*12 + 8;

    static double FastSpeed     = 0.25;
    static double BalanceSpeed  = 0.15;

    static double InclineAngle  = 10.0;
    static double BalanceAngle  = 3.0;

    static double ArmTolerance  = 1.0;
    static double LiftTolerance = 1.0;

    static double DroolDuration = 1.0;

/*  Do absolutely nothing. This is used as a safety in case
    things go horribly wrong and Auton modes do not execute
    as intended.
*/

    public static void Nothing () {
        switch ( Stage.StageNumber ) {
            default:
                Stage.Last();
                break;
        }
    }

/*
    The "Balance" path is used when the elevator assembly
    is no longer working and we become a defense only robot.
*/

    public static void Balance () {
        switch ( Stage.StageNumber ) {
            case 0:
                Autopilot.DriveSouth( FastSpeed );
                Stage.WaitForIncline( InclineAngle );
                break;

            case 1:
                Autopilot.DriveSouth( BalanceSpeed );
                Stage.WaitForBalance( BalanceAngle );
                break;

            default:
                Stage.Last();
                break;
        }
    }

/*  The "Any" paths are used to simply place a cone or cube
    without balancing. Useful for when another team balances
    and we need to stay out of the way.
*/

    public static void Any_1st () {
        switch ( Stage.StageNumber ) {
            case 0:
                Autopilot.DriveNorth( SlowSpeed );
                Stage.WaitForDistance( GoalDistance );
                break;

            case 1:
                Autopilot.DriveSouth( FastSpeed );
                Stage.WaitForDistance( CommunityDistance );
                break;

            default:
                Stage.Last();
                break;
        }
    }

    public static void Any_2nd () {
        switch ( Stage.StageNumber ) {
            case 0:
                Arm.SetLO();
                Lift.SetHI();
                Stage.WaitForArmLift( ArmTolerance, LiftTolerance );
                break;

            case 1:
                Roller.Drool();
                Stage.WaitForDuration( DroolDuration );
                break;

            case 2:
                Arm.SetHI();
                Roller.Stop();
                Stage.WaitForArm( 1 );
                break;

            case 3:
                Lift.SetLO();
                Stage.WaitForLift( 1 );
                break;

            case 4:
                Autopilot.DriveSouth( FastSpeed );
                Stage.WaitForDistance( CommunityDistance );
                break;

            default:
                Stage.Last();
                break;
        }
    }

/*
    The "Center" paths are used when we start in the center of the 
    field and desire to balance.
*/
    public static void Ctr_1st () {
        switch ( Stage.StageNumber ) {
            case 0:
                Autopilot.DriveNorth( SlowSpeed );
                Stage.WaitForDistance( GoalDistance );
                break;

            case 1:
                Autopilot.DriveSouth( FastSpeed );
                Stage.WaitForIncline( InclineAngle );
                break;

            case 2:
                Autopilot.DriveSouth( BalanceSpeed );
                Stage.WaitForBalance( BalanceAngle );
                break;

            default:
                Stage.Last();
                break;
        }
    }

    public static void Ctr_2nd () {
        switch ( Stage.StageNumber ) {
            case 0:
                Elevator.Preset3();
                Stage.WaitForArmLift( ArmTolerance, LiftTolerance );
                break;

            case 1:
                Roller.Drool();
                Stage.WaitForDuration( DroolDuration );
                break;

            case 3:
                Elevator.Preset1();
                Stage.WaitForArmLift( ArmTolerance, LiftTolerance );
                break;

            case 4:
                Autopilot.DriveSouth( FastSpeed );
                Stage.WaitForIncline( InclineAngle );
                break;

            case 5:
                Autopilot.DriveSouth( BalanceSpeed );
                Stage.WaitForBalance( BalanceAngle );
                break;

            default:
                Stage.Last();
                break;
        }
    }
    
    public static void Lft_1st () {
        switch ( Stage.StageNumber ) {
            case 0:
                Autopilot.DriveNorth( SlowSpeed );
                Stage.WaitForDistance( GoalDistance );
                break;

            case 1:
                // Twist robot to put block fully across line.

            case 2: 
                Elevator.Preset1();
                Stage.WaitForArmLift(ArmTolerance, LiftTolerance);
                break;

            case 3:
                Autopilot.DriveSouth( FastSpeed );
                Stage.WaitForDistance( CommunityDistance ); // 18 ft, 4 in
                break;

            default:
                Stage.Last();
                break;
        }
    }

    public static void Lft_2nd () {
        switch ( Stage.StageNumber ) {
            case 0:
                Elevator.Preset3();
                Stage.WaitForArmLift( ArmTolerance, LiftTolerance );
                break;

            case 1:
                Roller.Drool();
                Stage.WaitForDuration( DroolDuration );
                break;

            case 2:
                Elevator.Preset1();
                Stage.WaitForArmLift( ArmTolerance, LiftTolerance );
                break;

            case 3:
                Autopilot.DriveSouth( FastSpeed );
                Stage.WaitForDistance( CommunityDistance );
                break;

            default:
                Stage.Last();
                break;
        }
    }    

/*
    The "Right" paths are exactly the same as the left
    paths. Manipulate the item and then move away a value
    of CommunityDistance (last set to be 18 feet, 8 inches).
*/

    public static void Rgt_1st () {
        Lft_1st();
    }    

    public static void Rgt_2nd () {
        Lft_2nd();
   }    

}
