package frc.robot.Unused;

import edu.wpi.first.wpilibj.Ultrasonic;

public class Sonar {
 
    public static Ultrasonic SonarBack;

    public static void Initialize () {
        SonarBack = new Ultrasonic(
            0, //Settings.DIO_SonarBack[0],
            1  //Settings.DIO_SonarBack[1],
        );

        Ultrasonic.setAutomaticMode( true );
    }

    public static void Periodic () {
    
    }

    public static void Display () {
        
    }

    public static double GetRange () {
        return SonarBack.getRangeInches();
    }
}
