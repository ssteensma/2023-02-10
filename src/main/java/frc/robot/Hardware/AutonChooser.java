package frc.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonChooser {

    public static String AutonPath = "";
    
    public static final String kDefault = "Do Nothing";
    public static final String kPath01  = "Path-01";
    public static final String kPath02  = "Path-02";
    public static final String kPath03  = "Path-03";
    public static final String kPath04  = "Path-04";
    public static final String kPath05  = "Path-05";
    public static final String kPath06  = "Path-06";
    public static final String kPath07  = "Path-07";
    public static final String kPath08  = "Path-08";
    public static final String kPath09  = "Path-09";
    public static final SendableChooser<String> chooser = new SendableChooser<>();

    public static void  Initialize () {
        chooser.setDefaultOption( "Do Nothing", kDefault );
        chooser.addOption("Path 01", kPath01 );
        chooser.addOption("Path 02", kPath02 );
        chooser.addOption("Path 03", kPath03 );
        chooser.addOption("Path 04", kPath04 );
        chooser.addOption("Path 05", kPath05 );
        chooser.addOption("Path 06", kPath06 );
        chooser.addOption("Path 07", kPath07 );
        chooser.addOption("Path 08", kPath08 );
        chooser.addOption("Path 09", kPath09 );
        SmartDashboard.putData( "PATH", chooser );
    }

    public static void Periodic () {
        Display();
    }

    public static void Display () {
        SmartDashboard.putString("AUTON MODE", chooser.getSelected() );
    }
}
