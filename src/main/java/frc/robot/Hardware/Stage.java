package frc.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Elevator.Arm;
import frc.robot.Elevator.Lift;

public class Stage {

	public static double   AutonStartTime;
	public static double   AutonFinalTime;
	public static double   StageStartTime;
	
	public static int      StageNumber;
	public static boolean  ReadyToAdvance;	
	public static double[] StageDistance = new double[ Settings.MAX_NUMBER_OF_STAGES ];
	public static double[] StageTime     = new double[ Settings.MAX_NUMBER_OF_STAGES ];

	public static double
		NegTilt = 0,
		PosTilt = 0;

	public static boolean
		DoneWithAuton = true;

	public static void Initialize () {
		
		AutonStartTime = System.currentTimeMillis();
		DoneWithAuton  = false;
		StageStartTime = AutonStartTime;
		StageNumber    = 0;

		for ( int i = 0; i < 5; i++ ) {
			StageDistance[i] = 0;
			StageTime[i]     = 0;
		}
	}

	public static void Display () {
		SmartDashboard.putNumber("Robot-Stage Number",   StageNumber             );
		SmartDashboard.putNumber("Robot-Auton Time",     GetAutonDuration() );

		for ( int i = 0; i < 5; i++ ) {
			SmartDashboard.putNumber("Stage Time " + i, StageTime[i] );
			SmartDashboard.putNumber("Stage Dist " + i, StageDistance[i]     );
		}
	}

//
// The Next method advances to the next stage after storing Stage
// information. The Last method stops 
//
	public static void Begin () {
		ReadyToAdvance = true;
	}

	public static void Next () {
		StageDistance[ StageNumber ] = GetDistance();
		StageTime    [ StageNumber ] = GetStageTime();

		if ( (ReadyToAdvance == true) && (DoneWithAuton == false) ) {

			ResetOdometer();

			StageStartTime = System.currentTimeMillis();
			StageNumber++;
		}
	}

	public static void Last () {
		AutonFinalTime = System.currentTimeMillis();
		DoneWithAuton  = true;
		// ReadyToAdvance = false;
		// StageNumber = Settings.MAX_NUMBER_OF_STAGES - 1;

		Autopilot.Stop();
		Elevator.Reset();
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
		// double FR = Swerve.FL_module.DriveMotor.getSelectedSensorPosition();
		// double RL = Swerve.FL_module.DriveMotor.getSelectedSensorPosition();
		// double RR = Swerve.FL_module.DriveMotor.getSelectedSensorPosition();

		// ABS SINCE SOME WHEELS GOING BACKWARD
		FL = Math.abs( FL );
		// FR = Math.abs( FR );
		// RL = Math.abs( RL );
		// RR = Math.abs( RR );
		
		// return Math.abs( FL );
		// TAKE AN AVERAGE FOR SIMPLICITY
		// return ( FL + FR + RL + RR ) * Settings.IN_PER_CLICK / 4;
		return ( FL / 1000 * 3/4 );
	}

	public static void ResetOdometer () {
		Swerve.FL_module.DriveMotor.setSelectedSensorPosition( 0 );
		Swerve.FR_module.DriveMotor.setSelectedSensorPosition( 0 );
		Swerve.RL_module.DriveMotor.setSelectedSensorPosition( 0 );
		Swerve.RR_module.DriveMotor.setSelectedSensorPosition( 0 );
	}

	public static void WaitForDistance ( double Distance ) {
		if ( GetDistance() <= Distance ) {
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
	public static void WaitForArm ( double Tolerance ) {
		if ( Math.abs( Arm.PosER ) > 1.5 ) {
			ReadyToAdvance = false;
		}
	}

	public static void WaitForArmLift ( double ArmTolerance, double LiftTolerance ) {
		WaitForArm ( ArmTolerance  );
		WaitForLift( LiftTolerance );
	}

	public static void WaitForLift ( double Tolerance ) {
		if ( Math.abs( Lift.displacement ) > 1.5 ) {
			ReadyToAdvance = false;
		}
	}

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
