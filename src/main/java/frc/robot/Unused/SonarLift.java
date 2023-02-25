package frc.robot.Unused;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SonarLift {
 
    public static Ultrasonic SonarLift;

    public static void Initialize () {
        SonarLift = new Ultrasonic(
            0, //Settings.DIO_SonarBack[0], Input
            1  //Settings.DIO_SonarBack[1], Output
        );

        Ultrasonic.setAutomaticMode( true );
    }

    public static void Periodic () {
        
    }

    public static void Display () {
        SmartDashboard.putNumber( "SONAR", GetRange() );
    }

    public static double GetRange () {
        return SonarLift.getRangeInches();
    }
}
