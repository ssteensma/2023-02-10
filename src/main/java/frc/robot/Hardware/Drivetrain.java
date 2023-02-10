package frc.robot.Hardware;

public class Drivetrain {
    
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

}
