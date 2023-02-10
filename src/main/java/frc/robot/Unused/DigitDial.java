package frc.robot.Unused;

public class DigitDial {
    public static final String autonDoNothing     = "Pout";
	public static final String autonLeftStation   = "Left Station";
	public static final String autonCenterStation = "Center Station";
	public static final String autonRightStation  = "Right Station";
	public static final String autonCrossBaseline = "Cross Baseline";
	public static final String autonTesting       = "Testing";

	public static String autonSelected = autonTesting;


	public static void GetSelected() {

		double voltage = 6 * DigitBoard.getInstance().getPotentiometer();

		if      ( voltage < 1 ) { autonSelected = autonDoNothing;     }

		else if ( voltage < 2 ) { autonSelected = autonLeftStation;   }

		else if ( voltage < 3 ) { autonSelected = autonCenterStation; }

		else if ( voltage < 4 ) { autonSelected = autonRightStation;  }

		else if ( voltage < 5 ) { autonSelected = autonCrossBaseline; }

		else                    { autonSelected = autonTesting;       }

	}

}