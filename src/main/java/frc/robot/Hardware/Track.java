package frc.robot.Hardware;

public class Track {

    public static void Track_00 () {
        switch ( Stage.Number ) {
            case 0:
                Autopilot.DriveNorth( 0.12 );
                Stage.WaitForDistance( 200 );
                break;

            default:
                Stage.Last();
                break;
        }
    }
    
    public static void Track_01 () {
        switch ( Stage.Number ) {
            case 0:
                Stage.WaitForDuration( 1.00 );
                break;

            default:
                Stage.Last();
                break;
        }
    }
    
    public static void Track_02 () {
        switch ( Stage.Number ) {
            case 0:
                Stage.WaitForDuration( 2.00 );
                break;
                
            default:
                Stage.Last();
                break;
        }
    }
    
    public static void Track_03 () {
        switch ( Stage.Number ) {
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
        switch ( Stage.Number ) {
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
        switch ( Stage.Number ) {
            case 0:
                Stage.WaitForDuration( 1.00 );
                break;

            default:
                Stage.Last();
                break;
        }
    }    

    public static void Track_06 () {
        switch ( Stage.Number ) {
            case 0:
                Stage.WaitForDuration( 1.00 );
                break;

            default:
                Stage.Last();
                break;
        }
    }    

}
