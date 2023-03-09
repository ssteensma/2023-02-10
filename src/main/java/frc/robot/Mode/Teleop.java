package frc.robot.Mode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Hardware.Driver;
import frc.robot.Hardware.Elevator;
import frc.robot.Hardware.Settings;

public class Teleop {

    public static Joystick       DriveStick;
    public static XboxController ManipStick;

    public static double
        Xratio,
        Yratio,
        Tratio;

    public static void Initialize () {
        DriveStick = new Joystick      ( Settings.DriveStickID );
        ManipStick = new XboxController( Settings.ManipStickID );
    }

    public static void Periodic () {
        Xratio = -DriveStick.getY();
        Yratio = -DriveStick.getX();
        Tratio = -DriveStick.getTwist();

        // UPDATE MECHANISMS
        Driver   .Periodic();
        Elevator .Periodic();
    }

    public static void Display () {
    }
}