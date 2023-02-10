package frc.robot.Hardware;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Navigation {
    
    public static AHRS NavX;

    public static void Initialize () {
        NavX = new AHRS( SPI.Port.kMXP );
        NavX.calibrate();
        Reset();
    }

    public static void Periodic () {}

    public static void Display() {
        SmartDashboard.putNumber( "Nav-Yaw",    GetYaw()   );
        SmartDashboard.putNumber( "Nav-Pitch",  GetPitch() );
        SmartDashboard.putNumber( "Nav-Roll",   GetRoll()  );
    }

//
// SUPPORT METHODS
//
    public static void   Reset    () { NavX.reset(); }

    public static double GetPitch () { return  NavX.getPitch(); } // Forward tilt : - is up
    public static double GetRoll  () { return  NavX.getRoll();  } // Side-to-side : + is ?
    public static double GetYaw   () { return -NavX.getYaw();   } // Twist        : + is CCW

    public static double GetDirection () { return GetYaw(); }
}
