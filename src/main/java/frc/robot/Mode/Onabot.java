package frc.robot.Mode;

import frc.robot.Hardware.Driver;
import frc.robot.Hardware.Elevator;
import frc.robot.Hardware.Navigation;
import frc.robot.Hardware.Swerve;

public class Onabot {

    public static void Initialize () {
        Driver     .Initialize();
        Elevator   .Initialize();
        Navigation .Initialize();
        Swerve     .Initialize();

        // Lidar      .Initialize();
        // Sonar      .Initialize();
        // Vision     .Initialize();
    }

    public static void Periodic () {
        Driver     .Display();
        Elevator   .Display();
        Navigation .Display();
        Swerve     .Display();

        // Lidar      .Display();
        // Sonar      .Display();
        // Vision     .Display();
    }
    
}
