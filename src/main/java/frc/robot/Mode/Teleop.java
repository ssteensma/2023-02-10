package frc.robot.Mode;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Hardware.Driver;
import frc.robot.Hardware.Settings;
import frc.robot.Hardware.Stage;

public class Teleop {

    public static Joystick DriveStick;
    public static Joystick ManipStick;

    public static double Xratio;
    public static double Yratio;
    public static double Tratio;

    public static void Initialize () {
        DriveStick = new Joystick( Settings.DriveStickID );
        ManipStick = new Joystick( Settings.ManipStickID );
    }

    public static void Periodic () {
        Xratio = -DriveStick.getY();
        Yratio = -DriveStick.getX();
        Tratio = -DriveStick.getTwist();

        // UPDATE ALL COMPONENTS
        Driver   .Periodic();
        // Elevator .Periodic();

        Stage.StageNumber = 0;
    }

    public static void Display () {
    }
}