package frc.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonChooser {
    
    public static final String kDefault = "Nothing";
    public static final String kPath01  = "Path-01";
    public static final String kPath02  = "Path-02";
    public static final String kPath03  = "Path-03";
    public static final String kPath04  = "Path-04";
    public static final SendableChooser<String> chooser = new SendableChooser<>();

    public static void  Initialize () {
        chooser.setDefaultOption("Nothing", kDefault );
        chooser.setDefaultOption("Path 01", kPath01  );
        chooser.setDefaultOption("Path 02", kPath02  );
        chooser.setDefaultOption("Path 03", kPath03  );
        chooser.setDefaultOption("Path 04", kPath04  );
        SmartDashboard.putData  ("PATH",    chooser  );
    }

    public static void Periodic () {
        Display();
    }

    public static void Display () {
        SmartDashboard.putString("AUTON MODE", chooser.getSelected() );
    }
}
