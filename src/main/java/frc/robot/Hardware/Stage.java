package frc.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Stage {

	public static double   AutonStartTime;
	public static double   AutonFinalTime;
	public static double   StageStartTime;
	
	public static int      StageNumber;
	public static boolean  ReadyToAdvance;	
	public static double[] StageDistance = new double[ Settings.MAX_NUMBER_OF_STAGES ];
	public static double[] StageTime     = new double[ Settings.MAX_NUMBER_OF_STAGES ];

	public static double   NegTilt = 0;
	public static double   PosTilt = 0;
	

	public static void Initialize () {
		
		AutonStartTime = System.currentTimeMillis();
		StageStartTime = AutonStartTime;
		StageNumber    = 0;
	}

	public static void Display () {
		SmartDashboard.putNumber("Robot-Stage Number",   StageNumber             );
		SmartDashboard.putNumber("Robot-Stage Distance", GetDistance()      );
		SmartDashboard.putNumber("Robot-Stage Time",     GetStageTime()     );
		SmartDashboard.putNumber("Robot-Auton Time",     GetAutonDuration() );
	}

//
// The Next method advances to the next stage after storing Stage
// information. The Last method stops 
//
	public static void Begin () {
		Autopilot.Stop();
		ReadyToAdvance = true;
	}

	public static void Next () {
		if ( ReadyToAdvance == true ) {
			StageDistance[ StageNumber ] = GetDistance();
			StageTime    [ StageNumber ] = GetStageTime();

			ResetOdometer();

			StageStartTime = System.currentTimeMillis();
			StageNumber++;
		}
	}

	public static void Last () {
		AutonFinalTime = System.currentTimeMillis();
		ReadyToAdvance = false;
		StageNumber = Settings.MAX_NUMBER_OF_STAGES - 1;

		Autopilot.Stop();
	}

	public static void Fail () {
		AutonFinalTime = System.currentTimeMillis();
		ReadyToAdvance = false;
		StageNumber    = Settings.MAX_NUMBER_OF_STAGES;
	}

//
// Get...Time methods are useful in auton mode to determine the amount
// of time that the current stage or the entire auton process has been
// executing.
//
	public static double GetAutonDuration () {
		return ( System.currentTimeMillis() - AutonStartTime ) / 1000.0;
	}

	public static double GetStageTime () {
		return ( System.currentTimeMillis() - StageStartTime ) / 1000.0;
	}

	public static void WaitForDuration ( double Duration ) {
		if ( GetStageTime() < Duration ) {
			ReadyToAdvance = false;
		}
	}

//
//
//
	public static double GetDistance () {
		double FL = Swerve.FL_module.DriveMotor.getSelectedSensorPosition();
		double FR = Swerve.FL_module.DriveMotor.getSelectedSensorPosition();
		double RL = Swerve.FL_module.DriveMotor.getSelectedSensorPosition();
		double RR = Swerve.FL_module.DriveMotor.getSelectedSensorPosition();

		// ABS SINCE SOME WHEELS GOING BACKWARD
		FL = Math.abs( FL );
		FR = Math.abs( FR );
		RL = Math.abs( RL );
		RR = Math.abs( RR );
		
		// TAKE AN AVERAGE FOR SIMPLICITY
		return ( FL + FR + RL + RR ) * Settings.IN_PER_CLICK / 4;
	}

	public static void ResetOdometer () {
		Swerve.FL_module.DriveMotor.setSelectedSensorPosition( 0 );
		Swerve.FR_module.DriveMotor.setSelectedSensorPosition( 0 );
		Swerve.RL_module.DriveMotor.setSelectedSensorPosition( 0 );
		Swerve.RR_module.DriveMotor.setSelectedSensorPosition( 0 );
	}

	public static void WaitForDistance ( double Distance ) {
		if ( GetDistance() < Distance ) {
			ReadyToAdvance = false;
		}
	}

//
//
//
	public static void WaitForHeading ( double Heading, double Tolerance ) {
		double diff = Autopilot.HeadingDiff( Heading );
		if ( Math.abs( diff ) < Tolerance ) {
			ReadyToAdvance = false;
		}
	}

//
// Second draft of code to be used in auton. Drive forward until we notice an incline.
// At that point we advance stages and continue to drive forward until we notice a
// balanced condition. It would be good to also have a maximum distance travelled for
// each stage and fail if the condition is not met.
//
	public static void WaitForBalance ( double Tolerance ) {
		double pitch = Navigation.GetPitch();
		if ( Math.abs( pitch ) > Tolerance ) {
			ReadyToAdvance = false;
		}
	}

	public static void WaitForIncline ( double Angle ) {
		double pitch = Navigation.GetPitch();
		if ( Math.abs( pitch ) < Angle ) {
			ReadyToAdvance = false;
		}
	}

//
//
//
	// public static void WaitForWheelAlignment ( double Angle ) {

	// }
	// public static void WaitForHeading ( double Heading, double Tolerance ) {

	// }

//	public static boolean WaitForHeading( double targetHeading, double tolerance ) {
//		if ( Math.abs(Navigation.GetDelta(targetHeading)) < tolerance ) { return true; }
//	}
//	
//
//	public static boolean WaitForTarget( double tolerance ) {
//		if ( Drivetrain.TargetMin<-tolerance || Drivetrain.TargetMax>tolerance ) {
//			StillWorking = true;
//			return true;
//		}
//		else {
//			return false;
//		}
//	
//	}
	
	
}
