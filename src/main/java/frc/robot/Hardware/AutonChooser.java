package frc.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonChooser {

    public static String AutonPath = "";
    
    public static final String kDefault = "Nothing";
    public static final String Balance  = "Balance";
    public static final String Any_1st  = "Any 1st";
    public static final String Any_2nd  = "Any 2nd";
    public static final String Lft_1st  = "Lft 1st";
    public static final String Lft_2nd  = "Lft 2nd";
    public static final String Ctr_1st  = "Ctr 1st";
    public static final String Ctr_2nd  = "Ctr 2nd";
    public static final String Rgt_1st  = "Rgt 1st";
    public static final String Rgt_2nd  = "Rgt 2nd";
    public static final SendableChooser<String> chooser = new SendableChooser<>();

    public static void  Initialize () {
        chooser.setDefaultOption( "Nothing", kDefault );
        chooser.addOption("Balance", Balance );
        chooser.addOption("Any 1st", Any_1st );
        chooser.addOption("Any 2nd", Any_2nd );
        // chooser.addOption("Lft 1st", Lft_1st );
        // chooser.addOption("Lft 2nd", Lft_2nd );
        chooser.addOption("Ctr 1st", Ctr_1st );
        chooser.addOption("Ctr 2nd", Ctr_2nd );
        // chooser.addOption("Rgt 1st", Rgt_1st );
        // chooser.addOption("Rgt 2nd", Rgt_2nd );
        SmartDashboard.putData( "PATH", chooser );
    }

    public static void Periodic () {
        Display();
    }

    public static void Display () {
        SmartDashboard.putString("AUTON MODE", AutonPath );
    }
}
