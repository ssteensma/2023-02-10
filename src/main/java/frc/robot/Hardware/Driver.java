package frc.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Driver.Aubrey;
import frc.robot.Driver.Default;
import frc.robot.Driver.Nate;
import frc.robot.Driver.Steensma;

public class Driver {

    public static final String kDefault  = "Default";
    public static final String kAubrey   = "Aubrey";
    public static final String kNate     = "Nate";
    public static final String kSteensma = "Steensma";
    public static final SendableChooser<String> chooser = new SendableChooser<>();

    public static void Initialize () {
        chooser.setDefaultOption("Default",  kDefault  );
        chooser.addOption       ("Aubrey",   kAubrey   );
        chooser.addOption       ("Nate",     kNate     );
        chooser.addOption       ("Steensma", kSteensma );
        SmartDashboard.putData  ("DRIVER",   chooser   );
    }

    public static void Periodic () {
        switch ( chooser.getSelected() ) {
            case "Default" : Default  .Periodic();
            case "Aubrey"  : Aubrey   .Periodic();
            case "Nate"    : Nate     .Periodic();
            case "Steensma": Steensma .Periodic();
        }
    }

    public static void Display () {
        SmartDashboard.putString("Driver", chooser.getSelected() );
    }

}
