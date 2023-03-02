package frc.robot.Driver;

import frc.robot.Elevator.Arm;
import frc.robot.Hardware.Swerve;
import frc.robot.Mode.Teleop;

public class Steensma {

    public static void Periodic () {

        if ( Teleop.DriveStick.getRawButton(9) ) {
            Arm.SetHI();
        }

        if ( Teleop.DriveStick.getRawButton(11) ) {
            Arm.SetLO();
        }
       
        Swerve.UpdateRobotRelative(0, 0, 0);

    }
    
}
