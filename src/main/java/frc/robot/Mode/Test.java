package frc.robot.Mode;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Hardware.Stage;

public class Test {

    static Joystick DriveStick;
    static Joystick ManipStick;

    public static void Initialize () {
    }

    public static void Periodic () {
        Stage.Number = 0;
    }

}
