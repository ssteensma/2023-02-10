package frc.robot.Hardware;

public class Autopilot {

	public static double LastHeading = 0;

	public static double vx = 0;
	public static double vy = 0;
	public static double vt = 0;

//
// HeadingDiff is a simple method that calculates the angle difference
// between the current and desired heading. This can be used anywhere.
//
	public static double HeadingDiff ( double SP ) {

		// CALCULATE TURN VALUES
        double PV = Navigation.GetDirection(); // Current state (Initial)
            SP = ( SP + 360 ) % 360;           // Ensure SP is between 0 and 360
            double diff = -( SP - PV );        // Why is this negated? Should setInverted have been used?

        // SMALLEST ANGLE TO SWIVEL: -180 to 180
        double minTurn = ( diff + 180 ) % 360 - 180;
		return minTurn;
	}

//
// This is a simple method for driving somewhat straight without using
// a gyroscope. There may be situations where it is good enough.
//
	public static void DriveSortaStraight ( double Vx, double Vy ) {
		vx = Vx; vy = Vy; vt = 0;
	}

	// Consider turning this into a pseudo tank drive for purposes of 
	// driving in a straigh line using the gyroscope.
	public static void DriveStraight ( double Vx, double Vy ) {
		vx = Vx; vy = Vy; vt = 0;
	}

//
//
//
	public static void DriveNorth ( double Speed ) {
		vx = +Speed; vy = 0; vt = 0;
	}

	public static void DriveSouth ( double Speed ) {
		vx = -Speed; vy = 0; vt = 0;
	}

	public static void DriveWest ( double Speed ) {
		vx = 0; vy = +Speed; vt = 0;
	}

	public static void DriveEast ( double Speed ) {
		vx = Speed; vy = -Speed; vt = 0;
	}

//
//
//
	public static void DriveNorthWest ( double Speed ) {
		double radians = Math.toRadians( 45 );
		double speed   = Speed * Math.cos( radians );
		vx = +speed; vy = +speed; vt = 0;
	}

	public static void DriveNorthEast ( double Speed ) {
		double radians = Math.toRadians( 45 );
		double speed   = Speed * Math.cos( radians );
		vx = +speed; vy = -speed; vt = 0;
	}

	public static void DriveSouthWest ( double Speed ) {
		double radians = Math.toRadians( 45 );
		double speed   = Speed * Math.cos( radians );
		vx = -speed; vy = +speed; vt = 0;
	}

	public static void DriveSouthEast ( double Speed ) {
		double radians = Math.toRadians( 45 );
		double speed   = Speed * Math.cos( radians );
		vx = -speed; vy = -speed; vt = 0;
	}

//
// TurnToHeading sets the turn power variable in Autonomous mode to reach
// the desired heading using the shortest wheek swivel.	
//
	public static void TurnToHeading ( double NewHeading ) {
		double minTurn = HeadingDiff( NewHeading );
		double turnMag = Math.abs   ( minTurn );
		double turnDir = Math.signum( minTurn );

        // MINIMIZE WHEEL SWIVEL: +120 becomes -60
        if ( turnMag > 0 ) {
            turnMag  = 180 - turnMag; // Turn smaller angle
            turnDir *= -1;            // and reverse swivel
        }

        // DETERMINE POWER USING PSEUDO PID CONTROLLER
        if      ( turnMag > 20 ) { vt = 0.20; }
        else if ( turnMag > 10 ) { vt = 0.10; } 
        else if ( turnMag >  1 ) { vt = 0.08; } 
        else                     { vt = 0.00; }

		LastHeading = NewHeading;
		vx = 0; vy = 0; vt *= turnDir;
	}

//
// Stop sets the robot speed vector to zero. This is useful only in Autonomous
// mode. It should not be used elsewhere.
//
	public static void Stop () {
		vx = 0; vy = 0; vt = 0;
	}

//
// These methods rotate the robot at a constant counter-clockwise speed and
// clockwise speed respectively. This is only useful in Autonomous mode.
//
	public static void TurnLeftAtSpeed ( double Speed ) {
		vx = 0; vy = 0; vt = +Speed;
	}

	public static void TurnRightAtSpeed ( double Speed ) {
		vx = 0; vy = 0; vt = -Speed;
	}

	// public static void AdjustTurnSpeed( double Speed ) {
	// 	// double error = Speed - Navigation.GetTurnSpeed();
	// 	// LastPowerT  += error * 0.0001;
	// }

	// public static void DriveStraight ( double Speed, double Heading ) {
		
	// }
    
}
