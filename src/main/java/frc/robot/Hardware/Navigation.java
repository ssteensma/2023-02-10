package frc.robot.Hardware;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Navigation {
    
    public static Ultrasonic
        FrontSonar;

    public static double
        Offset = 0;

    public static AHRS NavX;

    public static void Initialize () {
        NavX = new AHRS( SPI.Port.kMXP );
        NavX.calibrate();
        Reset();

        // FrontSonar = new Ultrasonic(
        //     Settings.FrontSonar_DIO[0], // Input
        //     Settings.FrontSonar_DIO[1]  // Output
        // );

    }

    public static void Periodic () {}

    public static void Display() {
        SmartDashboard.putNumber( "Nav-Yaw",    GetDirection() );
        SmartDashboard.putNumber( "Nav-Pitch",  GetPitch()     );
    }

//
// SUPPORT METHODS
//
    public static void   Reset    () { NavX.reset(); }

    public static double GetPitch () { return  NavX.getPitch(); } // Forward tilt : - is up
    public static double GetRoll  () { return  NavX.getRoll();  } // Side-to-side : + is ?
    public static double GetYaw   () { return -NavX.getYaw();   } // Twist        : + is CCW

    public static double GetDirection () {
        return GetYaw();
    }
}
