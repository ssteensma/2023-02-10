package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevLift {

    public static double    last_position   = 0;
    public static double    target_position = 0;
    public static VictorSPX liftMotor;
    public static double    voltage = 0;

    public static void Initialize () {
        liftMotor = new VictorSPX( 20 );
    }

    public static void Periodic () {

        double current_position = 0;

        // CALCULATE DISPLACEMENT AND PSEUDO VELOCITY
        double displacement = target_position  - current_position;
        double velocity     = current_position - last_position;

        // COORDINATE SYSTEM: + is up; - is down
        double disMag = Math.abs( displacement ); double disSig = Math.signum( displacement );
        double velMag = Math.abs( velocity     ); double velSig = Math.signum( velocity     );

        // TOLERANCES
        double disTolerance = 5;
        double velTolerance = 5;

        // SITUATIONS
        voltage += displacement / 10;

        liftMotor.set( VictorSPXControlMode.PercentOutput, voltage );
    }

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

}
