package frc.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Driver.Aubrey;
import frc.robot.Driver.FieldRelative;
import frc.robot.Driver.Nate;
import frc.robot.Driver.RobotRelative;
import frc.robot.Driver.Steensma;

public class Driver {

    public static String SelectedDriver = "RobotRel";

    public static final String kRobotRel = "RobotRelative";
    public static final String kFieldRel = "FieldRelative";
    public static final String kAubrey   = "Aubrey";
    public static final String kNate     = "Nate";
    public static final String kSteensma = "Steensma";
    public static final SendableChooser<String> chooser = new SendableChooser<>();

    public static void Initialize () {
        chooser.setDefaultOption("RobotRel", kRobotRel );
        chooser.addOption       ("FieldRel", kFieldRel );
        chooser.addOption       ("Aubrey",   kAubrey   );
        chooser.addOption       ("Nate",     kNate     );
        chooser.addOption       ("Steensma", kSteensma );
        SmartDashboard.putData  ("DRIVER",   chooser   );
    }

    public static void Periodic () {
        switch ( "RobotRel" ) {
            case "RobotRel" : RobotRelative  .Periodic();
            case "FieldRel" : FieldRelative  .Periodic();
            case "Aubrey"   : Aubrey         .Periodic();
            case "Nate"     : Nate           .Periodic();
            case "Steensma" : Steensma       .Periodic();
        }
    }

    public static void Display () {
        SmartDashboard.putString("Driver", chooser.getSelected() );
    }

}
